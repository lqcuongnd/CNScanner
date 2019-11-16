package com.lqcuongnd.cnscanner.UserActivities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.lqcuongnd.cnscanner.R;

public class WelcomeActivity extends AppCompatActivity {

    Intent intent;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        intent = new Intent(WelcomeActivity.this, MainActivity.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                    finish();
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        };
        timer.start();
    }

}

/*
* - CẬP NHẬT THÔNG BÁO (USER)
- THỐNG KÊ THÔNG BÁO HÀNG NGÀY  (ADMIN): LỌC THEO THỜI GIAN, ĐỊA ĐIỂM (NHÓM PHÒNG (DÃY))
- XEM LẠI TÌNH TRẠNG CÁC BÁO CÁO CỦA MH (USER)
- ĐĂNG KÝ.
* */
