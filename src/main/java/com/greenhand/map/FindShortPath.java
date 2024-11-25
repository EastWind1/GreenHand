package com.greenhand.map;

import java.util.*;

public class FindShortPath {
    /**
     * Dijkstra算法
     * 不支持权值为负
     *
     * @param map   图临接矩阵
     * @param start 起始点
     * @return 到各点的最短路径距离
     */
    public int[] dijkstra(int[][] map, int start) {
        // 到某点的最短路径长度
        int[] dist = new int[map.length];
        // 除起点外初始化为最大
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        // 已确定的点集合
        boolean[] visit = new boolean[map.length];
        // 遍历 map.length 次
        // 此处可以使用最小堆进行优化
        for (int i = 0; i < map.length; i++) {
            // 寻找未确定的点中距离最短的点

            int min = -1;
            for (int j = 0; j < map.length; j++) {
                if (!visit[j] && (min == -1 || dist[j] < dist[min])) {
                    min = j;
                }
            }
            if (min == -1 || dist[min] == Integer.MAX_VALUE) {
                break;
            }
            // 标记为已确定
            visit[min] = true;
            // 尝试用经过该点的路径更新其他点的最短路径
            // 若没有路径，跳过
            if (dist[min] != Integer.MAX_VALUE) {
                for (int j = 0; j < map.length; j++) {
                    if (!visit[j] && map[min][j] != Integer.MAX_VALUE) {
                        dist[j] = Math.min(dist[j], dist[min] + map[min][j]);
                    }
                }
            }
        }
        return dist;
    }

    /**
     * Dijkstra算法，优先队列优化
     *
     * @param map 稀疏矩阵表示的图，map[i] = [{a,b}],表示节点i相连的节点a及对应的权值b
     * @return 节点0到其他节点的最短距离
     */
    private int[] dijkstra2(List<int[]>[] map) {
        // 到某点的最短路径长度
        int[] dist = new int[map.length];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        // 节点距离最小堆
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // 是否已经确定距离
        Set<Integer> visited = new HashSet<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited.contains(cur[0])) {
                continue;
            }
            visited.add(cur[0]);
            // 遍历相邻节点，尝试更新最短距离
            for (int[] next : map[cur[0]]) {
                if (!visited.contains(next[0]) && (dist[next[0]] == -1 || dist[next[0]] > dist[cur[0]] + next[1])) {
                    dist[next[0]] = dist[cur[0]] + next[1];
                    queue.add(new int[]{next[0], dist[next[0]]});
                }
            }
        }
        return dist;
    }

    /**
     * Bellman-Ford算法
     *
     * @param edges 边集合,{起点，终点，权值}
     * @param n     点数量
     * @param start 起始点
     * @return 到各点的最短路径距离
     */
    public int[] bellmanFord(int[][] edges, int n, int start) {
        // 到某点的最短路径长度
        int[] res = new int[n];
        // 除起点外初始化为最大
        Arrays.fill(res, Integer.MAX_VALUE);
        res[start] = 0;
        // 遍历n-1次
        for (int i = 0; i < n - 1; i++) {
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
        return res;
    }
    /**
     * SPFA算法，对Bellman-Ford进行优化
     * 思路为每次遍历更新的点相连的边，而不是遍历所有边
     *
     * 与Dijkstra区别，可解决负权值的问题
     *
     * @param map 图临接矩阵
     * @param n     点数量
     * @param start 起始点
     * @return 到各点的最短路径距离
     */
    public int[] spfa(int[][] map, int n, int start) {
        // 到某点的最短路径长度
        int[] dist = new int[n];
        // 除起点外初始化为最大
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 已更新点的队列
        Deque<Integer> queue = new ArrayDeque<>();
        // 点是否已经入队
        boolean[] isInQueue = new boolean[n];
        queue.add(start);
        isInQueue[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < n; i++) {
                // 松弛操作
                if (map[cur][i] != -1 && dist[i] > dist[cur] + map[cur][i]) {
                    dist[i] = dist[cur] + map[cur][i];
                    // 若未在队列中则入队
                    if (!isInQueue[i]) {
                        queue.add(i);
                        isInQueue[i] = true;
                    }
                }
            }
            isInQueue[start] = false;
        }

        /*
         * 判断负环
         * for (int[] edge : edges) {
         * if (res[edge[1]] < res[edge[0]] + edge[2]) {
         * return true;
         * }
         * }
         */
        return dist;
    }

    /**
     * Floyd-Warshall算法
     *
     * @param map 图临接矩阵
     * @return 任意两点间的最短距离
     */
    public int[][] floydWarshall(int[][] map) {
        int[][] res = new int[map.length][map.length];
        for (int[] row : res) {
            Arrays.fill(row, Integer.MAX_VALUE);
            // 此处也可赋值二分子一最大值，后续无需判断是否为最大值，且避免加法溢出，
        }
//        // 克隆map
//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < res.length; j++) {
//                res[i][j] = map[i][j];
//            }
//        }
        // 对任意两点i，j，尝试让其路径经过k，取较小值
        for (int k = 0; k < map.length; k++) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (i != j && res[i][k] != Integer.MAX_VALUE && res[k][j] != Integer.MAX_VALUE
                            && res[i][j] > res[i][k] + res[k][j]) {
                        res[i][j] = res[i][k] + res[k][j];
                    }
                }
            }
        }
        return res;
    }
}
