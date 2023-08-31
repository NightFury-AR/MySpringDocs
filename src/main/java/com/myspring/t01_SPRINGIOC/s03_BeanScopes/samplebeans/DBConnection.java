package com.myspring.t01_SPRINGIOC.s03_BeanScopes.samplebeans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE , proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DBConnection {

    public void connect() {
        System.out.println(" connecting with node "+this);
    }
}
