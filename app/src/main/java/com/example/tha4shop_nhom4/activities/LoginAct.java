package com.example.tha4shop_nhom4.activities;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tha4shop_nhom4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginAct extends AppCompatActivity {
    EditText email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

     //   getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
    }

    public void signIn(View v) {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userEmail)
                || TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

      auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(LoginAct.this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  Toast.makeText(LoginAct.this, "Đăng nhập thành công",Toast.LENGTH_SHORT).show();

                  startActivity(new Intent(LoginAct.this, MainAct.class));
              }else {
                  Toast.makeText(LoginAct.this, "Sai Email hoặc mật khẩu",Toast.LENGTH_SHORT).show();
              }
          }
      });
    }

    public void signUp(View v) {
        startActivity(new Intent(LoginAct.this, RegisterAct.class));
    }
}