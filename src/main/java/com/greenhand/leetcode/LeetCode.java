package com.greenhand.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * Leetcode测试类
 */
@Slf4j
public class LeetCode {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        Method method = solution.getClass().getMethods()[0];
        long start = System.currentTimeMillis();

        log.info(String.valueOf(
                method.invoke(solution,
                        (Object) "abcabcbb"
                      )));
        log.info("cast {}ms", System.currentTimeMillis() - start);
    }
}
