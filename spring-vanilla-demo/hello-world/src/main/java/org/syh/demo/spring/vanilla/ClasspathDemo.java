package org.syh.demo.spring.vanilla;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.InputStream;
import java.util.Properties;

public class ClasspathDemo {
    public static void main(String[] args) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        System.out.println("Using classpath:");
        Resource[] resourceOfClasspath = resolver.getResources("classpath:config-*.properties");
        for (Resource resource : resourceOfClasspath) {
            printProperties(resource);
        }
        System.out.println();
        
        System.out.println("Using classpath*:");
        Resource[] resourcesOfClasspathStar = resolver.getResources("classpath*:config-*.properties");
        for (Resource resource : resourcesOfClasspathStar) {
            printProperties(resource);
        }
    }

    private static void printProperties(Resource resource) throws Exception {
        if (resource.exists()) {
            try (InputStream is = resource.getInputStream()) {
                Properties props = new Properties();
                props.load(is);
                System.out.println("Loaded from: " + resource.getURI());
                props.forEach((k, v) -> System.out.println(k + ": " + v));
            }
        } else {
            System.out.println("Resource not found: " + resource.getDescription());
        }
    }
}
