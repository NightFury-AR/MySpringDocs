package com.myspring.t01_SPRINGIOC.s04_CustomizeBeanNature.t01_lifecycle.beanwithcontext;

import org.springframework.context.LifecycleProcessor;

public class WithLifeCycleProcessor implements LifecycleProcessor {
    @Override
    public void onRefresh() {
        System.out.println("execute when app context refresh");
    }

    @Override
    public void onClose() {
        System.out.println("execute when app context close");
    }

    @Override
    public void start() {
        System.out.println("execute when app context starts");
    }

    @Override
    public void stop() {
        System.out.println("execute when app context stops");
    }

    @Override
    public boolean isRunning() {
        //return the current status
        return false;
    }
}
