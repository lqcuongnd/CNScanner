package com.lqcuongnd.cnscanner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.lqcuongnd.cnscanner.R;

import java.util.HashMap;
import java.util.Map;

public class WelcomeActivity extends AppCompatActivity {

    Intent intent;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        /*String s = "C1.10";
        for(int i = 1;i<7;i++){
            Map<String, Object> p = new HashMap<>();
            p.put("TenPhong", s + i);
            p.put("GhiChu", "");
            db.collection("PHONG").document(s + i).set(p);
            if(s.compareTo("C1.10") == 0 && i == 6){
                i = 0;
                s = "C1.20";
            }
        }*/

        intent = new Intent(WelcomeActivity.this, MainActivity.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    startActivityForResult(intent, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
