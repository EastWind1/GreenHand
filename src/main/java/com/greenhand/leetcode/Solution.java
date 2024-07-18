package com.greenhand.leetcode;

import java.util.*;

class Solution {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<int[]>[] map = new List[n];
        Arrays.setAll(map, k -> new ArrayList<>());
        for (int[] edge : edges) {
            map[edge[0]].add(new int[]{edge[1], edge[2]});
            map[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        int[] dist = dijkstra(map, disappear);
        int[] res = new int[n];
        for (int i = 1; i <res.length ; i++) {
            if (dist[i] >= disappear[i]) {
                res[i] = -1;
            } else {
                res[i] = dist[i];
            }
        }
        return res;
    }
    private int[] dijkstra(List<int[]>[] map, int[] disappear) {
        int[] dist = new int[map.length];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)-> a[1] - b[1]);
        Set<Integer> visited = new HashSet<>();
        queue.add(new int[]{0,0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited.contains(cur[0])) {
                continue;
            }
            visited.add(cur[0]);
            for (int[] next : map[cur[0]]) {
                if (!visited.contains(next[0]) && (dist[next[0]] == -1 ||dist[next[0]] > dist[cur[0]] + next[1])) {
                    if (dist[cur[0]] + next[1] < disappear[next[0]]) {
                        dist[next[0]] = dist[cur[0]] + next[1];
                        queue.add(new int[]{next[0], dist[next[0]]});
                    }
                }
            }
        }
        return dist;
    }
}