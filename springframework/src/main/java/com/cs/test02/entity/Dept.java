package com.cs.test02.entity;

/**
 * 部门
 */
public class Dept {

    private String dName;

    public void setDName(String dName) {
        this.dName = dName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dName='" + dName + '\'' +
                '}';
    }
}
