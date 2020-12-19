package com.greenhand.sort;

import org.springframework.stereotype.Component;

/**
 * 堆排序
 */
@Component
public class HeapSort implements Sort{
    @Override
    public int[] sort(int[] source) {
        heapSort(source);
        return source;
    }

    /**
     * 构建最大堆
     */
    private void buildMaxHeap(int[] source) {
        for (int i = source.length / 2; i >= 0 ; i--) {
            adjustHeap(source, i, source.length -1);
        }
    }

    /**
     * 调整堆
     */
    private void adjustHeap(int[] heap, int head, int size) {
        int left = head * 2 + 1 > size ? -1 : head * 2 + 1;
        int right = head * 2 + 2 > size ? -1 : head * 2 + 2;
        int maxIndex = head;
        if (left != -1 && heap[left] > heap[maxIndex]) {
            maxIndex = left;
        }
        if (right!= -1&& heap[right] > heap[maxIndex]) {
            maxIndex = right;
        }
        if (maxIndex != head) {
            int temp = heap[head];
            heap[head] = heap[maxIndex];
            heap[maxIndex] = temp;

            adjustHeap(heap, maxIndex, size);
        }
    }

    /**
     * 堆排序
     */
    private void heapSort(int[] source) {
        buildMaxHeap(source);
        for (int i = source.length -1; i > 0 ; i--) {
            int temp = source[0];
            source[0] = source[i];
            source[i] = temp;

            adjustHeap(source, 0, i - 1);
        }
    }
}
