package com.myspring.t01_SPRINGIOC.s05_BeanInheritance;

public class ChildBeanXml extends ParentBeanXml{
    @Override
    public void greet() {
        super.greetEveryone();
        System.out.println(" hey hey hey !!!!");
    }
}
