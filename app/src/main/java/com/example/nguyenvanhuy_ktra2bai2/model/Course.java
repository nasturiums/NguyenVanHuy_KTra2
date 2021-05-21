package com.example.nguyenvanhuy_ktra2bai2.model;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String name;
    private String date;
    private String chuyenNganh;
    private boolean isActivated;

    public Course() {
    }

    public Course(String name, String date, String chuyenNganh, boolean isActivated) {
        this.name = name;
        this.date = date;
        this.chuyenNganh = chuyenNganh;
        this.isActivated = isActivated;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Course(int id, String name, String date, String chuyenNganh, boolean isActivated) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.chuyenNganh = chuyenNganh;
        this.isActivated = isActivated;
    }
}
