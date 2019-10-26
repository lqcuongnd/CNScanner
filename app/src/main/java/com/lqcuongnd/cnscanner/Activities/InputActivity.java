package com.lqcuongnd.cnscanner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lqcuongnd.cnscanner.R;

public class InputActivity extends AppCompatActivity {

    TextView lblTenPhong;
    EditText txtPhong;
    TextView txtLoi;
    TextView txtChiTiet;
    Intent intent;
    Bundle bundle;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        setPalletes();

        intent = getIntent();

        if(intent.getExtras() != null){
            bundle = intent.getExtras();
            code = bundle.getString("code");
            if(code.compareTo("") != 0){
                txtPhong.setText(code);
            }
        }
    }

    private void setPalletes(){
        lblTenPhong = (TextView) findViewById(R.id.lblTenPhong);
        txtPhong = (EditText) findViewById(R.id.txtPhong);
        txtLoi = (EditText) findViewById(R.id.txtLoi);
        txtChiTiet = (EditText) findViewById(R.id.txtChiTiet);
    }
}
