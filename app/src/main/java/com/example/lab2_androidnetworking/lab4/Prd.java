package com.example.lab2_androidnetworking.lab4;

public class Prd {
private String name, price, drc;

    public Prd() {
    }

    public Prd(String name, String price, String drc) {
        this.name = name;
        this.price = price;
        this.drc = drc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDrc() {
        return drc;
    }

    public void setDrc(String drc) {
        this.drc = drc;
    }
}
