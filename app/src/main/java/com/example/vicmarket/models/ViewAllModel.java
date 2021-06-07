package com.example.vicmarket.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String title,type,img_url;
    int price;

    public ViewAllModel() {
    }

    public ViewAllModel(String title, String type, String img_url, int price) {
        this.title = title;
        this.type = type;
        this.img_url = img_url;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
