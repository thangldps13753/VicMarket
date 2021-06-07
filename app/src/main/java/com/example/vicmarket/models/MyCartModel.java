package com.example.vicmarket.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {
    String title,price,date,time,totalSoLuong;
    int totalTongTien;

    public MyCartModel() {
    }

    public MyCartModel(String title, String price, String date, String time, String totalSoLuong, int totalTongTien) {
        this.title = title;
        this.price = price;
        this.date = date;
        this.time = time;
        this.totalSoLuong = totalSoLuong;
        this.totalTongTien = totalTongTien;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalSoLuong() {
        return totalSoLuong;
    }

    public void setTotalSoLuong(String totalSoLuong) {
        this.totalSoLuong = totalSoLuong;
    }

    public int getTotalTongTien() {
        return totalTongTien;
    }

    public void setTotalTongTien(int totalTongTien) {
        this.totalTongTien = totalTongTien;
    }
}
