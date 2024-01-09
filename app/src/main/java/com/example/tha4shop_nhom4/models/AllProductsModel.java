package com.example.tha4shop_nhom4.models;

import java.io.Serializable;

public class AllProductsModel implements Serializable {


    String description;
    String name;
    String img_url;
    String rating;
    int price;

    String type;
String documentId;
    public AllProductsModel() {
    }

    public AllProductsModel( String description, String name, String img_url, String rating, int price, String type) {
        this.description = description;
        this.name = name;
        this.img_url = img_url;
        this.rating = rating;
        this.price = price;
        this.type = type;
    }


    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
