package com.myspring.t01_SPRINGIOC.s02_DependencyInjection.samplebeans;

import java.util.Random;

public class Token {
    private int token;
    public Token() {
        this.token = new Random().nextInt(10000);
    }

    public int getToken() {
        return token;
    }
}
