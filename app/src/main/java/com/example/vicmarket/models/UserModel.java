package com.example.vicmarket.models;

public class UserModel {
    String ten;
    String email;
    String diaChi;
    String password;
    String sdt;

    public UserModel() {
    }

    public UserModel(String ten, String email, String diaChi, String password, String sdt) {
        this.ten = ten;
        this.email = email;
        this.diaChi = diaChi;
        this.password = password;
        this.sdt = sdt;
    }


    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}


