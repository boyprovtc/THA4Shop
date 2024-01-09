package com.example.tha4shop_nhom4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tha4shop_nhom4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_Products_Act extends AppCompatActivity {
    EditText productName, productsType, productsImg, productsRating, productDesc, productPrice;
    Toolbar toolbar;
    Button addProductsBtn;
    FirebaseFirestore firebase;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        toolbar = findViewById(R.id.products_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        firebase = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        productName = findViewById(R.id.add_productsName);
        productsType = findViewById(R.id.add_productsType);
        productsImg = findViewById(R.id.add_productsImgUrl);
        productsRating = findViewById(R.id.add_productsRating);
        productDesc = findViewById(R.id.add_productsDesc);
        productPrice = findViewById(R.id.add_productsPrice);
        addProductsBtn = findViewById(R.id.add_products_btn);

        addProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productName.getText().toString();
                String type = productsType.getText().toString();
                String img_url = productsImg.getText().toString();
                String rating = productsRating.getText().toString();
                String description = productDesc.getText().toString();
                String price = productPrice.getText().toString();
                if (!name.isEmpty() && !type.isEmpty() &&
                        !img_url.isEmpty() && !rating.isEmpty() && !description.isEmpty() && !type.isEmpty()) {
                    Map<String, Object> map = new HashMap<>();
                    int product_Price = Integer.parseInt(price.toString());
                    map.put("name",name);
                    map.put("type",type);
                    map.put("img_url",img_url);
                    map.put("rating",rating);
                    map.put("description",description);
                    map.put("price",product_Price);

                    firebase.collection("ShowAll")
                            .add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Add_Products_Act.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Add_Products_Act.this, AllproductsActivity.class));
                                        finish();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(Add_Products_Act.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}