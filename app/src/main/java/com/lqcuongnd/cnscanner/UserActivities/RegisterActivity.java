package com.lqcuongnd.cnscanner.UserActivities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lqcuongnd.cnscanner.Firebase.Firebase;
import com.lqcuongnd.cnscanner.Models.NguoiDung;
import com.lqcuongnd.cnscanner.R;

public class RegisterActivity extends AppCompatActivity {

    public static final int EXIST = 2;
    public static final int SUCESS = 1;
    public static final int DONE = 3;
    public static final int ERROR = 0;

    EditText txtTenDN;
    EditText txtPass;
    EditText txtHoTen;
    EditText txtID;

    NguoiDung nguoiDung;

    Button btnSinhVien;
    Button btnGiangVien;
    Button btnNam;
    Button btnNu;
    Button btnDangKy;
    int    gt              = 1;
    int    type            = 1;
    String clickedButton   = "#4166F5";
    String unClickedButton = "#e3dae2";
    String clickedText     = "#FFFFFF";
    String unClickedText   = "#f200ad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setPaletes();
        setOnClick();
    }

    private void setPaletes() {

        btnSinhVien = (Button) findViewById(R.id.btnSinhvien);
        btnGiangVien = (Button) findViewById(R.id.btnGiangVien);
        btnNam = (Button) findViewById(R.id.btnNam);
        btnNu = (Button) findViewById(R.id.btnNu);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        txtTenDN = (EditText) findViewById(R.id.txtTenDN);
        txtPass = (EditText) findViewById(R.id.txtPass);
        txtHoTen = (EditText) findViewById(R.id.txtHoTen);
        txtID = (EditText) findViewById(R.id.txtID);
    }

    public void setOnClick() {
        btnSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1)
                    return;
                btnSinhVien.setBackgroundColor(Color.parseColor("#4166F5"));
                btnSinhVien.setTextColor(Color.parseColor("#FFFFFF"));
                btnGiangVien.setBackgroundColor(Color.parseColor("#e3dae2"));
                btnGiangVien.setTextColor(Color.parseColor("#f200ad"));
                type = 1;
            }
        });
        btnGiangVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 2)
                    return;
                btnSinhVien.setBackgroundColor(Color.parseColor("#e3dae2"));
                btnSinhVien.setTextColor(Color.parseColor("#f200ad"));
                btnGiangVien.setBackgroundColor(Color.parseColor("#4166F5"));
                btnGiangVien.setTextColor(Color.parseColor("#FFFFFF"));
                type = 2;
            }
        });
        btnNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gt == 1)
                    return;
                btnNam.setBackgroundColor(Color.parseColor("#4166F5"));
                btnNam.setTextColor(Color.parseColor("#FFFFFF"));
                btnNu.setBackgroundColor(Color.parseColor("#e3dae2"));
                btnNu.setTextColor(Color.parseColor("#f200ad"));
                gt = 1;
            }
        });
        btnNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gt == 0)
                    return;
                btnNam.setBackgroundColor(Color.parseColor("#e3dae2"));
                btnNam.setTextColor(Color.parseColor("#f200ad"));
                btnNu.setBackgroundColor(Color.parseColor("#4166F5"));
                btnNu.setTextColor(Color.parseColor("#FFFFFF"));
                gt = 0;
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nguoiDung = new NguoiDung();

                nguoiDung.setTenDN(txtTenDN.getText().toString());
                nguoiDung.setMatKhau(txtPass.getText().toString());
                nguoiDung.setTen(txtHoTen.getText().toString());
                nguoiDung.setId(txtID.getText().toString());
                nguoiDung.setGioiTinh(gt == 1 ? true : false);
                nguoiDung.setLoai(1);
                nguoiDung.setKichHoat(true);

                Firebase firebase = new Firebase();

                firebase.dangKy(nguoiDung, RegisterActivity.this);
            }
        });
    }

    public void async(int i) {
        if (i == ERROR) {
            Toast.makeText(this, "Có lỗi xảy ra @@", Toast.LENGTH_SHORT).show();
        }
        if (i == SUCESS) {
            finish();
        }
        if (i == EXIST) {
            Toast.makeText(this, "Tên đăng nhập này đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }
}
