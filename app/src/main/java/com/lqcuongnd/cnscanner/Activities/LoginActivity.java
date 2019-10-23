package com.lqcuongnd.cnscanner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lqcuongnd.cnscanner.Models.LoginAsyncTask;
import com.lqcuongnd.cnscanner.Models.NguoiDung;
import com.lqcuongnd.cnscanner.R;

public class LoginActivity extends AppCompatActivity {

    EditText txtId;
    EditText txtPass;
    Button btnLogin;
    TextView lblRegister;

    String id;
    String pw;
    NguoiDung user;
    LoginAsyncTask loginAsyncTask;
    Boolean isSuccess = false;

    Bundle bundle = new Bundle();

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toast.makeText(this, "Log in", Toast.LENGTH_SHORT).show();

        setPalletes();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = txtId.getText().toString();
                pw = txtPass.getText().toString();

                user = new NguoiDung();

                if (id.compareTo("") != 0 && pw.compareTo("") != 0) {
                    user.setId(id);
                    user.setMatKhau(pw);

                    loginAsyncTask = new LoginAsyncTask(LoginActivity.this);
                    loginAsyncTask.setUser(user);
                    loginAsyncTask.execute();



                    /*if(!loginAsyncTask.getSuccess()){
                        Toast.makeText(LoginActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }*/

                } else {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

        lblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setPalletes() {
        txtId = (EditText) findViewById(R.id.txtId);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        lblRegister = (TextView) findViewById(R.id.lblRegister);
    }

    public void doFinish() {
        if (isSuccess)
            Toast.makeText(LoginActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
        /*else
            Toast.makeText(LoginActivity.this, "Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return false;
    }


}
