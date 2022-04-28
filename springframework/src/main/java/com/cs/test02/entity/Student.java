package com.cs.test02.entity;

public class Student {
    private Class aClass;

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "aClass=" + aClass +
                '}';
    }
}
