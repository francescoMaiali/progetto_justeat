package com.example.franc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int PASSWORD_LENGTH = 6;

    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;
    public static final String WELCOME ="WELCOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setVisibility(View.VISIBLE);

        registerBtn.setOnClickListener(this);

        loginBtn.setOnClickListener(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        RegisterActivity.class);
                startActivity(i);
            }
        });

        Log.i(TAG, "activity created");

    }



    private boolean isValidEmail(){
        String email = emailET.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }


    private boolean isValidPassword(){
        String password = passwordET.getText().toString();
        return (password.length() > PASSWORD_LENGTH);
    }


    private void showErrorMessage(String message){

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        Log.e(TAG,message);
    }



    private void showSuccessMessage(){

        Toast.makeText(this,getString(R.string.login_success),Toast.LENGTH_LONG)
                .show();
        Log.i(TAG,getString(R.string.login_success));
    }



    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_btn){
            /*
            if(isValidPassword() && isValidEmail()){
                showSuccessMessage();
            }else if (isValidPassword() && !isValidEmail()) {
                showErrorEmail();
            }else if (!isValidPassword() && isValidEmail()) {
                showErrorPassword();
            }else {
                showErrorMessage();
            }*/

            if(!isValidEmail()){
                showErrorMessage(getString(R.string.email_error));
                return;
            }
            if(!isValidPassword()){
                showErrorMessage(getString(R.string.password_error));
                return;
            }

            showSuccessMessage();
            Intent i = new Intent(this,WelcomeActivity.class);
            String mail = emailET.getText().toString();
            i.putExtra(WELCOME,mail);
            startActivity(i);


        }else if(view.getId() == R.id.register_btn){

            Intent i = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(i);
        }

    }
}
