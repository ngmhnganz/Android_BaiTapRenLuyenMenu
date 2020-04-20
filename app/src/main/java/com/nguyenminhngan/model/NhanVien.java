package com.nguyenminhngan.model;

import android.widget.ImageView;

public class NhanVien {
    private String ten;
    private String ma;
    private boolean laNu;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public boolean getGioitinh() {
        return laNu;
    }

    public void setGioitinh(boolean gioitinh) {
        this.laNu = gioitinh;
    }

    public NhanVien(String ten, String ma, boolean laNu) {
        this.ten = ten;
        this.ma = ma;
        this.laNu = laNu;
    }

    public NhanVien() {
    }
}
