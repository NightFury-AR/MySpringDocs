package com.myspring.t01_SPRINGIOC.s02_DependencyInjection.samplebeans;

public class Toppings {

    private String toppingName;

    public Toppings() {
    }

    public Toppings(String toppingName) {
        this.toppingName = toppingName;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }
}
