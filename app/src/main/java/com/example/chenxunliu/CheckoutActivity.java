package com.example.chenxunliu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * CheckoutActivity
 *
 * @date 2019-11-26
 */
public class CheckoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("Library_CHENXUNLIU");
        findViewById(R.id.checkoutRecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckoutActivity.this,CheckoutRecordActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.checkoutSummary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckoutActivity.this,CheckoutSummaryActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.checkoutReturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckoutActivity.this,CheckoutReturnActivity.class);
                startActivity(i);
            }
        });
    }
}
