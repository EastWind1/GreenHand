package com.greenhand.sort;

import org.springframework.stereotype.Component;

/**
 * 计数排序
 */
@Component
public class CountSort implements Sort {

    @Override
    public int[] sort(int[] source) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : source) {
            if (min > n) {
                min = n;
            }
            if (max < n) {
                max = n;
            }
        }
        int[] bucket = new int[max - min + 1];
        for (int n : source) {
            bucket[n - min] ++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                source[index] = min + i;
                index++;
            }
        }
        return source;
    }
}
