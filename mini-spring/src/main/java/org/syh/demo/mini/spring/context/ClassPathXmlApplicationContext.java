package org.syh.demo.mini.spring.context;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.syh.demo.mini.spring.beans.factory.config.BeanDefinition;

public class ClassPathXmlApplicationContext {
    private List<BeanDefinition> beanDefinitions;
    private Map<String, Object> singletons;

    public ClassPathXmlApplicationContext(String configLocation) {
        readXml(configLocation);
        instanceBeans();
    }

    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();

            for (Element element : (List<Element>) rootElement.elements()) {
                String beanID = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
                beanDefinitions.add(beanDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                singletons.put(
                    beanDefinition.getId(),
                    Class.forName(beanDefinition.getClassName()).getDeclaredConstructor().newInstance()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }

    public Object getBean(String beamName) {
        return singletons.get(beamName);
    }
}
