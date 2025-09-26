package org.syh.demo.mini.spring.beans.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.syh.demo.mini.spring.beans.exceptions.BeansException;
import org.syh.demo.mini.spring.beans.factory.config.BeanDefinition;

public class SimpleBeanFactory implements BeanFactory {
    private List<BeanDefinition> beanDefinitions;
    private List<String> beanNames;
    private Map<String, Object> singletons;

    public SimpleBeanFactory() {
        this.beanDefinitions = new ArrayList<>();
        this.beanNames = new ArrayList<>();
        this.singletons = new HashMap<>();
    }

    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.singletons.get(beanName);
        
        if (singleton == null) {
            int beanDefinitionIndex = this.beanNames.indexOf(beanName);
            
            if (beanDefinitionIndex == -1) {
                throw new BeansException("No such bean definition with name: " + beanName);
            }

            BeanDefinition beanDefinition = this.beanDefinitions.get(beanDefinitionIndex);
            try {
                singleton = Class.forName(beanDefinition.getClassName()).getDeclaredConstructor().newInstance();
                this.singletons.put(beanName, singleton);
            } catch (Exception e) {
                throw new BeansException("Failed to create bean with name: " + beanName + ", error: " + e.getMessage());
            }
        }

        return singleton;
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}
