package com.greenhand.solution;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode测试类
 */
@Slf4j
public class LeetCode {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException {
        Date date = new Date(new java.util.Date().getTime());
        LocalDate.from(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()));
        Solution solution = new Solution();
        Method method = solution.getClass().getMethods()[0];
        long start = System.currentTimeMillis();


        log.info(String.valueOf(
                method.invoke(solution,
                        "ilovecodingonleetcode",
                        "code"
                )
        ));

        log.info("cast {}ms", System.currentTimeMillis() - start);
    }
}
