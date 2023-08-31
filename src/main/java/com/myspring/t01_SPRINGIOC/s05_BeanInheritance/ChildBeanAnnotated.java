package com.myspring.t01_SPRINGIOC.s05_BeanInheritance;

import org.springframework.stereotype.Component;

@Component
public class ChildBeanAnnotated extends ParentBeanAnnotated{

    @Override
    public void greetSomeone() {
        super.greetEveryone();
        System.out.println(" greeting from child");
    }
}
