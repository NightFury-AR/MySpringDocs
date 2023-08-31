package com.myspring.t01_SPRINGIOC.s05_BeanInheritance;

import org.springframework.stereotype.Component;

@Component
public abstract class ParentBeanAnnotated {
    public abstract void greetSomeone();
    public void greetEveryone() {
        System.out.println(" hello all , have a nice day !!!");
    }
}
