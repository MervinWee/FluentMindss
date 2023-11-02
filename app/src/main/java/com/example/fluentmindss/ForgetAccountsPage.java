package com.example.fluentmindss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetAccountsPage extends AppCompatActivity {
    EditText etusername,etemailaddress;
    Button btnBack,btnsendcode;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_accounts_page);

        etemailaddress = findViewById(R.id.etemailaddress);
        etusername = findViewById(R.id.etusername);

        btnsendcode = findViewById(R.id.btnSendCode);

        etusername.setOnClickListener((View.OnClickListener) this);
        btnsendcode.setOnClickListener((View.OnClickListener) this);

        btnBack = findViewById(R.id.btnback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgetAccountsPage.this, MainActivity.class);
                startActivity(i);
            }
        });
        auth = FirebaseAuth.getInstance();
    }
   
    public void onClick(View v) {
        if (v.getId() == R.id.btnSendCode) {
            resetPassword();
          
        }

    }

    private void resetPassword() {
        String email = etemailaddress.getText().toString().trim();

        if (email.isEmpty()){
            etemailaddress.setError("Email is Required!");
            etemailaddress.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etemailaddress.setError("Please Provide valid email!");
            etemailaddress.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetAccountsPage.this,"Check your email to reset your password!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ForgetAccountsPage.this,"Error, Something wrong happened. Please try again!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}