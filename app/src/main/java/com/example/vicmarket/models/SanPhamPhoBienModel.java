package com.example.vicmarket.models;

public class SanPhamPhoBienModel {
    String title, description,img_url,type;

    public SanPhamPhoBienModel() {
    }

    public SanPhamPhoBienModel(String title, String description, String img_url, String type) {
        this.title = title;
        this.description = description;
        this.img_url = img_url;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
