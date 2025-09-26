package org.syh.demo.java.vanilla;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.syh.demo.java.vanilla.threads.ThreadPoolInitializer;
import org.syh.demo.java.vanilla.context.UserContext;
import org.syh.demo.java.vanilla.processors.BaseProcessor;
import org.syh.demo.java.vanilla.processors.UserIdProcessor;
import org.syh.demo.java.vanilla.processors.UserRoleProcessor;
import org.syh.demo.java.vanilla.processors.UserSummaryProcessor;

public class App {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = ThreadPoolInitializer.newPool();
        List<BaseProcessor> processors = List.of(
            new UserIdProcessor(),
            new UserRoleProcessor(),
            new UserSummaryProcessor()
        );

        int numThreads = 9;
        IntStream.rangeClosed(1, numThreads).forEach(i -> {
            String userId = "user-" + i;
            String userRole = (i % 3 == 1) ? "admin" : "customer";
            executor.submit(() -> {
                UserContext.get().setUserId(userId);
                UserContext.get().setUserRole(userRole);

                processors.forEach(BaseProcessor::process);
            });
        });

        executor.shutdown();
        try {
            boolean finished = executor.awaitTermination(42, TimeUnit.SECONDS);

            if (!finished) {
                System.err.println("Some tasks did not finish within the timeout.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Thread pool was interrupted: " + e.getMessage());
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
