package com.greenhand.sort;

import org.springframework.stereotype.Component;

/**
 * 快速排序
 */
@Component
public class QuickSort implements Sort {
    @Override
    public int[] sort(int[] source) {
        quickSort(source, 0, source.length - 1);
        return source;
    }

    /**
     * 移动基准
     */
    private int moveBase(int[] source, int left, int right) {
        int base = source[left];
        while (left < right) {
            while (left < right && source[right] > base) {
                right--;
            }
            source[left] = source[right];
            while (left < right && source[left] <= base) {
                left++;
            }
            source[right] = source[left];
        }
        source[left] = base;
        return left;
    }

    /**
     * 排序
     */
    private void quickSort(int[] source, int left, int right) {
        if (left < right) {
            int base = moveBase(source, left, right);
            quickSort(source, left, base - 1);
            quickSort(source, base + 1, right);
        }
    }
}
