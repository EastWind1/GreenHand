package com.greenhand.map;

import java.util.Arrays;
import java.util.List;

public class FindShortPath {
    /**
     * Dijkstra算法
     * 不支持权值为负
     * @param map 图临接矩阵
     * @param start 起始点
     * @return 到各点的最短路径距离
     */
    public int[] dijkstra(int[][] map, int start) {
        // 到某点的最短路径长度
        int[] res = new int[map.length];
        // 除起点外初始化为最大
        Arrays.fill(res, Integer.MAX_VALUE);
        res[start] = 0;
        // 已确定的点集合
        boolean[] visit = new boolean[map.length];
        // 遍历所有点
        for (int i = 0; i < map.length ; i++) {
            // 寻找未确定的点中距离最短的点
            int min = -1;
            for (int j = 0; j < map.length; j++) {
                if (!visit[j] && (min == -1 || res[j] < res[min])) {
                    min = j;
                }
            }
            // 标记为已确定
            visit[min] = true;
            // 尝试用经过该点的路径更新其他点的最短路径
            for (int j = 0; j < map.length; j++) {
                if (!visit[i] && map[min][j] != Integer.MAX_VALUE) {
                    res[i] = Math.min(res[i], res[min] + map[min][j]);
                }
            }
        }
        return res;
    }
    /**
     * Bellman-Ford算法
     * @param edges 边集合,{起点，终点，权值}
     * @param n 点数量
     * @param start 起始点
     * @return 到各点的最短路径距离
     */
    public int[] bellmanFord(int[][] edges, int n, int start) {
        // 到某点的最短路径长度
        int[] res = new int[n];
        // 除起点外初始化为最大
        Arrays.fill(res, Integer.MAX_VALUE);
        res[start] = 0;
        // 遍历所有边
        for (int i = 0; i < n; i++) {
            // 是否有更新，若没有，则证明所有可达的点的最短距离已计算
            boolean hasUpdate = false;
            for (int[] edge : edges) {
                // 尝试用当前边更新到该点的最短距离
                if (res[edge[0]] != Integer.MAX_VALUE && res[edge[1]] > res[edge[0]] + edge[2]) {
                    res[edge[1]] = res[edge[0]] + edge[2];
                    hasUpdate = true;
                }
            }
            if (!hasUpdate) {
                break;
            }
        }
        /* 判断负环
        for (int[] edge : edges) {
            if (res[edge[1]] < res[edge[0]] + edge[2]) {
                return true;
            }
        }
         */
        return res;
    }

    /**
     * Floyd-Warshall算法
     * @param map 图临接矩阵
     * @param start 起始点
     * @return 任意两点间的最短距离
     */
    public int[][] floydWarshall(int[][] map, int start) {
        // 克隆map
        int[][] res = new int[map.length][map.length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                res[i][j] = map[i][j];
            }
        }
        // 对任意两点i，j，尝试让其路径经过k，取较小值
        for(int k = 0; k < map.length; k++) {
            for(int i = 1; i < map.length; i++) {
                for(int j = 1; j < map.length; j++) {
                    if(res[i][k] != Integer.MAX_VALUE && res[k][j] != Integer.MAX_VALUE && res[i][j] > res[i][k] + res[k][j]) {
                        res[i][j] = res[i][k] + res[k][j];
                    }
                }
            }
        }
        return res;
    }
}
