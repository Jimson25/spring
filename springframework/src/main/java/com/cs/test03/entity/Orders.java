package com.cs.test03.entity;

public class Orders {

    private String name;

    public Orders() {
        System.out.println("Orders.Orders");
    }

    public void initMethod(){
        System.out.println("Orders.initMethod");
    }

    public void destroyMethod(){
        System.out.println("Orders.destroyMethod");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Orders.setName");
    }
}
