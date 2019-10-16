package com.lqcuongnd.cnscanner.Models;

import java.io.Serializable;

public class User implements Serializable {

    private String tenDN;
    private String matKhau;
    private String ten;
    private boolean gioiTinh;
    private String id;
    private int loai;
    private boolean kichHoat;

    public User() {
    }

    public User(String tenDN, String matKhau, String ten, boolean gioiTinh, String id, int loai, boolean kichHoat) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.id = id;
        this.loai = loai;
        this.kichHoat = kichHoat;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public boolean isKichHoat() {
        return kichHoat;
    }

    public void setKichHoat(boolean kichHoat) {
        this.kichHoat = kichHoat;
    }

    @Override
    public String toString() {
        return "User{" +
                "tenDN='" + tenDN + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", ten='" + ten + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", id='" + id + '\'' +
                ", loai=" + loai +
                ", kichHoat=" + kichHoat +
                '}';
    }

}
