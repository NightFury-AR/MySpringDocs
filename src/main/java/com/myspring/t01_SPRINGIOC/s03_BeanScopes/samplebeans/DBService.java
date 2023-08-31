package com.myspring.t01_SPRINGIOC.s03_BeanScopes.samplebeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DBService {

    @Autowired
    private DBConnection connection;

    public DBConnection getConnection() {
        return connection;
    }

    public void setConnection(DBConnection connection) {
        this.connection = connection;
    }

    public void establishConnection() {
        System.out.println(" Establishing connection from : "+this);
        this.connection.connect();
    }
}
