package com.lqcuongnd.cnscanner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.lqcuongnd.cnscanner.R;

public class WelcomeActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

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
        //Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        timer.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
