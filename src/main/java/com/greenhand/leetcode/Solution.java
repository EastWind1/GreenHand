package com.greenhand.leetcode;

import java.util.*;

class Solution {
    private record Job(int startTime, int endTime, int profit) {}
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        if (startTime.length == 0) {
            return 0;
        }
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a,b) -> a.endTime - b.endTime);
        int[] dp = new int[jobs.length + 1];
        for (int i = 1; i < dp.length; i++) {
            int l = 0;
            int r = i - 1;
            int res = -1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (jobs[m].endTime <= jobs[i - 1].startTime) {
                    res = m;
                    l = m+1;
                } else {
                    r = m -1;
                }
            }
            res++;
            dp[i] = Math.max(dp[i-1], dp[res] + jobs[i - 1].profit);
        }
        return dp[dp.length - 1];
    }
}