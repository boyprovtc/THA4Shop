package com.example.tha4shop_nhom4.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tha4shop_nhom4.R;
import com.example.tha4shop_nhom4.adapters.ShowAllAdapter;
import com.example.tha4shop_nhom4.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllAct extends AppCompatActivity {
    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelsList;

    Toolbar toolbar;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        toolbar = findViewById(R.id.show_all_toolsbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String type = getIntent().getStringExtra("type");

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        showAllModelsList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this, showAllModelsList);
        recyclerView.setAdapter(showAllAdapter);

        if (type == null || type.isEmpty()) {
            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                    showAllModelsList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }

        if (type!= null && type.equalsIgnoreCase("laptop")){
            firestore.collection("ShowAll").whereEqualTo("type","laptop")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                    showAllModelsList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }
        if (type!= null && type.equalsIgnoreCase("mouse")){
            firestore.collection("ShowAll").whereEqualTo("type","mouse")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                    showAllModelsList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }
        if (type!= null && type.equalsIgnoreCase("watch")){
            firestore.collection("ShowAll").whereEqualTo("type","watch")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                    showAllModelsList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }
        if (type!= null && type.equalsIgnoreCase("keyboard")){
            firestore.collection("ShowAll").whereEqualTo("type","keyboard")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                    showAllModelsList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }
        if (type!= null && type.equalsIgnoreCase("phone")){
            firestore.collection("ShowAll").whereEqualTo("type","phone")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                    showAllModelsList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }
        if (type!= null && type.equalsIgnoreCase("camera")){
            firestore.collection("ShowAll").whereEqualTo("type","camera")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                    showAllModelsList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();


                                }
                            }
                        }
                    });
        }

    }
}