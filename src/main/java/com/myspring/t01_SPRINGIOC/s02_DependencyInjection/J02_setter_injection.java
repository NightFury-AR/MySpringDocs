package com.myspring.t01_SPRINGIOC.s02_DependencyInjection;

import com.myspring.t01_SPRINGIOC.s02_DependencyInjection.samplebeans.IceCream;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class J02_setter_injection {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("setterInjection.xml");
        IceCream iceCream = applicationContext.getBean("iceCream",IceCream.class);
        iceCream.buyIceCream();
    }
}
