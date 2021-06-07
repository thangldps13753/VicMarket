package com.example.vicmarket.models;

public class TrangChuTheLoaiModel {
    String title,type,img_url;

    public TrangChuTheLoaiModel() {
    }

    public TrangChuTheLoaiModel(String title, String type, String img_url) {
        this.title = title;
        this.type = type;
        this.img_url = img_url;
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
}
