package org.syh.demo.java.vanilla.context;

public class ThreadStatisticsContext {
    private static final ThreadLocal<ThreadStatisticsContext> threadStatisticsContextThreadLocal = new ThreadLocal<>();

    private int processedTaskCount;


    public ThreadStatisticsContext() {
        threadStatisticsContextThreadLocal.set(this);
        this.processedTaskCount = 0;
    }

    public static ThreadStatisticsContext get() {
        return threadStatisticsContextThreadLocal.get();
    }

    public static void clear() {
        threadStatisticsContextThreadLocal.remove();
    }

    public int getProcessedTaskCount() {
        return processedTaskCount;
    }

    public void incrementProcessedTaskCount() {
        this.processedTaskCount++;
    }
}
