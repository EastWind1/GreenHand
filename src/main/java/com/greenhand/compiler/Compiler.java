package com.greenhand.compiler;

import org.junit.jupiter.api.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Compiler {
    private final JavaCompiler compiler;

    public Compiler() {
        this.compiler = ToolProvider.getSystemJavaCompiler();
    }

    public Object run(String source, String className, Iterable<Object> constructParams, String method, Object... args) {
        Path path = buildFile(source, className);
        compiler(path);
        Class clazz = loadClass(className, path);
        return invoke(clazz, constructParams, method, args);
    }

    private Path buildFile(String source, String className) {
        Path path = Paths.get(String.format("target/dynamic/%s.java", className));
        try {
            if (Files.notExists(path.getParent())) {
                Files.createDirectory(path.getParent());
            }
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.writeString(path, source);
        } catch (IOException e) {
            throw new RuntimeException("build file failed: " + e.getMessage(), e);
        }
        return path;
    }

    private void compiler(Path path) {
        compiler.run(null, null, null, path.toString());

    }

    private Class loadClass(String className, Path path) {
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://" + path.getParent().toAbsolutePath() + "/")})) {
            return classLoader.loadClass(className);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Load class failed: " + e.getMessage(), e);
        }
    }

    private Object invoke(Class clazz, Iterable<Object> constructParams, String method, Object... args) {
        try {
            Method m = clazz.getDeclaredMethod(method);
            Object instance = null;
            if (!Modifier.isStatic(m.getModifiers())) {
                Object[] conParams;
                if (constructParams != null) {
                    int conParamsSize = 0;
                    for (Object constructParam : constructParams) {
                        conParamsSize++;
                    }
                    conParams = new Object[conParamsSize];
                    conParamsSize = 0;
                    for (Object constructParam : constructParams) {
                        conParams[conParamsSize] = constructParam;
                        conParamsSize++;
                    }
                } else {
                    conParams = new Object[0];
                }
                instance = clazz.getConstructor().newInstance(conParams);
            }
            return m.invoke(instance, args);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException("invoke method failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void test() {
        String content = """
                    public class Test {
                        public void test() {
                            System.out.println("Hello World");
                        }
                    }
                """;
        run(content, "Test", null, "test");
    }
}
