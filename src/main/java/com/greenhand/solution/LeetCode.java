package com.greenhand.solution;


import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
                        (Object) new int[0][0]
                )
        ));
        log.info("cast {}ms", System.currentTimeMillis() - start);
    }
}
