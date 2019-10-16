package com.lqcuongnd.cnscanner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lqcuongnd.cnscanner.Models.User;
import com.lqcuongnd.cnscanner.R;

//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    EditText txtId;
    EditText txtPass;
    Button btnLogin;
    TextView lblRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toast.makeText(this, "Log in", Toast.LENGTH_SHORT).show();

        txtId = (EditText) findViewById(R.id.txtId);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        lblRegister = (TextView) findViewById(R.id.lblRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User u = new User();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", u);
                Intent intent = getIntent();
                intent.putExtras(bundle);
                setResult(MainActivity.RESULT_LOGIN, intent);
                finish();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                //Dang nhap thanh cong
                if (true) {
                    //testing

                    db.collection("NGUOIDUNG")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {

                                        for (QueryDocumentSnapshot document : task.getResult()) {

                                            String id = txtId.getText().toString();
                                            String pass = txtPass.getText().toString();

                                            if(document.get("TenDN").toString().compareTo(id) == 0){
                                                if(document.get("MatKhau").toString().compareTo(pass) == 0){
                                                }
                                            }

                                            //Log.d("kqqqqqq", document.get("first").toString());
                                        }
                                    } else {
                                        Log.w("kq", "Thất bại");
                                    }
                                }
                            });


                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Ngăn trở về main khi chưa đăng nhập
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //"doNotThing";
        }
        return false;
    }

}
