package org.syh.demo.java.vanilla.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.syh.demo.java.vanilla.context.ThreadStatisticsContext;
import org.syh.demo.java.vanilla.context.UserContext;

public class ContextManagedThreadPool extends ThreadPoolExecutor {
    public ContextManagedThreadPool(int poolSize, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        new UserContext();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        UserContext.clear();

        ThreadStatisticsContext.get().incrementProcessedTaskCount();
        System.out.println("Thread " + Thread.currentThread().getName() + " has processed " + ThreadStatisticsContext.get().getProcessedTaskCount() + " tasks so far.");
    }
}
