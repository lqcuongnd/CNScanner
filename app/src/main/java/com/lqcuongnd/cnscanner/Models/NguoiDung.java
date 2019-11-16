package com.lqcuongnd.cnscanner.Models;

import android.os.Bundle;
import java.io.Serializable;
import java.util.ArrayList;

public class NguoiDung implements Serializable {

    private String  tenDN;
    private String  matKhau;
    private String  ten;
    private boolean gioiTinh;
    private String  id;
    private int     loai;
    private boolean kichHoat;
    private String  documentID;

    public NguoiDung() {
    }

    public NguoiDung(String tenDN, String matKhau, String ten, boolean gioiTinh, String id, int loai, boolean kichHoat) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.id = id;
        this.loai = loai;
        this.kichHoat = kichHoat;
    }

    public NguoiDung(NguoiDung user) {
        this.tenDN = user.getTenDN();
        this.matKhau = user.getMatKhau();
        this.ten = user.getTen();
        this.gioiTinh = user.getGioiTinh();
        this.id = user.getId();
        this.loai = user.getLoai();
        this.kichHoat = user.getKichHoat();
    }

    public NguoiDung(ArrayList<String> user) {
        this.tenDN = user.get(0);
        this.matKhau = user.get(0);
        this.ten = user.get(0);
        this.gioiTinh = Boolean.parseBoolean(user.get(0));
        this.id = user.get(0);
        this.loai = Integer.parseInt(user.get(0));
        this.kichHoat = Boolean.parseBoolean(user.get(0));
    }

    public NguoiDung(Bundle bundle) {

        this.tenDN = bundle.getString("tenDN", tenDN);
        this.matKhau = bundle.getString("matKhau", matKhau);
        this.ten = bundle.getString("ten", ten);
        this.gioiTinh = bundle.getBoolean("gioiTinh", gioiTinh);
        this.id = bundle.getString("id", id);
        this.loai = bundle.getInt("loai", loai);
        this.kichHoat = bundle.getBoolean("kichHoat", kichHoat);
        documentID = bundle.getString("documentID", documentID);
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

    public String getOnlyTen() {
        String[] abc = this.ten.split(" ");
        return abc[abc.length - 1];
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean getGioiTinh() {
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

    public boolean getKichHoat() {
        return kichHoat;
    }

    public void setKichHoat(boolean kichHoat) {
        this.kichHoat = kichHoat;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "tenDN='" + tenDN + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", ten='" + ten + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", id='" + id + '\'' +
                ", loai=" + loai +
                ", kichHoat=" + kichHoat +
                '}';
    }

    public ArrayList<String> getArray() {

        ArrayList<String> a = new ArrayList<String>();
        a.add(tenDN);
        a.add(matKhau);
        a.add(ten);
        a.add(Boolean.toString(gioiTinh));
        a.add(id);
        a.add(Integer.toString(loai));
        a.add(Boolean.toString(kichHoat));

        return a;
    }

    public Bundle getBundle() {

        Bundle bundle = new Bundle();

        bundle.putString("tenDN", tenDN);
        bundle.putString("matKhau", matKhau);
        bundle.putString("ten", ten);
        bundle.putBoolean("gioiTinh", gioiTinh);
        bundle.putString("id", id);
        bundle.putInt("loai", loai);
        bundle.putBoolean("kichHoat", kichHoat);
        bundle.putString("documentID", documentID);

        return bundle;
    }

}
