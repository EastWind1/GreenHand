package com.greenhand.leetcode;

import java.util.*;

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

    /**
     * 遍历结果，用于toString
     */
    private class DFSResult {
        public int value;
        public int layer;
        public int index;
        public DFSResult parent;

        public DFSResult(int value, int layer, int index, DFSResult parent) {
            this.value = value;
            this.layer = layer;
            this.index = index;
            this.parent = parent;
        }
    }

    /**
     * 打印树结构
     */
    @Override
    public String toString() {
        List<DFSResult> list = new ArrayList<>();
        frontDfs(list, this, 0, null);
        int height = 0;
        for (DFSResult dfsResult : list) {
            height = Math.max(height, dfsResult.layer + 1);
        }
        String[][] res = new String[height * 3][list.size()];
        for (int i = 0; i < list.size(); i++) {
            DFSResult cur = list.get(i);
            res[cur.layer * 3 + 2][i] = String.format("% 10d", cur.value);
            if (cur.parent != null) {
                res[cur.layer * 3 + 1][i] = " ".repeat(9) + "|";
                if (i <= cur.parent.index) {
                    for (int j = i+1; j <= cur.parent.index ; j++) {
                        res[cur.layer * 3][j] = "_".repeat(10);
                    }
                } else {
                    for (int j = cur.parent.index + 1; j <= i; j++) {
                        res[cur.layer * 3][j] = "_".repeat(10);
                    }
                }
                if (res[cur.layer * 3][cur.parent.index] == null) {
                    res[cur.layer * 3][cur.parent.index] = " ".repeat(10);
                }
                StringBuilder parentRes = new StringBuilder(res[cur.layer * 3][cur.parent.index]);
                parentRes.setCharAt(9, '|');
                res[cur.layer * 3][cur.parent.index] = parentRes.toString();


            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Tree height: ").append(height).append("\n");
        sb.append("Tree node count: ").append(list.size()).append("\n");
        for (int i = 2; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                if (res[i][j] == null) {
                    sb.append(" ".repeat(10));
                } else {
                    sb.append(res[i][j]);
                }
                sb.append(' ');
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 前序遍历获得索引，用于toString中计算各个节点的横坐标位置
     * @param res 遍历结果
     * @param root 当前节点
     * @param layer 当前层级
     * @param parent 父节点遍历结果
     */
    private void frontDfs(List<DFSResult> res, TreeNode root, int layer, DFSResult parent) {
        if (root == null) {
            return;
        }
        DFSResult cur = new DFSResult(root.val, layer, 0, parent);
        frontDfs(res, root.left, layer+1, cur);
        cur.index = res.size();
        res.add(cur);
        frontDfs(res, root.right, layer + 1, cur);
    }
}
