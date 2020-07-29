package com.visumit.businessboost.model;

public class Product {
    private int id;
    private String name;
    private float price;
    private float discount;
    private String brand;
    private float discontPrice;
    private float totalPrice;
    private String category;
    private String reference;
    private String deliveryTime;
    private String imagesUrl;
    private float sold;
    private String status;
    ProductInformation ProductInformationObject;


    // Getter Methods

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public String getBrand() {
        return brand;
    }

    public float getDiscontPrice() {
        return discontPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getCategory() {
        return category;
    }

    public String getReference() {
        return reference;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public float getSold() {
        return sold;
    }

    public String getStatus() {
        return status;
    }

    public ProductInformation getProductInformation() {
        return ProductInformationObject;
    }

    // Setter Methods

    public void setId( int id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setPrice( float price ) {
        this.price = price;
    }

    public void setDiscount( float discount ) {
        this.discount = discount;
    }

    public void setBrand( String brand ) {
        this.brand = brand;
    }

    public void setDiscontPrice( float discontPrice ) {
        this.discontPrice = discontPrice;
    }

    public void setTotalPrice( float totalPrice ) {
        this.totalPrice = totalPrice;
    }

    public void setCategory( String category ) {
        this.category = category;
    }

    public void setReference( String reference ) {
        this.reference = reference;
    }

    public void setDeliveryTime( String deliveryTime ) {
        this.deliveryTime = deliveryTime;
    }

    public void setImagesUrl( String imagesUrl ) {
        this.imagesUrl = imagesUrl;
    }

    public void setSold( float sold ) {
        this.sold = sold;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public void setProductInformation( ProductInformation productInformationObject ) {
        this.ProductInformationObject = productInformationObject;
    }
}
class ProductInformation {
    private int id;
    private float stock;
    private String size;
    private String color;


    // Getter Methods

    public int getId() {
        return id;
    }

    public float getStock() {
        return stock;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    // Setter Methods

    public void setId( int id ) {
        this.id = id;
    }

    public void setStock( float stock ) {
        this.stock = stock;
    }

    public void setSize( String size ) {
        this.size = size;
    }

    public void setColor( String color ) {
        this.color = color;
    }
}