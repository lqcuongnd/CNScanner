package com.lqcuongnd.cnscanner.Models;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lqcuongnd.cnscanner.Activities.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NguoiDung implements Serializable {

    private String tenDN;
    private String matKhau;
    private String ten;
    private boolean gioiTinh;
    private String id;
    private int loai;
    private boolean kichHoat;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Activity activity;

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


    public void set(NguoiDung user) {
        this.tenDN = user.getTenDN();
        this.matKhau = user.getMatKhau();
        this.ten = user.getTen();
        this.gioiTinh = user.getGioiTinh();
        this.id = user.getId();
        this.loai = user.getLoai();
        this.kichHoat = user.getKichHoat();
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

        return bundle;
    }

    public void login(Activity act) {
        this.activity = act;

        db.collection("NGUOIDUNG").whereEqualTo("TenDN", id).whereEqualTo("MatKhau", matKhau).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(activity, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (QueryDocumentSnapshot doc : value) {
                    if (doc.get("Ten") != null) {

                        tenDN = (String) doc.get("TenDN");
                        matKhau = (String) doc.get("MatKhau");
                        ten = (String) doc.get("Ten");
                        gioiTinh = (Boolean) doc.get("GioiTinh");
                        id = (String) doc.get("Id").toString();
                        loai = Integer.parseInt(doc.get("Loai").toString());
                        kichHoat = (Boolean) doc.get("KichHoat");

                        Toast.makeText(activity, "Xin chào " + ten, Toast.LENGTH_SHORT).show();

                        Intent intent = activity.getIntent();
                        /*Bundle bundle = new Bundle();
                        bundle.putSerializable("user", new NguoiDung(tenDN, matKhau, ten, gioiTinh, id, loai, kichHoat));
                        intent.putExtras(bundle);*/
                        activity.setResult(MainActivity.RESULT_LOGIN, intent);
                        activity.finish();
                    }
                }
            }
        });
    }

}
