package com.example.exbeginner.domain;

public class Item {
    String name;
    Integer price;
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
    @Override
    public String toString() {
        return "Item [name=" + name + ", price=" + price + "]";
    }
    
}
