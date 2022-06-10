package com.greenhand.solution;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    TreeNode(Integer[] array) {
        if (array == null || array.length ==0 || array[0] == null) {
            return;
        }
        val= array[0];
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(this);
        int index = 1;
        while (index < array.length && !queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (array[index] != null) {
                TreeNode left = new TreeNode(array[index]);
                queue.add(left);
                cur.left = left;
            }
            index++;
            if (index < array.length && array[index] != null) {
                TreeNode right = new TreeNode(array[index]);
                queue.add(right);
                cur.right = right;
            }
            index++;
        }
    }
}
