package org.syh.demo.mini.spring.beans.factory.xml;

import org.dom4j.Element;
import org.syh.demo.mini.spring.beans.factory.BeanFactory;
import org.syh.demo.mini.spring.beans.factory.config.BeanDefinition;
import org.syh.demo.mini.spring.core.Resource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class XmlBeanDefinitionReader {
    private final BeanFactory beanFactory;

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();

            String beanId = element.attributeValue("id");
            String beanClass = element.attributeValue("class");

            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClass);
            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
