package com.lqcuongnd.cnscanner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lqcuongnd.cnscanner.R;

import javax.annotation.Nonnull;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    //Khai báo biến và paletes================================================================
    GifImageView btnScan;
    ImageView btnInput;
    ImageView btnList;
    ImageView btnSetting;
    ImageView btnAbout;

    private boolean isWelcomed = false;

    Intent intent;
    Bundle bundle;

    IntentIntegrator intentIntegrator;

    public static final int WELCOME = 1;
    public static final int RESULT_WELCOME = 11;
    public static final int LOGIN = 2;
    public static final int RESULT_LOGIN = 21;
    public static final int SCANNING = 3;
    public static final int RESULTOK_SCANNING = 31;
    public static final int LIST = 4;
    public static final int RESULT_LIST = 41;
    public static final int SETTING = 5;
    public static final int RESULT_SETTING = 51;
    public static final int INPUT = 6;
    public static final int RESULT_INPUT = 61;
    //Xong phần khai báo =====================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //run Login screen
        if (!isLogged()) {
            intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, LOGIN);
        }

        //set Paletes
        setPalettes();

        //set onClick
        setOnClick();
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
                intent = new Intent(MainActivity.this, InputActivity.class);
                startActivityForResult(intent, INPUT);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ReportListActivity.class);
                startActivityForResult(intent, SCANNING);
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
                startActivityForResult(intent, LIST);
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

    //Hàm kiểm tra đã đăng nhập hay chưa
    private boolean isLogged() {

        if (false) {
            //Đã đăng nhập
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nonnull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //get qrcode scan
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                bundle = new Bundle();
                bundle.putString("code", result.getContents());
                intent = new Intent(MainActivity.this, InputActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, INPUT);
            }
        }

        switch (requestCode) {
            case WELCOME:
                if (resultCode == RESULT_WELCOME) {
                }
                break;
            case LOGIN:
                if (resultCode == RESULT_LOGIN) {

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
                if (resultCode == RESULT_SETTING) {

                }
                break;
            case INPUT:
                if (resultCode == RESULT_INPUT) {

                }
                break;
        }
    }
}
