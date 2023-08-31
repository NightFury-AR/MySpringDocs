package com.myspring.t01_SPRINGIOC.s02_DependencyInjection;

import com.myspring.t01_SPRINGIOC.s02_DependencyInjection.samplebeans.TokenMachine;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class J03_lookup_method_injection {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("lookupinjection.xml");
        TokenMachine tokenMachine = ac.getBean("tokenMachine", TokenMachine.class);
        tokenMachine.getToken();
        System.out.println(" getting next token");
        tokenMachine.getToken();
    }

}
