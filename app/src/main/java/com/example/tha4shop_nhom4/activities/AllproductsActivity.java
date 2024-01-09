package com.example.tha4shop_nhom4.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tha4shop_nhom4.R;
import com.example.tha4shop_nhom4.adapters.AllProductsAdapter;
import com.example.tha4shop_nhom4.models.AllProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllproductsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AllProductsAdapter allProductsAdapter;
    List<AllProductsModel> allProductsModelsList;
    Toolbar toolbar;
    private FirebaseFirestore firestore;
    Button addproducts;
    FirebaseFirestore firebase;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allproducts);
        addproducts = findViewById(R.id.add_products);

        firebase = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.all_products_toolsbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String type = getIntent().getStringExtra("type");

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.all_products_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        addproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllproductsActivity.this, Add_Products_Act.class));
            }
        });

        allProductsModelsList = new ArrayList<>();
        allProductsAdapter = new AllProductsAdapter(this, allProductsModelsList);
        recyclerView.setAdapter(allProductsAdapter);
        if (type == null || type.isEmpty()) {
            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    AllProductsModel allProductsModel = document.toObject(AllProductsModel.class);
                                    String documentId = document.getId();
                                    allProductsModel.setDocumentId(documentId);
                                    allProductsModelsList.add(allProductsModel);
                                    allProductsAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }

        if (type != null && type.equalsIgnoreCase("laptop")) {
            firestore.collection("ShowAll").whereEqualTo("type", "laptop")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    AllProductsModel allProductsModel = document.toObject(AllProductsModel.class);
                                    allProductsModelsList.add(allProductsModel);
                                    allProductsAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("mouse")) {
            firestore.collection("ShowAll").whereEqualTo("type", "mouse")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    AllProductsModel allProductsModel = document.toObject(AllProductsModel.class);
                                    allProductsModelsList.add(allProductsModel);
                                    allProductsAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("watch")) {
            firestore.collection("ShowAll").whereEqualTo("type", "watch")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    AllProductsModel allProductsModel = document.toObject(AllProductsModel.class);
                                    allProductsModelsList.add(allProductsModel);
                                    allProductsAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("keyboard")) {
            firestore.collection("ShowAll").whereEqualTo("type", "keyboard")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    AllProductsModel allProductsModel = document.toObject(AllProductsModel.class);
                                    allProductsModelsList.add(allProductsModel);
                                    allProductsAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("phone")) {
            firestore.collection("ShowAll").whereEqualTo("type", "phone")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    AllProductsModel allProductsModel = document.toObject(AllProductsModel.class);
                                    allProductsModelsList.add(allProductsModel);
                                    allProductsAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }
        if (type != null && type.equalsIgnoreCase("camera")) {
            firestore.collection("ShowAll").whereEqualTo("type", "camera")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    AllProductsModel allProductsModel = document.toObject(AllProductsModel.class);
                                    allProductsModelsList.add(allProductsModel);
                                    allProductsAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }

    }
}