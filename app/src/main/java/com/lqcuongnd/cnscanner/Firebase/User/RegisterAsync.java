package com.lqcuongnd.cnscanner.Firebase.User;

import android.os.AsyncTask;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lqcuongnd.cnscanner.UserActivities.RegisterActivity;
import com.lqcuongnd.cnscanner.Models.NguoiDung;

import java.util.HashMap;
import java.util.Map;

public class RegisterAsync extends AsyncTask<Void, Integer, Void> {
    RegisterActivity activity;
    NguoiDung        nguoiDung;
    private FirebaseFirestore   db        = FirebaseFirestore.getInstance();
    private Map<String, Object> u;
    private Boolean             isSuccess = false;
    int th = 0;

    public RegisterAsync(NguoiDung nguoiDung, RegisterActivity contextParent) {
        this.nguoiDung = nguoiDung;
        this.activity = contextParent;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity, "Đang tạo tài khoản ...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        DocumentReference docRef = db.collection("NGUOIDUNG").document(nguoiDung.getTenDN());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        publishProgress(RegisterActivity.EXIST);
                        th = 2;
                        return;
                    } else {

                        Map<String, Object> data = new HashMap<>();
                        data.put("TenDN", nguoiDung.getTenDN());
                        data.put("MatKhau", nguoiDung.getMatKhau());
                        data.put("Ten", nguoiDung.getTen());
                        data.put("GioiTinh", nguoiDung.getGioiTinh());
                        data.put("Id", nguoiDung.getId());
                        data.put("Loai", nguoiDung.getLoai());
                        data.put("KichHoat", nguoiDung.getKichHoat());

                        db.collection("NGUOIDUNG").document(nguoiDung.getTenDN())
                                .set(data)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(activity, "Tài khoản của " + nguoiDung.getOnlyTen() + " đã được tạo thành công", Toast.LENGTH_SHORT).show();
                                        publishProgress(1);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        publishProgress(0);
                                    }
                                });
                    }
                } else {

                }
            }
        });



/*
        db.collection("NGUOIDUNG").whereEqualTo("TenDN", nguoiDung.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().size() > 0) {
                        publishProgress(RegisterActivity.EXIST);
                        th = 2;
                        return;
                    } else {
                        Map<String, Object> data = new HashMap<>();
                        data.put("TenDN", nguoiDung.getTenDN());
                        data.put("MatKhau", nguoiDung.getMatKhau());
                        data.put("Ten", nguoiDung.getTen());
                        data.put("GioiTinh", nguoiDung.getGioiTinh());
                        data.put("Id", nguoiDung.getId());
                        data.put("Loai", nguoiDung.getLoai());
                        data.put("KichHoat", nguoiDung.getKichHoat());

                        db.collection("NGUOIDUNG")
                                .add(data)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(activity, "Tài khoản của " + nguoiDung.getOnlyTen() + " đã được tạo thành công", Toast.LENGTH_SHORT).show();
                                        publishProgress(1);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        publishProgress(0);
                                    }
                                });
                    }
                } else {


                }
            }
        });*/

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... value) {
        activity.async(value[0]);
    }

    @Override
    protected void onPostExecute(Void data) {
        super.onPostExecute(data);
    }
}