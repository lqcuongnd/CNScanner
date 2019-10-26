package com.lqcuongnd.cnscanner.Models;

import net.sourceforge.jtds.jdbc.DateTime;

public class BaoCao {
    String ma;
    DateTime thoiGian;
    String maPhong;
    String maThietBi;
    String maND;
    String maLoi;
    String chiTiet;
    String trangThai;
    String chuThich;
    String maKTV;

    public BaoCao() {
    }

    public BaoCao(String ma, DateTime thoiGian, String maPhong, String maThietBi, String maND, String maLoi, String chiTiet, String trangThai, String chuThich, String maKTV) {
        this.ma = ma;
        this.thoiGian = thoiGian;
        this.maPhong = maPhong;
        this.maThietBi = maThietBi;
        this.maND = maND;
        this.maLoi = maLoi;
        this.chiTiet = chiTiet;
        this.trangThai = trangThai;
        this.chuThich = chuThich;
        this.maKTV = maKTV;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public DateTime getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(DateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaThietBi() {
        return maThietBi;
    }

    public void setMaThietBi(String maThietBi) {
        this.maThietBi = maThietBi;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    public String getMaKTV() {
        return maKTV;
    }

    public void setMaKTV(String maKTV) {
        this.maKTV = maKTV;
    }
}
