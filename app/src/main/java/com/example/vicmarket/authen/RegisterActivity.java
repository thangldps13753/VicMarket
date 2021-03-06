package com.example.vicmarket.authen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vicmarket.R;
import com.example.vicmarket.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView tvDaCoTaiKhoan;
    Button btnDangKyTaiKhoan;
    EditText name, email, pasword, sdt, diachi;
    ProgressBar progressBar;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        tvDaCoTaiKhoan = findViewById(R.id.tvDaCoTaiKhoan);
        btnDangKyTaiKhoan = findViewById(R.id.btnDangKyTaiKhoan);
        name = findViewById(R.id.edt_tenDangKy);
        email = findViewById(R.id.edt_emailDangKy);
        pasword = findViewById(R.id.edt_passwordDangKy);
        sdt = findViewById(R.id.edt_sdtDangKy);
        diachi = findViewById(R.id.edt_diaChiDangKy);
        progressBar = findViewById(R.id.progress_register);
        progressBar.setVisibility(View.GONE);
        btnDangKyTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangKy();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        tvDaCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void dangKy() {
        String Userten = name.getText().toString();
        String Useremail = email.getText().toString();
        String Userpassword = pasword.getText().toString();
        String Usersdt = sdt.getText().toString();
        String UserdiaChi = diachi.getText().toString();

        //B???T L???I ????NG K??
        if (TextUtils.isEmpty(Userten)) {
            Toast.makeText(this, "T??n kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Useremail)) {
            Toast.makeText(this, "Email kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Userpassword)) {
            Toast.makeText(this, "Password kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return;
        }if (TextUtils.isEmpty(Usersdt)) {
            Toast.makeText(this, "S??? ??i???n tho???i kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(UserdiaChi)) {
            Toast.makeText(this, "?????a ch??? kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Userpassword.length() < 6) {
            Toast.makeText(this, "Password kh??ng ???????c d?????i 6 k?? t???", Toast.LENGTH_SHORT).show();
            return;
        }
        //KH???I T???O USER B???NG FIREBASE
        auth.createUserWithEmailAndPassword(Useremail, Userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserModel user = new UserModel(Userten, Useremail, Userpassword,UserdiaChi,Usersdt);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "????ng k?? th??nh c??ng!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "????ng k?? th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}