package com.lqcuongnd.cnscanner.Firebase.User;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.lqcuongnd.cnscanner.UserActivities.LoginActivity;
import com.lqcuongnd.cnscanner.Models.NguoiDung;

import java.util.Map;

public class LoginAsync extends AsyncTask<Void, Integer, Void> {
    LoginActivity activity;
    NguoiDung     nguoiDung;
    private FirebaseFirestore   db = FirebaseFirestore.getInstance();
    private Map<String, Object> u;

    public LoginAsync(NguoiDung nguoiDung, LoginActivity activity) {
        this.activity = activity;
        this.nguoiDung = nguoiDung;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity, "Đang kiểm tra ...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        db.collection("NGUOIDUNG").whereEqualTo("TenDN", nguoiDung.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().size() > 0) {

                        DocumentSnapshot document = task.getResult().getDocuments().get(0);

                        Log.d("KQ", document.getId());

                        u = document.getData();

                        if (nguoiDung.getMatKhau().compareTo((String) u.get("MatKhau")) != 0) {
                            publishProgress(-1);
                            return;
                        }

                        nguoiDung.setDocumentID((String) document.getId());
                        nguoiDung.setTenDN((String) u.get("TenDN"));
                        nguoiDung.setMatKhau((String) u.get("MatKhau"));
                        nguoiDung.setTen((String) u.get("Ten"));
                        nguoiDung.setGioiTinh((Boolean) u.get("GioiTinh"));
                        nguoiDung.setId((String) u.get("Id").toString());
                        nguoiDung.setLoai(Integer.parseInt(u.get("Loai").toString()));
                        nguoiDung.setKichHoat((Boolean) u.get("KichHoat"));

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
        activity.checkLogin(value[0]);
    }

    @Override
    protected void onPostExecute(Void data) {
        super.onPostExecute(data);
    }
}