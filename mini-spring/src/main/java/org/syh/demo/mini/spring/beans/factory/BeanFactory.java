package org.syh.demo.mini.spring.beans.factory;

import org.syh.demo.mini.spring.beans.exceptions.BeansException;
import org.syh.demo.mini.spring.beans.factory.config.BeanDefinition;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;
    void registerBeanDefinition(BeanDefinition beanDefinition);
}
