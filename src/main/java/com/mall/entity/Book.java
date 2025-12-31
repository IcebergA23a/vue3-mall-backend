package com.mall.entity;

import java.util.Date;

/**
 * @author bingshan
 * @since 2025/12/31 17:27
 */

public class Book {
    private Integer id;
    private String name;
    private Integer price;
    private Date updateTime;

    public Book() {
    }

    public Book(Integer id, String name, Integer price, Date updateTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
