package com.example.vicmarket.models;

public class NavTheLoaiModel {
    String title,img_url,type;

    public NavTheLoaiModel() {
    }

    public NavTheLoaiModel(String title, String img_url, String type) {
        this.title = title;
        this.img_url = img_url;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
