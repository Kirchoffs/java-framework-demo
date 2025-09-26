package org.syh.demo.java.vanilla.threads;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.syh.demo.java.vanilla.context.ThreadStatisticsContext;

public class ThreadPoolInitializer {
    private static void initOncePerThread() {
        System.out.println("Thread " + Thread.currentThread().getName() + " initializing thread-local contexts.");
        new ThreadStatisticsContext();
    }

    public static ThreadPoolExecutor newPool() {
        AtomicInteger idx = new AtomicInteger(1);

        ThreadFactory threadFactory = (Runnable target) -> {
            Thread thread = new Thread(() -> {
                initOncePerThread();
                target.run();
            }, "thread-" + idx.getAndIncrement());

            thread.setDaemon(false);
            thread.setUncaughtExceptionHandler((th, ex) -> {
                System.err.println("Uncaught exception in thread " + th.getName() + ": " + ex.getMessage());
                ex.printStackTrace();
            });

            return thread;
        };

        return new ContextManagedThreadPool(
            3,
            new LinkedBlockingQueue<>(),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
