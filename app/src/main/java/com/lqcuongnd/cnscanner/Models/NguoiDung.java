package com.lqcuongnd.cnscanner.Models;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.lqcuongnd.cnscanner.Activities.LoginActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class NguoiDung implements Serializable {

    private String tenDN;
    private String matKhau;
    private String ten;
    private boolean gioiTinh;
    private String id;
    private int loai;
    private boolean kichHoat;
    private String documentID;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        return bundle;
    }

    public void login(LoginActivity contextParent) {

        LoginAsync loginAsync = new LoginAsync(contextParent);
        loginAsync.execute();

    }

    class LoginAsync extends AsyncTask<Void, Integer, Void> {
        LoginActivity contextParent;
        private FirebaseFirestore db = FirebaseFirestore.getInstance();
        private Map<String, Object> u;
        private Boolean isSuccess = false;

        public LoginAsync(LoginActivity contextParent) {
            this.contextParent = contextParent;
        }

        public Boolean getSuccess() {
            return isSuccess;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(contextParent, "Đang kiểm tra thông tin ...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            db.collection("NGUOIDUNG").whereEqualTo("TenDN", id).whereEqualTo("MatKhau", matKhau).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().size() > 0) {
                            DocumentSnapshot document = task.getResult().getDocuments().get(0);

                            u = document.getData();

                            documentID = document.getId();
                            tenDN = (String) u.get("TenDN");
                            matKhau = (String) u.get("MatKhau");
                            ten = (String) u.get("Ten");
                            gioiTinh = (Boolean) u.get("GioiTinh");
                            id = (String) u.get("Id").toString();
                            loai = Integer.parseInt(u.get("Loai").toString());
                            kichHoat = (Boolean) u.get("KichHoat");

                            publishProgress(1);
                        } else {
                            publishProgress(0);
                        }
                    } else {
                        publishProgress(0);
                    }
                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... value) {
            if (value[0] == 1)
                isSuccess = true;
            contextParent.checkLogin(isSuccess);
        }

        @Override
        protected void onPostExecute(Void data) {
            super.onPostExecute(data);
        }
    }
}
