package com.mall.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String pwd;

    private String address;

    public User(int id, String name, String pwd, String address) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
