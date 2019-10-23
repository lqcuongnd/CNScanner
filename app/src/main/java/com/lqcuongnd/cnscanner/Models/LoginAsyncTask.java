package com.lqcuongnd.cnscanner.Models;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lqcuongnd.cnscanner.Activities.LoginActivity;
import com.lqcuongnd.cnscanner.Activities.MainActivity;

import java.io.Serializable;
import java.util.Map;

public class LoginAsyncTask extends AsyncTask<Void, Integer, Void> {
    LoginActivity contextParent;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Map<String, Object> u;
    private NguoiDung user;
    Boolean isSuccess = false;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public LoginAsyncTask(LoginActivity contextParent) {
        this.contextParent = contextParent;
    }

    public void setU(Map<String, Object> u) {
        this.u = u;
    }

    public NguoiDung getUser() {
        return user;
    }

    public void setUser(NguoiDung user) {
        this.user = user;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextParent, "Đang kiểm tra thông tin ...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        db.collection("NGUOIDUNG").whereEqualTo("TenDN", user.getId()).whereEqualTo("MatKhau", user.getMatKhau()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        u = document.getData();

                        user.setTenDN((String) u.get("TenDN"));
                        user.setMatKhau((String) u.get("MatKhau"));
                        user.setTen((String) u.get("Ten"));
                        user.setGioiTinh((Boolean) u.get("GioiTinh"));
                        user.setId((String) u.get("Id").toString());
                        user.setLoai(Integer.parseInt(u.get("Loai").toString()));
                        user.setKichHoat((Boolean) u.get("KichHoat"));

                        publishProgress(1);
                        return;
                    }
                } else {
                    publishProgress(0);
                    return;
                }
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... value) {
        if(value[0] == 1){
            isSuccess = true;
            contextParent.setSuccess(true);
            Intent intent = contextParent.getIntent();
            Bundle bundle = user.getBundle();
            intent.putExtras(bundle);
            contextParent.setResult(MainActivity.RESULT_LOGIN, intent);
            contextParent.finish();
        }
        else{
            isSuccess = false;
            contextParent.setSuccess(false);
        }
    }

    @Override
    protected void onPostExecute(Void data) {
        super.onPostExecute(data);
        contextParent.doFinish();
    }


}
