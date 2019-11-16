package com.lqcuongnd.cnscanner.UserActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.lqcuongnd.cnscanner.Models.NguoiDung;
import com.lqcuongnd.cnscanner.Models.SQLite;
import com.lqcuongnd.cnscanner.R;

public class SettingActivity extends AppCompatActivity {

    public static final int ACOUNT = 1;

    TextView btnDangXuat;
    TextView btnSuaThongTin;
    TextView lblTen;
    SQLite   sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setPalletes();
        setOnClick();
    }

    private void setPalletes() {
        sqLite = new SQLite(SettingActivity.this);
        btnSuaThongTin = (TextView) findViewById(R.id.btnSuaThongTin);
        btnDangXuat = (TextView) findViewById(R.id.btnDangXuat);
        lblTen = (TextView) findViewById(R.id.lblTen);

        String[] name = sqLite.getUser().getTen().split(" ");
        char n = name[name.length - 1].charAt(0);
        lblTen.setText(n + "");

    }

    private void setOnClick() {

        btnSuaThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NguoiDung nguoiDung = sqLite.getUser();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", nguoiDung);

                Intent intent = new Intent(SettingActivity.this, AccountActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, ACOUNT);
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("Xác thực đăng xuất");
                builder.setMessage("Bạn có chắc muốn đăng xuất?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logOut();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    private void logOut() {
        sqLite.logOut();
        Intent intent = getIntent();
        setResult(MainActivity.RESULT_LOGOUT_SETTING, intent);
        finish();
    }
}
