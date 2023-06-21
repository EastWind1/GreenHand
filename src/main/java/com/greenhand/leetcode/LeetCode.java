package com.greenhand.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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
                        (Object)new TreeNode(new Integer[]{1953828,5321214,35213,45213,5512,null,41436,53157,13568,null, null, 413419,null, null, 153210,154261})

                )
        ));
        log.info("cast {}ms", System.currentTimeMillis() - start);
    }

}
