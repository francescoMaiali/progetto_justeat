package com.example.franc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int PASSWORD_LENGHT = 6;


    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$";

        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setVisibility(View.VISIBLE);

        registerBtn.setOnClickListener(this);

        loginBtn.setOnClickListener(this);

        Log.i(TAG, "activity created");
    }

    private boolean isValidEmail() {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailET.getText()).matches();
    }


    private void showErroMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        Log.e(TAG, message);


    }

    /**
     * @return
     */
    private boolean isValidPassword() {
        String password = passwordET.getText().toString();
        return (password.length() > PASSWORD_LENGHT);
    }

    private void showSuccesMessage() {
        Context context = getApplicationContext();
        CharSequence text = "login successfull";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Log.i(TAG, getString(R.string.login_success));
    }


    @Override
    public void onClick(View view) {


        //does something
        if (view.getId() == R.id.login_btn) {
            if (!isValidEmail()) {
                showErroMessage(getString(R.string.email_error));
                return;
            }
            if (!isValidPassword()) {
                showErroMessage(getString(R.string.password_error));
                return;
            }
            showSuccesMessage();

        } else if (view.getId() == R.id.register_btn) {
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }
}