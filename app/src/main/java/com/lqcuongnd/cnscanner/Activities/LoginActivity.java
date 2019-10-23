package com.lqcuongnd.cnscanner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lqcuongnd.cnscanner.Models.NguoiDung;
import com.lqcuongnd.cnscanner.R;
import java.util.Map;
import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends AppCompatActivity {

    EditText txtId;
    EditText txtPass;
    Button btnLogin;
    TextView lblRegister;
    GifImageView imgloadingLogo;
    ImageView imgLogo;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String id;
    String pw;
    Map<String, Object> u;
    NguoiDung user;

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

        setPalletes();

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

                    user.login(LoginActivity.this);

                    /*loginAsyncTask = new LoginAsyncTask();
                    loginAsyncTask.execute();*/
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
        imgloadingLogo = (GifImageView) findViewById(R.id.imgloadingLogo);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
    }

    public void checkLogin(Boolean b){
        if(b){
            intent = getIntent();
            bundle = user.getBundle();
            intent.putExtras(bundle);
            setResult(11, intent);
            finish();
        }
        else{
            Toast.makeText(LoginActivity.this, "Vui lòng xem lại thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            txtId.setText("");
            txtPass.setText("");
            imgLogo.setVisibility(View.VISIBLE);
            imgloadingLogo.setVisibility(View.GONE);
        }
    }

    /*private class LoginAsyncTask extends AsyncTask<Void, Integer, Void> {

        public LoginAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(LoginActivity.this, "Đang kiểm tra thông tin ...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            db.collection("NGUOIDUNG").whereEqualTo("TenDN", user.getId()).whereEqualTo("MatKhau", user.getMatKhau()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().size() > 0) {
                            DocumentSnapshot document = task.getResult().getDocuments().get(0);
                            u = document.getData();

                            user.setTenDN((String) u.get("TenDN"));
                            user.setMatKhau((String) u.get("MatKhau"));
                            user.setTen((String) u.get("Ten"));
                            user.setGioiTinh((Boolean) u.get("GioiTinh"));
                            user.setId((String) u.get("Id").toString());
                            user.setLoai(Integer.parseInt(u.get("Loai").toString()));
                            user.setKichHoat((Boolean) u.get("KichHoat"));

                            publishProgress(1);
                        } else {
                            publishProgress(0);
                        }
                    } else {
                        publishProgress(0);
                    }
                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... value) {
            if (value[0] == 1) {
                intent = getIntent();
                bundle = user.getBundle();
                intent.putExtras(bundle);
                setResult(11, intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                txtId.setText("");
                txtPass.setText("");
            }
        }

        @Override
        protected void onPostExecute(Void data) {
            super.onPostExecute(data);
        }
    }*/
}

