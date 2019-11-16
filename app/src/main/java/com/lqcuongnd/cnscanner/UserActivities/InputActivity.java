package com.lqcuongnd.cnscanner.UserActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.lqcuongnd.cnscanner.Models.BaoCao;
import com.lqcuongnd.cnscanner.Firebase.Firebase;
import com.lqcuongnd.cnscanner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InputActivity extends AppCompatActivity {

    TextView             lblTenPhong;
    EditText             txtPhong;
    AutoCompleteTextView txtLoi;
    TextView             txtChiTiet;
    Button               btnSubmit;

    Intent            intent;
    Bundle            bundle;
    String            code;
    String            thoiGian;
    String            tenDN;
    BaoCao            baoCao;
    ArrayList<String> loi;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Ngăn tự động bung bàn phím ảo

        setPalletes();
        setOnClick();

        intent = getIntent();

        bundle = intent.getExtras();
        tenDN = bundle.getString("tenDN");

        if (bundle.getString("code") != null) {
            code = bundle.getString("code");
            txtPhong.setText(code);
        }
    }

    private void setPalletes() {
        lblTenPhong = (TextView) findViewById(R.id.lblTenPhong);
        txtPhong = (EditText) findViewById(R.id.txtPhong);
        txtLoi = (AutoCompleteTextView) findViewById(R.id.txtLoi);
        txtChiTiet = (EditText) findViewById(R.id.txtChiTiet);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //Demo nhắc lỗi, sẽ làm lại get từ firebase về danh sách lỗi để nhắc lỗi
        loi = new ArrayList<String>();
        loi.add("Wifi mất sóng");
        loi.add("Hỏng máy tính");
        loi.add("Hỏng máy chiếu");
        loi.add("Lỗi máy tính");
        loi.add("Lỗi máy chiếu");
        loi.add("Hỏng quạt");
        loi.add("Hỏng tivi");
        loi.add("Hỏng đèn");
        loi.add("Mất điện");
        loi.add("Mất mạng");

        ArrayAdapter loiAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, loi);
        txtLoi.setAdapter(loiAdapter);
        // Sét đặt số ký tự nhỏ nhất, để cửa sổ gợi ý hiển thị
        txtLoi.setThreshold(0);

    }

    private void setOnClick() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tenDN == null || tenDN.compareTo("") == 0)
                    return;

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Ngăn tự động bung bàn phím ảo

                Date date = new Date(); // this object contains the current date value
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                thoiGian = formatter.format(date);
                code = txtPhong.getText().toString();

                baoCao = new BaoCao();

                baoCao.setTenPhong(code);
                baoCao.setLoi(txtLoi.getText().toString());

                if(baoCao.getTenPhong().compareTo("") == 0 || baoCao.getLoi().compareTo("") == 0){
                    Toast.makeText(InputActivity.this, "Vui lòng nhập các thông tin cơ bản", Toast.LENGTH_SHORT).show();
                    return;
                }

                baoCao.setChiTiet(txtChiTiet.getText().toString());
                baoCao.setChuThich("");
                baoCao.setMa("");
                baoCao.setMaKTV("");
                //baoCao.setLoi(Integer.toString(loi.indexOf(txtLoi.getText().toString())));
                baoCao.setMaND(tenDN);
                baoCao.setMaThietBi("");
                baoCao.setThoiGian(thoiGian.toString());
                baoCao.setTrangThai("Chưa xử lý");


                Firebase fireBase = new Firebase();
                fireBase.taoBaoCao(baoCao, InputActivity.this);

                //baoCao.create(InputActivity.this);
            }
        });
    }

    public void async(int result, Bundle bundle) {
        switch (result) {
            case 1:
                Toast.makeText(this, "Đã tạo báo cáo", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case 2:
                Toast.makeText(this, "Tạo báo cáo lỗi!", Toast.LENGTH_SHORT).show();

        }

    }

}
