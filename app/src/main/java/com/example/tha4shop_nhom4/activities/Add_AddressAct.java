package com.example.tha4shop_nhom4.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tha4shop_nhom4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_AddressAct extends AppCompatActivity {

    EditText name, address, city, postalCode, phoneNumber;
    Toolbar toolbar;
    Button addAddressBtn;
    FirebaseFirestore firebase;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);


        name = findViewById(R.id.ad_name);
        address = findViewById(R.id.ad_address);
        city = findViewById(R.id.ad_city);
        postalCode = findViewById(R.id.ad_code);
        phoneNumber = findViewById(R.id.ad_phone);
        addAddressBtn = findViewById(R.id.ad_add_address_btn);
        auth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userCity = city.getText().toString();
                String userAddress = address.getText().toString();
                String userpCode = postalCode.getText().toString();
                String userPhone = phoneNumber.getText().toString();

                String final_address = "";
                if (!userName.isEmpty()) {
                    final_address = final_address + userName + " " ;
                }
                if (!userCity.isEmpty()) {
                    final_address = final_address + userCity + " ";
                }
                if (!userAddress.isEmpty()) {
                    final_address = final_address + userAddress + " ";
                }
                if (!userpCode.isEmpty()) {
                    final_address = final_address + userpCode + " ";
                }
                if (!userPhone.isEmpty()) {
                    final_address = final_address + userPhone + " ";
                }
                if (!userName.isEmpty() && !userCity.isEmpty() &&
                        !userPhone.isEmpty() && !userAddress.isEmpty() && !userpCode.isEmpty()) {
                    Map<String, String> map = new HashMap<>();
                    map.put("userAddress", final_address);

                    firebase.collection("currentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Add_AddressAct.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(Add_AddressAct.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}