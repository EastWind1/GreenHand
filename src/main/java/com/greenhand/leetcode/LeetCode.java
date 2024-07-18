package com.greenhand.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Leetcode测试类
 */
@Slf4j
public class LeetCode {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        Method method = solution.getClass().getMethods()[0];
        StopWatch watch = new StopWatch();
        watch.start();
        Object result =  method.invoke(solution,
                (Object) 3,new int[][]{{0,1,2},{1,2,1},{0,2,4}}, new int[] {1,1,5}

        );
        watch.stop();
        log.info(String.valueOf(result));
        log.info("cast {}ms", watch.getTotalTimeMillis());

    }
}
