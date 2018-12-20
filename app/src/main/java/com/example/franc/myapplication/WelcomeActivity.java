package com.example.franc.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class WelcomeActivity extends AppCompatActivity implements FoodAdapter.OnQuantitaChange, View.OnClickListener {
    RecyclerView recyclerView;

     TextView totaleTextView;
     String mail;
     String eseguimail;


    int totale = 0;

    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    FoodAdapter adapter;
    Button btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_welcome);
        totaleTextView = findViewById(R.id.total_shop);
        layoutManager = new LinearLayoutManager(this);
        adapter = new FoodAdapter(this, foodArrayList);
        adapter.setOnQuantitaChange(this);
        recyclerView = findViewById(R.id.food_rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        btn =findViewById(R.id.buy_btn);



        TextView mail_passata = findViewById(R.id.welcome_tv);



        mail = getIntent().getStringExtra("WELCOME");
        mail_passata.setOnClickListener(this);

        getProducts();



        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (intent.getData() != null) {
            eseguimail = Uri.decode(intent.getData().toString().substring(7));

            mail_passata.setText(eseguimail);
        }else mail_passata.setText(mail);


    }

    ArrayList<Food> foodArrayList = new ArrayList<>();


        private void getProducts() {
// Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://5ba19290ee710f0014dd764c.mockapi.io/Food";

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Success", response);
                            try {
                                JSONArray responseJSON = new JSONArray(response);
                                ArrayList<Food> foodArrayList = new ArrayList<>();


                                for (int i =0; i<responseJSON.length(); i++) {

                                    Food food = new Food(responseJSON.getJSONObject(i));
                                    if(food.isDisponibilita()){
                                        foodArrayList.add(food);

                                    }
                                }
                                adapter.setData(foodArrayList);

                                // Display the first 500 characters of the response string.
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error", error.getMessage());

                        }
                    });
            queue.add(stringRequest);

        }





    public void onClick(View view) {
        if (view.getId() == R.id.welcome_tv) {
            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getIntent().getStringExtra("WELCOME"), null));
            startActivity(Intent.createChooser(i, "scegli un email client:"));
        }

    }
    @Override
    public void onItemAdded(float price) {

        totale+= price;
        totaleTextView.setText("Total :" + totale);


        //utton btn =findViewById(R.id.buy_btn);
        if(totale>=5)
            btn.setEnabled(true);


        progressBar = findViewById(R.id.determinateBar);
        progressBar.setMax(5);
        progressBar.setProgress(0);
        progressBar.setProgress(totale);



    }

    @Override
    public void onItemRemoved(float price) {
        if(totale == 0) return;
        totale -= price;
        totaleTextView.setText("Totale :" + totale);


        //Button btn =findViewById(R.id.buy_btn);
        if(totale<5)
        btn.setEnabled(false);

        progressBar = findViewById(R.id.determinateBar);
        progressBar.setMax(5);
        progressBar.setProgress(0);
        progressBar.setProgress(totale);


    }
}
