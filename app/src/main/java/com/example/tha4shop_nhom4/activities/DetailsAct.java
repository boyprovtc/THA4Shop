package com.example.tha4shop_nhom4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.tha4shop_nhom4.R;
import com.example.tha4shop_nhom4.models.NewProductsModel;
import com.example.tha4shop_nhom4.models.PopularProductsModel;
import com.example.tha4shop_nhom4.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailsAct extends AppCompatActivity {

    ImageView detailedImg;
    TextView rating, name, description, price, quantity;
    Button addToCart, buynow;
    ImageView addItems, removeItem;
    Toolbar toolbar;
    int totalQuantity = 1;
    int getTotalPrice = 0;
    //new products
    NewProductsModel newProductsModel = null;
    //Popular Product
    PopularProductsModel popularProductsModel = null;

    //Show All Products
    ShowAllModel showAllModel = null;
    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = findViewById(R.id.Detailed_ToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof NewProductsModel) {
            newProductsModel = (NewProductsModel) obj;
        } else if (obj instanceof PopularProductsModel) {
            popularProductsModel = (PopularProductsModel) obj;
        } else if (obj instanceof ShowAllModel) {
            showAllModel = (ShowAllModel) obj;
        }
        detailedImg = findViewById(R.id.detailed_img);
        name = findViewById(R.id.detailed_name);
        rating = findViewById(R.id.rating);
        description = findViewById(R.id.detailed_desc);
        price = findViewById(R.id.detailed_price);
        quantity = findViewById(R.id.quantity);

        addToCart = findViewById(R.id.add_to_cart);
        buynow = findViewById(R.id.buy_now);

        addItems = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        //New Products

        if (newProductsModel != null) {
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            description.setText(newProductsModel.getDescription());
            price.setText(String.valueOf(newProductsModel.getPrice()));
            name.setText(newProductsModel.getName());
            getTotalPrice = newProductsModel.getPrice() * totalQuantity;
        }

        //popular products
        if (popularProductsModel != null) {
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            name.setText(popularProductsModel.getName());
            rating.setText(popularProductsModel.getRating());
            description.setText(popularProductsModel.getDescription());
            price.setText(String.valueOf(popularProductsModel.getPrice()));
            name.setText(popularProductsModel.getName());
            getTotalPrice = popularProductsModel.getPrice() * totalQuantity;

        }
        //Show all products
        if (showAllModel != null) {
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));
            name.setText(showAllModel.getName());
            getTotalPrice = showAllModel.getPrice() * totalQuantity;
        }
        //Buy Now
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsAct.this, AddressAct.class);
                if (newProductsModel != null) {
                    intent.putExtra("item", newProductsModel);
                }
                if (popularProductsModel != null){
                    intent.putExtra("item", popularProductsModel);
                }
                if (showAllModel != null){
                    intent.putExtra("item", showAllModel);
                }
                startActivity(intent);
            }
        });
        //Add To Cart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    if (newProductsModel != null) {
                        getTotalPrice = newProductsModel.getPrice() * totalQuantity;

                    }
                    if (popularProductsModel != null) {
                        getTotalPrice = popularProductsModel.getPrice() * totalQuantity;

                    }
                    if (showAllModel != null) {
                        getTotalPrice = showAllModel.getPrice() * totalQuantity;

                    }
                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));

                    if (newProductsModel != null) {
                        getTotalPrice = newProductsModel.getPrice() * totalQuantity;

                    }
                    if (popularProductsModel != null) {
                        getTotalPrice = popularProductsModel.getPrice() * totalQuantity;

                    }
                    if (showAllModel != null) {
                        getTotalPrice = showAllModel.getPrice() * totalQuantity;

                    }
                }
            }
        });

    }

    private void addToCart() {
        String saveCurrentTime, saveCurrentDate;


        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd MM, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", name.getText().toString());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", getTotalPrice);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("currentDate", saveCurrentDate);


        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailsAct.this, "Added to cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

}