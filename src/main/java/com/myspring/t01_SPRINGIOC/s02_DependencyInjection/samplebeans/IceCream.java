package com.myspring.t01_SPRINGIOC.s02_DependencyInjection.samplebeans;

public class IceCream {

    private Toppings toppings;

    public IceCream() {
    }

    public IceCream(Toppings toppings) {
        this.toppings = toppings;
    }

    public Toppings getToppings() {
        return toppings;
    }

    public void setToppings(Toppings toppings) {
        this.toppings = toppings;
    }

    public void buyIceCream() {
        System.out.println("Ice Cream with "+this.toppings.getToppingName()+" topping");
    }

}
