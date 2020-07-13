package com.visumit.businessboost.model;

public class ProductInformation {

    private int id;
    private int stock;
    private String size;
    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ProductInformation{" +
                "id=" + id +
                ", stock=" + stock +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public ProductInformation(int id, int stock, String size, String color) {
        this.id = id;
        this.stock = stock;
        this.size = size;
        this.color = color;
    }
}
