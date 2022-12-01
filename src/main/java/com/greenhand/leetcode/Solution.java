package com.greenhand.leetcode;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;

class Solution {
    public int minimumSwap(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }
        int xy = 0, yx = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            if (s1.charAt(i) == 'x') {
                xy++;
            } else {
                yx++;
            }
        }
        return ((xy + yx) & 1) == 1 ? -1 : ((xy & 1) == 1 ? xy / 2 + yx / 2 + 2 : (xy + yx) / 2);
    }
}