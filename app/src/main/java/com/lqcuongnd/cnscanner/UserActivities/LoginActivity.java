package com.lqcuongnd.cnscanner.UserActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lqcuongnd.cnscanner.Firebase.Firebase;
import com.lqcuongnd.cnscanner.Models.NguoiDung;
import com.lqcuongnd.cnscanner.Models.SQLite;
import com.lqcuongnd.cnscanner.R;

import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends AppCompatActivity {

    EditText     txtId;
    EditText     txtPass;
    Button       btnLogin;
    TextView     lblRegister;
    GifImageView imgloadingLogo;
    ImageView    imgLogo;

    SQLite              sqLite;
    String              id;
    String              pw;
    Map<String, Object> u;
    NguoiDung           user;

    Intent intent = null;
    Bundle bundle = null;

    public GifImageView getImgloadingLogo() {
        return imgloadingLogo;
    }

    public ImageView getImgLogo() {
        return imgLogo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intent = getIntent();

        setPalletes();

        setOnClick();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(MainActivity.RESULT_CANCEL_LOGIN, intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setPalletes() {
        txtId = (EditText) findViewById(R.id.txtId);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        lblRegister = (TextView) findViewById(R.id.lblRegister);
        imgloadingLogo = (GifImageView) findViewById(R.id.imgloadingLogo);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
    }

    private void setOnClick() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgLogo.setVisibility(View.GONE);
                imgloadingLogo.setVisibility(View.VISIBLE);

                id = txtId.getText().toString();
                pw = txtPass.getText().toString();

                user = new NguoiDung();

                if (id.compareTo("") != 0 && pw.compareTo("") != 0) {
                    user.setId(id);
                    user.setMatKhau(pw);

                    Firebase firebase = new Firebase();
                    firebase.dangNhap(user,LoginActivity.this);

                    //user.login(LoginActivity.this);
                } else {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

        lblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkLogin(int result) {
        if (result == 1) {
            bundle = user.getBundle();
            intent.putExtras(bundle);
            setResult(MainActivity.RESULT_OK_LOGIN, intent);
            sqLite = new SQLite(this);
            sqLite.logIn(user);
            Toast.makeText(this, "Xin chào " + user.getTen(), Toast.LENGTH_SHORT).show();
            finish();
        } else if (result == -1){
            Toast.makeText(LoginActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
            txtPass.setText("");
            imgLogo.setVisibility(View.VISIBLE);
            imgloadingLogo.setVisibility(View.GONE);
        } else {
            Toast.makeText(LoginActivity.this, "Vui lòng xem lại thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            txtId.setText("");
            txtPass.setText("");
            imgLogo.setVisibility(View.VISIBLE);
            imgloadingLogo.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == 1){

            }
            else{

            }
        }

    }

}

