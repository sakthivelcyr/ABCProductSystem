package com.sakthi.abcproductsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LinearLayout add_product = (LinearLayout) findViewById(R.id.add_product);
        LinearLayout list_product = (LinearLayout) findViewById(R.id.list_product);
        LinearLayout contact_us = (LinearLayout) findViewById(R.id.contact_us);

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddProduct.class);
                startActivity(i);
            }
        });

        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ContactUs.class);
                startActivity(i);
            }
        });

        list_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListProduct.class);
                startActivity(i);
            }
        });
    }

}