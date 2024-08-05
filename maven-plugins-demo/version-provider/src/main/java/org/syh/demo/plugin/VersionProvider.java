package org.syh.demo.plugin;

import org.apache.maven.plugin.MojoExecutionException;

public interface VersionProvider {
    String getVersion(String command) throws MojoExecutionException;
}
