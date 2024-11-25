package com.greenhand.leetcode;

import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] map = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                map[i][j] = -1;
            }
        }
        for (int[] time : times) {
            map[time[0]][time[1]] = time[2];
        }
        int[] res = dijkstra(map, n, k);
        int max = 0;
        for (int i = 1; i <=n; i++) {
            if (res[i] == -1) {
                return -1;
            }
            max = Math.max(max, res[i]);
        }
        return max;
    }
    private int[] dijkstra(int[][] map, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[k] = 0;
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.offer(new int[]{k, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;
            for (int i = 1; i < n + 1; i++) {
                if (visited[i]) {
                    continue;
                }
                if (map[cur[0]][i] != -1 && (dist[i] == -1 || dist[i] > dist[cur[0]] + map[cur[0]][i])) {
                    dist[i] = dist[cur[0]] + map[cur[0]][i];
                    queue.offer(new int[]{i, dist[i]});
                }
            }
        }
        return dist;
    }
}