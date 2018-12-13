package com.example.franc.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_welcome);

        TextView welcomeTW= findViewById(R.id.welcome_tv);

        welcomeTW.setOnClickListener(this);

        String mail= getIntent().getStringExtra(MainActivity.WELCOME);

        welcomeTW.setText(getString(R.string.welcome)+" "+mail);
    }
    public void onClick(View view) {
        if (view.getId() == R.id.welcome_tv) {
            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getIntent().getStringExtra("WELCOME"), null));
            startActivity(Intent.createChooser(i, "scegli un email client:"));
        }

    }
}
