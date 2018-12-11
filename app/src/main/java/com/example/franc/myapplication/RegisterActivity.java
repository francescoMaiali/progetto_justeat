package com.example.franc.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements OnClickListener {
    private static final int PASSWORD_LENGHT = 6;
    private static final String TAG = "RegisterActivity" ;
    EditText passwordRegister;
    EditText emailRegister;
    EditText phoneRegister;
    private static final int PHONE_LENGHT = 10;

    Button RegistratiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);





        Log.i(TAG, "activity created");
    }
    private boolean isValidPassword() {
        String password = passwordRegister.getText().toString();
        return (password.length() > PASSWORD_LENGHT);
    }
    private boolean isValidEmail() {
        return Patterns.EMAIL_ADDRESS.matcher(emailRegister.getText()).matches();

    }
    private boolean isValidPhoneNumber() {
        String phoneNumber = phoneRegister.getText().toString();
        return (phoneNumber.length() == PHONE_LENGHT);

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

    }
}
