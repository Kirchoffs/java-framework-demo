package org.syh.demo.mini.spring.beans.factory.config;

import lombok.Data;

@Data
public class BeanDefinition {
    private String id;
    private String className;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }
}
