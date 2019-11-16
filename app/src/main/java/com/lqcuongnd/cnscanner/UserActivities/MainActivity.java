package com.lqcuongnd.cnscanner.UserActivities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lqcuongnd.cnscanner.Models.BaoCao;
import com.lqcuongnd.cnscanner.Models.NguoiDung;
import com.lqcuongnd.cnscanner.Models.SQLite;
import com.lqcuongnd.cnscanner.R;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    //Khai báo biến và paletes================================================================
    GifImageView btnScan;
    ImageView    btnInput;
    ImageView    btnList;
    ImageView    btnSetting;
    ImageView    btnAbout;

    Intent intent;
    Bundle bundle;
    public static NguoiDung user = null;
    SQLite sqLite;

    IntentIntegrator intentIntegrator;

    public static final int LOGIN                 = 2;
    public static final int RESULT_OK_LOGIN       = 21;
    public static final int RESULT_CANCEL_LOGIN   = 222;
    public static final int SCANNING              = 3;
    public static final int RESULTOK_SCANNING     = 31;
    public static final int LIST                  = 4;
    public static final int RESULT_LIST           = 41;
    public static final int SETTING               = 5;
    public static final int RESULT_LOGOUT_SETTING = 51;
    public static final int INPUT                 = 6;
    public static final int RESULT_INPUT          = 61;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //Xong phần khai báo =====================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //run Login screen
        sqLite = new SQLite(this);
        if (!sqLite.isLogIn()) {
            intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, LOGIN);
        } else {
            user = sqLite.getUser();
        }

        setPalettes();

        setOnClick();

        catchEvents();
    }

    private void setPalettes() {
        btnScan = (GifImageView) findViewById(R.id.btnScan);
        btnInput = (ImageView) findViewById(R.id.btnInput);
        btnList = (ImageView) findViewById(R.id.btnList);
        btnSetting = (ImageView) findViewById(R.id.btnSetting);
        btnAbout = (ImageView) findViewById(R.id.btnAbout);
    }

    private void setOnClick() {
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle = user.getBundle();
                intent = new Intent(MainActivity.this, InputActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, INPUT);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle = user.getBundle();
                intent = new Intent(MainActivity.this, ReportListActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, LIST);
            }
        });
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setOrientationLocked(false);
                integrator.setTimeout(10000);
                integrator.setBarcodeImageEnabled(true);
                integrator.setPrompt("");
                integrator.initiateScan();
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent, SETTING);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //get qrcode scan
        try {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() != null) {
                    bundle = new Bundle();
                    bundle.putString("code", result.getContents().toString());
                    bundle.putString("tenDN", user.getTenDN());
                    intent = new Intent(MainActivity.this, InputActivity.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, INPUT);
                }
            }
        } catch (Exception e) {
        }

        switch (requestCode) {
            case LOGIN:

                if (resultCode == RESULT_OK_LOGIN) {
                    Bundle bundle = data.getExtras();
                    user = new NguoiDung(bundle);
                    Toast.makeText(this, "Xin chào " + user.getTen(), Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }

                break;

            case SCANNING:
                if (resultCode == RESULTOK_SCANNING) {

                }
                break;
            case LIST:
                if (resultCode == RESULT_LIST) {

                }
                break;
            case SETTING:
                if (resultCode == RESULT_LOGOUT_SETTING) {
                    intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, LOGIN);
                }
                break;
            case INPUT:
                if (resultCode == RESULT_INPUT) {

                }
                break;
        }
    }


    public void catchEvents() {
        //Chưa đăng nhập thì thoát
        if(user == null)
            return;

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
                                if (user.getTenDN().compareTo(s) != 0 || dc.getDocument().getData().get("TrangThai").toString().compareTo("Chưa xử lý") == 0) {
                                    return;
                                }

                                DocumentSnapshot document = dc.getDocument();
                                BaoCao bc = new BaoCao();
                                bc = new BaoCao();
                                bc.setMa(document.get("Ma").toString());
                                bc.setTrangThai(document.get("TrangThai").toString());
                                bc.setChuThich(document.get("ChuThich").toString());
                                bc.setTenPhong(document.get("TenPhong").toString());
                                bc.setLoi(document.get("Loi").toString());
                                bc.setChiTiet(document.get("ChiTiet").toString());
                                bc.setThoiGian(document.get("ThoiGian").toString());


                                // Tạo báo cáo
                                createNoti(bc);
                            }
                        }

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

        SQLite sqLite = new SQLite(MainActivity.this);
        sqLite.putNotiBaoCao(bc);

        Intent intent = new Intent(MainActivity.this, ReportDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, "CNScanner")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Báo cáo của bạn vừa được phản hồi")
                .setContentText(bc.getTrangThai())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Báo cáo phòng " + bc.getTenPhong() + " của bạn vừa được kỹ thuật viên phản hồi, click vào đây để xem chi tiết"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

        notificationManager.notify(1, mBuilder.build());

        Toast.makeText(MainActivity.this, "Báo cáo phòng " + bc.getTenPhong() + " của bạn " + bc.getTrangThai(), Toast.LENGTH_SHORT).show();
    }
}
