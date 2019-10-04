package com.example.cnscanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;

    public static final int WELCOME = 1;
    public static final int RESULT_WELCOME = 11;
    public static final int LOGIN = 2;
    public static final int RESULT_LOGIN = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, WelcomeActivity.class);
        startActivityForResult(intent, WELCOME);

        if (!isLogged()) {
            intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, LOGIN);
        }
    }

    //Hàm kiểm tra đã đăng nhập hay chưa
    private boolean isLogged() {
        if (true) {
            //Đã đăng nhập
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case WELCOME:
                if (resultCode == RESULT_WELCOME) {

                }
                break;
            case LOGIN:
                if (resultCode == RESULT_LOGIN) {

                }
                break;

        }
    }
}
