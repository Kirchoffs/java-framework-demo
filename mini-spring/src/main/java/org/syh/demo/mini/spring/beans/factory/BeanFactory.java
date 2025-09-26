package org.syh.demo.mini.spring.beans.factory;

import org.syh.demo.mini.spring.beans.exceptions.BeansException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;
    Boolean containsBean(String beanName);
    void registerBean(String beanName, Object bean);
}
