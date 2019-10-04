package com.example.cnscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView txt1;
    EditText editname, editpass;
    Button btnlogin, btncacel;
    ImageView imageView;
    int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControl();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editname.getText().toString().equals("admin") &&
                        editpass.getText().toString().equals("123")) {
                    Toast.makeText(LoginActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                   // Intent intent = new Intent(getApplicationContext(), Manager.class);
                   // startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();

                    txt1.setVisibility(View.VISIBLE);
                    txt1.setBackgroundColor(Color.RED);
                    counter--;
                    txt1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        btnlogin.setEnabled(false);
                    }
                }
            }
        });
        btncacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void addControl() {
        txt1 = (TextView) findViewById(R.id.textview);
        editname = (EditText) findViewById(R.id.editname);
        editpass = (EditText) findViewById(R.id.editpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btncacel = (Button) findViewById(R.id.btncancel);
        imageView = (ImageView) findViewById(R.id.imageView);
    }



}
