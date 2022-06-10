package com.greenhand.sort;

import org.springframework.stereotype.Component;

/**
 * 归并排序
 */
@Component
public class MergeSort implements Sort{
    @Override
    public int[] sort(int[] source) {
        mergeSort(source, 0, source.length-1);
        return source;
    }

    /**
     * 合并
     */
    private void merge(int[] source, int left, int middle, int right) {
        int index1 = left;
        int index2 = middle + 1;
        int[] result = new int[right - left + 1];
        int index = 0;
        while (index1 <= middle && index2 <= right) {
            if (source[index1] <= source[index2]) {
                result[index] = source[index1++];
            } else {
                result[index] = source[index2++];
            }
            index++;
        }
        while (index1 <= middle) {
            result[index++] = source[index1++];
        }
        while (index2 <= right) {
            result[index++] = source[index2++];
        }
        System.arraycopy(result, 0, source, left, result.length);
    }
    /**
     * 递归调用
     */
    private void mergeSort(int[] source, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + (right - left) / 2;
        mergeSort(source, left, middle);
        mergeSort(source, middle + 1, right);
        merge(source, left, middle, right);
    }
}
