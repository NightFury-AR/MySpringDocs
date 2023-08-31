package com.myspring.t01_SPRINGIOC.s02_DependencyInjection.samplebeans;

import java.util.Date;

public abstract class TokenMachine {
    public abstract Token getNextToken();
    public void getToken() {
        Token t = this.getNextToken();
        System.out.println(" Token :"+t.getToken()+" generated at "+new Date()+" from :"+t);
    }
}
