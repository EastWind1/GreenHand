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
                (Object)new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4,2

        );
        watch.stop();
        log.info(String.valueOf(result));
        log.info("cast {}ms", watch.getTotalTimeMillis());

    }
}
