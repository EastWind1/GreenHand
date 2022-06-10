package com.greenhand.solution;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.*;

class Solution {
    public boolean isStraight(int[] nums) {
        int king = 0;
        Arrays.sort(nums);
        int pre = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                king++;
            } else {
                if (pre == -1) {
                    pre = nums[i];
                } else {
                    if (nums[i] != pre + 1) {
                        if (king == 0) {
                            return false;
                        } else {
                            pre = pre + 1;
                            i--;
                            king--;
                        }
                    } else {
                        pre = nums[i];
                    }
                }
            }
        }
        return true;
    }
}