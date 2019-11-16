package com.lqcuongnd.cnscanner.Firebase.Report;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lqcuongnd.cnscanner.UserActivities.InputActivity;
import com.lqcuongnd.cnscanner.Models.BaoCao;

import java.util.Map;

public class AddReportAsync extends AsyncTask<Void, Integer, Void> {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private BaoCao            baoCao;
    private InputActivity     activity;

    public AddReportAsync(BaoCao baoCao, InputActivity activity) {
        this.baoCao = baoCao;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity, "Đang tạo báo cáo ...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        Map<String, String> data = baoCao.getMap();

        db.collection("BAOCAO")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("KQ", "DocumentSnapshot written with ID: " + documentReference.getId());
                        baoCao.setMa(documentReference.getId());
                        publishProgress(1);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("KQ", "Error adding document", e);
                        publishProgress(2);
                    }
                });
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... value) {
        if (value[0] == 1) {
            DocumentReference ref = db.collection("BAOCAO").document(baoCao.getMa());
            ref.update("Ma", baoCao.getMa());
        }
        activity.async(value[0], null);
    }

    @Override
    protected void onPostExecute(Void data) {
        super.onPostExecute(data);
    }
}