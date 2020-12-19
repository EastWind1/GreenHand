package com.greenhand.sort;

import org.springframework.stereotype.Component;

/**
 * 希尔排序
 */
@Component
public class ShellSort implements Sort {
    @Override
    public int[] sort(int[] source) {
        for (int i = source.length / 2; i >=1 ; i = i/2) {
            for (int j = 0; j < source.length - i; j++) {
                int point  = j + i;
                int current = source[point];
                while (point - i >= 0 && current < source[point - i]) {
                    source[point] = source[point - i];
                    point -= i;
                }
                source[point] = current;
            }
        }
        return source;
    }
}
