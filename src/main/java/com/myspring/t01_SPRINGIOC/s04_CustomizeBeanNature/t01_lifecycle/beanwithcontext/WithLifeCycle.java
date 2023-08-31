package com.myspring.t01_SPRINGIOC.s04_CustomizeBeanNature.t01_lifecycle.beanwithcontext;

import org.springframework.context.Lifecycle;

public class WithLifeCycle implements Lifecycle {
    @Override
    public void start() {
        System.out.println(" executed when app context starts ");
    }

    @Override
    public void stop() {
        System.out.println(" executes when app context stops ");
    }

    @Override
    public boolean isRunning() {
        //return the current status
        return false;
    }
}
