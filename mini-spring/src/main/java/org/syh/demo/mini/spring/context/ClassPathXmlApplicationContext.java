package org.syh.demo.mini.spring.context;

import org.syh.demo.mini.spring.beans.exceptions.BeansException;
import org.syh.demo.mini.spring.beans.factory.BeanFactory;
import org.syh.demo.mini.spring.beans.factory.SimpleBeanFactory;
import org.syh.demo.mini.spring.beans.factory.config.BeanDefinition;
import org.syh.demo.mini.spring.beans.factory.xml.XmlBeanDefinitionReader;
import org.syh.demo.mini.spring.core.ClassPathXmlResource;
import org.syh.demo.mini.spring.core.Resource;

public class ClassPathXmlApplicationContext implements BeanFactory {
    private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String configLocation) {
        Resource resource = new ClassPathXmlResource(configLocation);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }
}
