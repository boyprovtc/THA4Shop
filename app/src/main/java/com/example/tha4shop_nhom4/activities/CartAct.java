package com.example.tha4shop_nhom4.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tha4shop_nhom4.R;
import com.example.tha4shop_nhom4.adapters.MyCartAdapter;
import com.example.tha4shop_nhom4.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartAct extends AppCompatActivity {
    Toolbar toolbar;
    TextView overAllAmount;
    RecyclerView recyclerView;
    List<MyCartModel> cartModelList;
    MyCartAdapter myCartAdapter;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    int OverAlltotalAmount;
    Button btnBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.my_cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Get data from my cart
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver, new IntentFilter("MyTotalAmount"));
        overAllAmount = findViewById(R.id.total_price);
        recyclerView = findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(this, cartModelList);
        recyclerView.setAdapter(myCartAdapter);
        btnBuyNow = findViewById(R.id.buy_now);
        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                MyCartModel myCartModel = document.toObject(MyCartModel.class);
                                String documentId = document.getId();
                                myCartModel.setDocumentId(documentId);
                                cartModelList.add(myCartModel);
                                myCartAdapter.notifyDataSetChanged();
                            }
                        }

                    }
                });
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount", 0);

            // Trước khi sử dụng, bạn cần khởi tạo NumberFormat cho Locale tiếng Việt
            NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
            overAllAmount.setText("Tổng: " + numberFormat.format(totalBill) + " VNĐ");
        }
    };
}