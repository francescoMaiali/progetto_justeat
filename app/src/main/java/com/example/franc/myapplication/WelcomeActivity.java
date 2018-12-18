package com.example.franc.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    FoodAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_welcome);

        TextView welcomeTW= findViewById(R.id.welcome_tv);

        welcomeTW.setOnClickListener(this);

        String mail= getIntent().getStringExtra(MainActivity.WELCOME);

        welcomeTW.setText(getString(R.string.welcome)+" "+mail);

        recyclerView = findViewById(R.id.food_rv);

        layoutManager = new LinearLayoutManager(this);

        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(new Food("Milk", "1.00€", "0"));
        foodList.add(new Food("bread", "1.00€", "0"));
        foodList.add(new Food("eggs", "1.00€", "0"));
        foodList.add(new Food("juice", "1.00€", "0"));
        foodList.add(new Food("cream", "1.00€", "0"));


        adapter = new FoodAdapter(this,foodList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void onClick(View view) {
        if (view.getId() == R.id.welcome_tv) {
            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getIntent().getStringExtra("WELCOME"), null));
            startActivity(Intent.createChooser(i, "scegli un email client:"));
        }

    }
}
