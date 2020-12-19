package com.greenhand.sort;

import org.springframework.stereotype.Component;

/**
 * 选择排序
 */
@Component
public class SelectSort implements Sort{
    @Override
    public int[] sort(int[] source) {
        for (int i = 0; i < source.length; i++) {
            int index = i;
            for (int j = i+1; j < source.length; j++) {
                if (source[j] < source[index]) {
                    index = j;
                }
            }
            int temp = source[i];
            source[i] = source[index];
            source[index] = temp;
        }
        return source;
    }
}
