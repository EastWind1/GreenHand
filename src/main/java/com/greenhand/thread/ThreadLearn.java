package com.greenhand.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ThreadLearn {
    private static final int processors = Runtime.getRuntime().availableProcessors();
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final Lock lock = new ReentrantLock();
    private static final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final CountDownLatch countDownLatch = new CountDownLatch(3);
    private static final BlockingQueue<Integer> blockQueue = new ArrayBlockingQueue<>(3);
    private static final ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(processors + 1);

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.getClass().arrayType();
        log.info("CPU processors: {}. Max memory: {}MB", processors, Runtime.getRuntime().maxMemory() / 1024 / 1024);
        threadPool.scheduleAtFixedRate(new TestThread(), 0,1, TimeUnit.SECONDS);
        long startNano = System.currentTimeMillis();
        countDownLatch.await();
        log.info("cast {}ms", System.currentTimeMillis() - startNano);
        log.info(String.valueOf(count));
    }

    static class TestThread implements Runnable {
        @Override
        public void run() {
            log.info(String.valueOf(System.currentTimeMillis()));
        }
    }
}
