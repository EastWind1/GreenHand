package com.greenhand.sort;

import org.springframework.stereotype.Component;

/**
 * 插入排序
 */
@Component
public class InsertSort implements Sort {
    @Override
    public int[] sort(int[] source) {
        for (int i = 0; i < source.length - 1; i++) {
            int point  = i + 1;
            int current = source[point];
            while (point > 0 && current < source[point - 1]) {
                source[point] = source[point - 1];
                point --;
            }
            source[point] = current;
        }
        return source;
    }
}
