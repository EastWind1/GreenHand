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

    private void mergeSort(int[] source, int l, int r) {
        if (l >= r) {
            return;
        }
        // 从中间划分
        int m = l + (r - l) / 2;
        mergeSort(source, l, m);
        mergeSort(source, m + 1, r);

        // 合并
        // 暂存
        int[] temp = new int[r - l + 1];
        for (int i = l; i <= r ; i++) {
            temp[i - l] = source[i];
        }
        int p1 = 0, p2 = m - l + 1;
        for (int i = l; i <= r ; i++) {
            // 左侧数组遍历完
            if (p1 == m - l + 1) {
                source[i] = temp[p2++];
            } else if (p2 == r - l + 1 || temp[p1] <= temp[p2]) { // 右侧遍历完或左侧小于右侧
                source[i] = temp[p1++];
            } else { // 左侧大于右侧
                source[i] = temp[p2++];
            }
        }
    }
}
