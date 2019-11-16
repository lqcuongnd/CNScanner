package com.lqcuongnd.cnscanner.UserActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.lqcuongnd.cnscanner.Models.NguoiDung;
import com.lqcuongnd.cnscanner.R;

public class AccountActivity extends AppCompatActivity {

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

    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Ngăn tự động bung bàn phím ảo

        setPaletes();
        getData();
        setOnClick();
    }

    private void setPaletes() {
        intent = getIntent();
        bundle = intent.getExtras();

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

    public void getData() {
        nguoiDung = (NguoiDung) bundle.getSerializable("user");
        txtTenDN.setText(nguoiDung.getTenDN());
        txtPass.setText(nguoiDung.getMatKhau());
        txtHoTen.setText(nguoiDung.getTen());
        txtID.setText(nguoiDung.getId());
        if (nguoiDung.getLoai() == 1) {
            chooseSinhVien();
        } else {
            chooseGiangVien();
        }
        if (nguoiDung.getGioiTinh()) {
            chooseNam();
        } else {
            chooseNu();
        }
    }

    public void setOnClick() {
        btnSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseSinhVien();
            }
        });
        btnGiangVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseGiangVien();
            }
        });
        btnNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseNam();
            }
        });
        btnNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseNu();
            }
        });
    }

    private void chooseSinhVien() {
        if (type == 1)
            return;
        btnSinhVien.setBackgroundColor(Color.parseColor("#4166F5"));
        btnSinhVien.setTextColor(Color.parseColor("#FFFFFF"));
        btnGiangVien.setBackgroundColor(Color.parseColor("#e3dae2"));
        btnGiangVien.setTextColor(Color.parseColor("#f200ad"));
        type = 1;
    }

    private void chooseGiangVien() {
        if (type == 2)
            return;
        btnSinhVien.setBackgroundColor(Color.parseColor("#e3dae2"));
        btnSinhVien.setTextColor(Color.parseColor("#f200ad"));
        btnGiangVien.setBackgroundColor(Color.parseColor("#4166F5"));
        btnGiangVien.setTextColor(Color.parseColor("#FFFFFF"));
        type = 2;
    }

    private void chooseNam() {
        if (gt == 1)
            return;
        btnNam.setBackgroundColor(Color.parseColor("#4166F5"));
        btnNam.setTextColor(Color.parseColor("#FFFFFF"));
        btnNu.setBackgroundColor(Color.parseColor("#e3dae2"));
        btnNu.setTextColor(Color.parseColor("#f200ad"));
        gt = 1;
    }

    private void chooseNu() {
        if (gt == 0)
            return;
        btnNam.setBackgroundColor(Color.parseColor("#e3dae2"));
        btnNam.setTextColor(Color.parseColor("#f200ad"));
        btnNu.setBackgroundColor(Color.parseColor("#4166F5"));
        btnNu.setTextColor(Color.parseColor("#FFFFFF"));
        gt = 0;
    }
}
