package com.lqcuongnd.cnscanner.UserActivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lqcuongnd.cnscanner.Models.BaoCao;
import com.lqcuongnd.cnscanner.Models.SQLite;
import com.lqcuongnd.cnscanner.R;

public class ReportDetailActivity extends AppCompatActivity {

    TextView lblNgay;

    EditText txtPhong;
    EditText txtLoi;
    EditText txtChiTiet;
    EditText txtChuThich;
    EditText txtTrangThai;

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Ngăn tự động bung bàn phím ảo

        anhXa();
        setOnClick();
        getBaoCao();
    }

    private void anhXa() {
        lblNgay = (TextView) findViewById(R.id.lblNgay);
        txtPhong = (EditText) findViewById(R.id.txtPhong);
        txtLoi = (EditText) findViewById(R.id.txtLoi);
        txtChiTiet = (EditText) findViewById(R.id.txtChiTiet);
        txtChuThich = (EditText) findViewById(R.id.txtChuThich);
        txtTrangThai = (EditText) findViewById(R.id.txtTrangThai);
        btnBack = (Button) findViewById(R.id.btnBack);
    }

    private void setOnClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getExtras() != null)
                    finish();
                else {
                    Intent intent = new Intent(ReportDetailActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getBaoCao() {

        Bundle bundle = getIntent().getExtras();
        BaoCao baoCao;
        SQLite sqLite = new SQLite(ReportDetailActivity.this);

        if (bundle != null) {
            baoCao = (BaoCao) bundle.getSerializable("baocao");
        } else {
            baoCao = sqLite.getNotiBaoCao();
        }

        lblNgay.setText(lblNgay.getText().toString() + baoCao.getThoiGian());
        txtPhong.setText(baoCao.getTenPhong());
        txtLoi.setText(baoCao.getLoi());
        txtChiTiet.setText(baoCao.getChiTiet());
        txtChuThich.setText(baoCao.getChuThich());
        txtTrangThai.setText(baoCao.getTrangThai());

        if (baoCao.getTrangThai().compareTo("Đã xử lý") == 0)
            txtTrangThai.setTextColor(Color.parseColor("#0f7a4a"));
        if (baoCao.getTrangThai().compareTo("Đang xử lý") == 0)
            txtTrangThai.setTextColor(Color.parseColor("#2640bf"));

        sqLite.removeNotiBaoCao();
        sqLite.close();
    }


}
