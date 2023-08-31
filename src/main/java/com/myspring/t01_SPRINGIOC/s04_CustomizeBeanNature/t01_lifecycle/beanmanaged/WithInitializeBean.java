package com.myspring.t01_SPRINGIOC.s04_CustomizeBeanNature.t01_lifecycle.beanmanaged;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class WithInitializeBean implements InitializingBean {

    /*
    * equal xml config (init-something is normal java method) :
    * <bean id="withInitializeBean"
    * class="com.myspring.t01_SPRINGIOC.s04_CustomizeBeanNature.t01_lifecycle.beanmanaged.WithInitializeBean"
    * init-method="init-something"/>
    * */

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("This block is called after all bean properties set by the container");
    }
}
