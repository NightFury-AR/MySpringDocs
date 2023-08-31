package com.myspring.t01_SPRINGIOC.s04_CustomizeBeanNature.t01_lifecycle.awareinterfaces;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class WithAppCxtAware implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.getBean("SomeBean");
        // like we can use app context's useful methods here
    }
}
