package com.greenhand.sort;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 控制台执行类
 */
@Slf4j
@Component
public class SortTest implements CommandLineRunner {

    private final List<Sort> sortList;

    public SortTest(List<Sort> sortList) {
        this.sortList = sortList;
    }

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        int[] source = new int[10];
        for (int i = 0; i < source.length; i++) {
            source[i] = random.nextInt(100);
        }
        // log.debug("origin arrays: " + arrayToString(source));
        for (Sort sort : sortList) {
            int[] copy = Arrays.copyOf(source, source.length);
            long start = System.currentTimeMillis();
            int[] result = sort.sort(copy);
            log.info(sort.getClass().getSimpleName() + " cast {}ms", System.currentTimeMillis() - start);
            // log.debug(sort.getClass().getSimpleName() + " sort result: " + arrayToString(result));
        }
    }

    private String arrayToString(int[] arrays) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i : arrays) {
            sb.append(i);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
