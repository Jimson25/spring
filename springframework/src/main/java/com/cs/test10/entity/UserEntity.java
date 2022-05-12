package com.cs.test10.entity;

public class UserEntity {
    private String name;
    private String addr;

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
