package org.syh.demo.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.maven.plugin.MojoExecutionException;

@Named
@Singleton
public class RuntimeExecVersionProvider implements VersionProvider {
    public String getVersion(String command) throws MojoExecutionException {
        try {
            StringBuilder sb = new StringBuilder();

            Process process = Runtime.getRuntime().exec(command);

            ExecutorService pool = Executors.newSingleThreadExecutor();
            pool.submit(() -> {
                new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(sb::append);
            });
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new MojoExecutionException("Execution of command '" + command + "' failed with exit code: " + exitCode);
            }

            return sb.toString();
        } catch (IOException | InterruptedException e) {
            throw new MojoExecutionException("Execution of command '" + command + "' failed", e);
        }
    }
}
