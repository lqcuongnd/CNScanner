package com.lqcuongnd.cnscanner.Models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaoCao implements Serializable {

    private String ma;
    private String thoiGian;
    private String tenPhong;
    private String maThietBi;
    private String maND;
    private String loi;
    private String chiTiet;
    private String trangThai;
    private String chuThich;
    private String maKTV;

    public BaoCao() {
    }

    public BaoCao(String ma, String thoiGian, String tenPhong, String maThietBi, String maND, String loi, String chiTiet, String trangThai, String chuThich, String maKTV) {
        this.ma = ma;
        this.thoiGian = thoiGian;
        this.tenPhong = tenPhong;
        this.maThietBi = maThietBi;
        this.maND = maND;
        this.loi = loi;
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

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
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

    public String getLoi() {
        return loi;
    }

    public void setLoi(String loi) {
        this.loi = loi;
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

    @Override
    public String toString() {
        return "BaoCao{" +
                "ma='" + ma + '\'' +
                ", thoiGian='" + thoiGian + '\'' +
                ", tenPhong='" + tenPhong + '\'' +
                ", maThietBi='" + maThietBi + '\'' +
                ", maND='" + maND + '\'' +
                ", loi='" + loi + '\'' +
                ", chiTiet='" + chiTiet + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", chuThich='" + chuThich + '\'' +
                ", maKTV='" + maKTV + '\'' +
                '}';
    }

    public Map<String, String> getMap(){

        Map<String, String> data = new HashMap<>();

        data.put("Ma", ma);
        data.put("ThoiGian", thoiGian);
        data.put("TenPhong", tenPhong);
        data.put("MaThietBi", maThietBi);
        data.put("MaND", maND);
        data.put("Loi", loi);
        data.put("ChiTiet", chiTiet);
        data.put("TrangThai", trangThai);
        data.put("ChuThich", chuThich);
        data.put("MaKTV", maKTV);

        return data;
    }

    public Date getDate() throws ParseException {
        String[] ss = thoiGian.split(" "); //tách bỏ giờ
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        return sdf.parse(ss[0]);
    }

    public int compareByDateTo(BaoCao baoCao) {
        if (getThoiGian() == null || baoCao.getThoiGian() == null) {
            return 0;
        }
        return getThoiGian().compareTo(baoCao.getThoiGian());
    }
}
