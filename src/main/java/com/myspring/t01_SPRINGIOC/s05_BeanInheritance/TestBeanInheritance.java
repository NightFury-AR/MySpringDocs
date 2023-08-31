package com.myspring.t01_SPRINGIOC.s05_BeanInheritance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan("com.myspring.t01_SPRINGIOC.s05_BeanInheritance")
public class TestBeanInheritance {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestBeanInheritance.class);
        ChildBeanAnnotated bean = context.getBean(ChildBeanAnnotated.class);
        bean.greetSomeone();

        ApplicationContext context1 = new ClassPathXmlApplicationContext("beanInheritance.xml");
        ChildBeanXml childGreet = context1.getBean("childGreet", ChildBeanXml.class);
        childGreet.greet();
    }
}
