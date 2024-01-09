package com.example.tha4shop_nhom4.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tha4shop_nhom4.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentAct extends AppCompatActivity implements PaymentResultListener {
    Toolbar toolbar;
    TextView subTotal, discount, shipping, total,total_usd;
    Button paymentBtn;
    double vnd = 0.0, ship= 0.0, disc = 0.0, theTotal;
    double amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //Tool bar
        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vnd = getIntent().getDoubleExtra("amount", 0.0);

        subTotal = findViewById(R.id.sub_total);
        discount = findViewById(R.id.textView17);
        shipping = findViewById(R.id.textView18);
        total = findViewById(R.id.total_amt);
        total_usd = findViewById(R.id.total_amt_usd);
        paymentBtn = findViewById(R.id.pay_btn);

        theTotal = vnd  - disc + ship;

        
        amount = Math.round((theTotal / 24380)*100.0)/100.0 ;



        subTotal.setText(vnd + " VNĐ");
        total.setText(theTotal + " VNĐ");
        total_usd.setText(amount + " $");
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMethod();
            }
        });
    }

    private void paymentMethod() {

        Checkout checkout = new Checkout();

        final Activity activity = PaymentAct.this;

        try {
            JSONObject options = new JSONObject();
            //Set Company Name
            options.put("name", "THA4 Shop");
            //Ref no
            options.put("description", "Reference No. #123456");
            //Image to be display
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_9A33XWu170gUtm");
            // Currency type
            options.put("currency", "USD");
            //double total = Double.parseDouble(mAmountText.getText().toString());
            //multiply with 100 to get exact amount in rupee
            amount = amount * 100;
            //amount
            options.put("amount", amount);
            JSONObject preFill = new JSONObject();
            //email
            preFill.put("email", "tha4shop@gmail.com");
            //contact
            preFill.put("contact", "0945447095");

            options.put("prefill", preFill);

            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Đã hủy thanh toán", Toast.LENGTH_SHORT).show();

    }
}