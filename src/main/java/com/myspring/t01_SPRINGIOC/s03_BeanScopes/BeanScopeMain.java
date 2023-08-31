package com.myspring.t01_SPRINGIOC.s03_BeanScopes;

import com.myspring.t01_SPRINGIOC.s03_BeanScopes.samplebeans.DBService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan("com.myspring.t01_SPRINGIOC.s03_BeanScopes")
public class BeanScopeMain {
    public static void main(String[] args) {

        //using annotated config
        ApplicationContext sc1 = new AnnotationConfigApplicationContext(BeanScopeMain.class);
        DBService dbService1 = sc1.getBean(DBService.class);
        dbService1.establishConnection();
        dbService1.establishConnection();
        System.out.println("======================================================================");
        //using xml config : see previous section for Lookup Method Injection

    }
}
