package com.visumit.businessboost.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private double discount;
    private String brand;
    private String category;
    private String reference;
    private String deliveryTime;
    private String imagesUrl;
    private int sold;
    private String status;
    ProductInformation ProductInformationObject;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductInformation getProductInformationObject() {
        return ProductInformationObject;
    }

    public void setProductInformationObject(ProductInformation productInformationObject) {
        ProductInformationObject = productInformationObject;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", reference='" + reference + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", imagesUrl='" + imagesUrl + '\'' +
                ", sold=" + sold +
                ", status='" + status + '\'' +
                ", ProductInformationObject=" + ProductInformationObject +
                '}';
    }

    public Product(int id, String name, double price, double discount, String brand, String category, String reference, String deliveryTime, String imagesUrl, int sold, String status, ProductInformation productInformationObject) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.brand = brand;
        this.category = category;
        this.reference = reference;
        this.deliveryTime = deliveryTime;
        this.imagesUrl = imagesUrl;
        this.sold = sold;
        this.status = status;
        ProductInformationObject = productInformationObject;
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
