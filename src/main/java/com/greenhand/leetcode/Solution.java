package com.greenhand.leetcode;


import java.util.*;

class Solution {


    public int flipChess(String[] chessboard) {

        int max = 0;
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length(); j++) {
                if (chessboard[i].charAt(j) == '.') {
                    max = Math.max(max, reverse(copy(chessboard), i, j));
                }
            }
        }
        return max;

    }

    private int reverse(char[][] map, int r, int c) {
        int res = map[r][c] == 'O' ? 1 : 0;
        map[r][c] = 'X';
        int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        for (int[] dir : dirs) {
            List<int[]> list = new ArrayList<>();
            int nr = r + dir[0];
            int nc = c + dir[1];
            while (nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length && map[nr][nc] == 'O') {
                list.add(new int[]{nr, nc});
                nr += dir[0];
                nc += dir[1];
            }
            if (nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length && map[nr][nc] == 'X') {
                for (int[] node : list) {
                    res += reverse(map, node[0], node[1]);
                }
            }
        }
        return res;
    }

    private char[][] copy(String[] chessboard) {
        char[][] map = new char[chessboard.length][chessboard[0].length()];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = chessboard[i].charAt(j);
            }
        }
        return map;
    }
}