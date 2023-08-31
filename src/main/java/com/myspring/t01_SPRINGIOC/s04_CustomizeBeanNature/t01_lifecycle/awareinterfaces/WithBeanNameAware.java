package com.myspring.t01_SPRINGIOC.s04_CustomizeBeanNature.t01_lifecycle.awareinterfaces;

import org.springframework.beans.factory.BeanNameAware;

public class WithBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        System.out.println(" bean name "+name);
    }
}
