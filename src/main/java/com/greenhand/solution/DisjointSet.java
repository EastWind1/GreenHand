package com.greenhand.solution;

/**
 * 并查集
 */
public class DisjointSet {
    /** 父节点 */
    private final int[] parent;
    /** 当前节点层级（秩），用于合并时优化查找层级 */
    private final int[] rank;
    public DisjointSet(int count) {
        this.parent = new int[count];
        this.rank = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 查找根节点
     */
    public int find(int cur) {
        if (parent[cur] == cur) {
            return parent[cur];
        }
        // 路径压缩优化
        parent[cur] = find(parent[cur]);
        return parent[cur];
    }

    /**
     * 合并
     */
    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }
        // 较低的合并至较高的 (按秩排序，在路径压缩下并不准确）
        if (rank[x] <= rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
        // 若相同，深度加一
        if(rank[x] == rank[y]) {
            rank[y] ++;
        }
    }
}
