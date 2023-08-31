package com.myspring.t01_SPRINGIOC.s04_CustomizeBeanNature.t01_lifecycle.beanmanaged;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class WithDisposableBean implements DisposableBean {

    /*
     * equal xml config (destroy-something is normal java method) :
     * <bean id="withInitializeBean"
     * class="com.myspring.t01_SPRINGIOC.s04_CustomizeBeanNature.t01_lifecycle.beanmanaged.WithDisposableBean"
     * destroy-method="destroy-something"/>
     * */
    @Override
    public void destroy() throws Exception {
        System.out.println("call this block when container destroyed !");
    }
}
