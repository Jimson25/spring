package com.example.springwebflux.test01;

import java.util.Observable;

public class ObserverDemo extends Observable {
    public static void main(String[] args) {
        // 这里的ObserverDemo对象就是被观察者，通过addObserver方法添加观察者
        // 这里通过匿名对象的方式传入一个观察者
        // 我们调用setChanged表示被观察者行为发生了改变，调用notifyObservers将这一行为传递给观察者
        // 如果观察者是具体对象，也可以将行为传递到指定的观察者
        ObserverDemo demo = new ObserverDemo();
        demo.addObserver((o, arg) -> System.out.println("o = " + o + ", arg = " + arg));
        demo.setChanged();
        demo.notifyObservers("aaaaaaaaaaaaaaaaa");
    }
}
