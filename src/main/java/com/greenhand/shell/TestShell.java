package com.greenhand.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class TestShell {
    @ShellMethod(value = "test")
    public String test(String name, String msg) {
        return msg;
    }
    @ShellMethod(value = "info")
    public String info() {
        StringBuilder sb = new StringBuilder();
        System.getProperties().forEach((key, value) -> {
            sb.append(key);
            sb.append(" = ");
            sb.append(value);
            sb.append("\n");
        });
        return sb.toString();
    }
    @ShellMethod(value = "help")
    public String help() {
        return "Programming";
    }
}
