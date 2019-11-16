package com.lqcuongnd.cnscanner.UserActivities;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.lqcuongnd.cnscanner.Firebase.Report.GetReportListAsync;
import com.lqcuongnd.cnscanner.Models.BaoCao;
import com.lqcuongnd.cnscanner.Models.BaoCaoAdapter;
import com.lqcuongnd.cnscanner.Models.BaoCaoList;
import com.lqcuongnd.cnscanner.Models.SQLite;
import com.lqcuongnd.cnscanner.R;

import java.util.ArrayList;
import java.util.List;

public class ReportListActivity extends AppCompatActivity {

    private List<BaoCao>  baoCaoList = new ArrayList<>();
    private RecyclerView  reportList;
    private BaoCaoAdapter baoCaoAdapter;
    private String        tenDN;
    TextView lblEmpty;
    Spinner  spinTrangThai;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    GetReportListAsync getReportListAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tenDN = bundle.getString("tenDN");

        addControl();
        addEvent();
        getReportListAsync = new GetReportListAsync(tenDN, this);
        getReportListAsync.execute();

    }

    private void addControl() {
        reportList = (RecyclerView) findViewById(R.id.reportList);
        lblEmpty = (TextView) findViewById(R.id.lblEmpty);
        spinTrangThai = (Spinner) findViewById(R.id.spinTrangThai);
    }

    private void addEvent() {
        /** Call method add data*/
        createData();
    }

    /**
     * Add data to baoCaoList
     */
    public void createData() {

    }

    public void getList(int i, List<BaoCao> list) {

        if (i == 0) {
            lblEmpty.setVisibility(View.VISIBLE);
        } else {
            if (i == 1) {
                Toast.makeText(this, "Load xong!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Báo cáo của bạn được phản hồi", Toast.LENGTH_SHORT).show();
            }
            BaoCaoList bcList = new BaoCaoList(list);
            bcList.reverse();

            baoCaoList = bcList.getBaoCaoList();

            baoCaoAdapter = new BaoCaoAdapter(this, baoCaoList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            reportList.setLayoutManager(mLayoutManager);
            reportList.setAdapter(baoCaoAdapter);
        }

        catchEvents();
        addFilterListener();
    }


    public void catchEvents() {

        db.collection("BAOCAO")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            return;
                        }

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.MODIFIED) {
                                String s = dc.getDocument().getData().get("MaND").toString();
                                if (tenDN.compareTo(s) != 0 || dc.getDocument().getData().get("TrangThai").toString().compareTo("Chưa xử lý") == 0) {
                                    return;
                                }

                                DocumentSnapshot document = dc.getDocument();
                                BaoCao bc = new BaoCao();
                                bc = new BaoCao();
                                bc.setMa(document.get("Ma").toString());
                                bc.setTenPhong(document.get("TenPhong").toString());
                                bc.setTrangThai(document.get("TrangThai").toString());
                                bc.setChuThich(document.get("ChuThich").toString());

                                /*ThongBaoBaoCao thongBaoBaoCao = new ThongBaoBaoCao();
                                thongBaoBaoCao.setBaoCao(bc);
                                thongBaoBaoCao.setLabel("CNScanner: " + bc.getTrangThai());
                                thongBaoBaoCao.setBigText("Báo cáo phòng " + document.get("TenPhong").toString() + " của bạn " + bc.getTrangThai());
                                thongBaoBaoCao.run();*/

                                int flag = -1;
                                for (int i = 0; i < baoCaoList.size(); i++) {
                                    if (baoCaoList.get(i).getMa().compareTo(bc.getMa()) == 0) {
                                        baoCaoList.get(i).setTrangThai(bc.getTrangThai());
                                        baoCaoList.get(i).setChuThich(bc.getChuThich());
                                        flag = i;
                                        break;
                                    }
                                }

                                baoCaoAdapter = new BaoCaoAdapter(ReportListActivity.this, baoCaoList);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                reportList.setLayoutManager(mLayoutManager);
                                reportList.setAdapter(baoCaoAdapter);

                                // Tạo báo cáo

                                createNoti(baoCaoList.get(flag));

                            }
                        }

                    }
                });
    }

    private void addFilterListener() {

        spinTrangThai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (baoCaoList.size() == 0) {
                    return;
                }

                String loc = spinTrangThai.getSelectedItem().toString();

                if (loc.compareTo("Tất cả") == 0) {
                    baoCaoAdapter = new BaoCaoAdapter(ReportListActivity.this, baoCaoList);
                } else {
                    List<BaoCao> list;
                    BaoCaoList bcList = new BaoCaoList(baoCaoList);
                    list = bcList.filter(loc);

                    baoCaoAdapter = new BaoCaoAdapter(ReportListActivity.this, list);
                }
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                reportList.setLayoutManager(mLayoutManager);
                reportList.setAdapter(baoCaoAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createNoti(BaoCao bc){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Noti";
            String description = "Haizz";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CNScanner", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        SQLite sqLite = new SQLite(this);
        sqLite.putNotiBaoCao(bc);

        Intent intent = new Intent(this, ReportDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "CNScanner")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Báo cáo của bạn vừa được phản hồi")
                .setContentText(bc.getTrangThai())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Báo cáo phòng " + bc.getTenPhong() + " của bạn vừa được kỹ thuật viên phản hồi, click vào đây để xem chi tiết"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(1, mBuilder.build());

        Toast.makeText(this, "Báo cáo phòng " + bc.getTenPhong() + " của bạn " + bc.getTrangThai(), Toast.LENGTH_SHORT).show();
    }
}
