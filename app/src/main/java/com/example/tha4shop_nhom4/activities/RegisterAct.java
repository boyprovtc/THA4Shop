package com.example.tha4shop_nhom4.activities;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tha4shop_nhom4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterAct extends AppCompatActivity {
    EditText name, email, password, confirmpassword;
    private FirebaseAuth auth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

      //  getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterAct.this, MainAct.class));
            finish();
        }

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        confirmpassword = findViewById(R.id.editConfirmTextPassword);

        sharedPreferences = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);
        if(isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();
            Intent intent = new Intent(RegisterAct.this, OnboardingAct.class);
            startActivity(intent);
            finish();
        }

    }

    public void sign_Up(View v) {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String confirmUserpassword = confirmpassword.getText().toString();

        if (TextUtils.isEmpty(userName)
                || TextUtils.isEmpty(userEmail)
                || TextUtils.isEmpty(userPassword)) {
            makeText(this, "Vui lòng điền đầy đủ thông tin", LENGTH_SHORT).show();
            return;
        }
        if(userEmail == "admin@gmail.com"){
            makeText(this, "Email lỗi, vui lòng đổi email khác", LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 6) {
            makeText(this, "Mật khẩu ít nhất 6 kí tự", LENGTH_SHORT).show();
            return;
        }
        if (!userPassword.equals(confirmUserpassword)) {
            makeText(this, "Mật khẩu  phải trùng khớp", LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(RegisterAct.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            makeText(RegisterAct.this, "Đăng kí thành công", LENGTH_SHORT).show();

                            startActivity(new Intent(RegisterAct.this, LoginAct.class));
                        } else {
                            makeText(RegisterAct.this, "Đăng kí thất bại" + task.getException(), LENGTH_SHORT).show();

                        }
                    }
                });
        //   startActivity(new Intent(RegisterAct.this, LoginAct.class));
    }

    public void sign_In(View v) {
        startActivity(new Intent(RegisterAct.this, LoginAct.class));
    }
}