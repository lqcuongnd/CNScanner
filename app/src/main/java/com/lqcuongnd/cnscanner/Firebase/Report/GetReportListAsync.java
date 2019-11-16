package com.lqcuongnd.cnscanner.Firebase.Report;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.lqcuongnd.cnscanner.UserActivities.ReportListActivity;
import com.lqcuongnd.cnscanner.Models.BaoCao;

import java.util.ArrayList;
import java.util.List;

public class GetReportListAsync extends AsyncTask<Void, Integer, Void> {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private BaoCao             baoCao;
    private String             tenDN;
    private ReportListActivity activity;
    List<BaoCao> baoCaoList;

    public GetReportListAsync(String tenDN, ReportListActivity activity) {
        this.tenDN = tenDN;
        this.activity = activity;
        baoCaoList = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity, "Đang tải ...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        db.collection("BAOCAO")
                .whereEqualTo("MaND", tenDN)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int l = task.getResult().size();
                            for (int i = 0; i < l; i++) {
                                DocumentSnapshot document = task.getResult().getDocuments().get(i);
                                baoCao = new BaoCao();
                                baoCao.setMa(document.get("Ma").toString());
                                baoCao.setTenPhong(document.get("TenPhong").toString());
                                baoCao.setLoi(document.get("Loi").toString());
                                baoCao.setChiTiet(document.get("ChiTiet").toString());
                                baoCao.setTrangThai(document.get("TrangThai").toString());
                                baoCao.setChuThich(document.get("ChuThich").toString());
                                baoCao.setThoiGian(document.get("ThoiGian").toString());
                                baoCaoList.add(baoCao);
                                if (i == l - 1) {
                                    Toast.makeText(activity, "Tải xong!", Toast.LENGTH_SHORT).show();
                                    publishProgress(1);
                                }
                            }
                            if (l == 0) {
                                publishProgress(0);
                            }
                        } else {
                        }
                    }
                });

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... value) {
        activity.getList(value[0], baoCaoList);
    }

    @Override
    protected void onPostExecute(Void data) {
        super.onPostExecute(data);
    }
}