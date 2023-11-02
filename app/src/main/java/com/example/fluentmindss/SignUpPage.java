package com.example.fluentmindss;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpPage extends AppCompatActivity implements View.OnClickListener {
    Button btnSignUp, btnBack;

    EditText etusername, etemailaddress, etPassword;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etemailaddress = findViewById(R.id.tvEmailAddressCreation);
        etusername = findViewById(R.id.tvUsernameCreation);
        etPassword = findViewById(R.id.tvPassword2);


        btnBack = findViewById(R.id.btnBack);
        btnSignUp = findViewById(R.id.btnsignup);
btnSignUp.setOnClickListener(this);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpPage.this, MainActivity.class);
                startActivity(i);
            }
        });
    };

        public void onClick(View view){
            if (view.getId() == R.id.btnsignup) {
                registerUser();
            }
        }


        private void registerUser () {
            String email = etemailaddress.getText().toString().trim();
            String username = etusername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty()) {
                etusername.setError("Full Name is Required!");
                etusername.requestFocus();
                return;
            }
            if (email.isEmpty()) {
                etemailaddress.setError("Email is Required!");
                etemailaddress.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etemailaddress.setError("Please Provide valid email!");
                etemailaddress.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                etPassword.setError("Password is Required!");
                etPassword.requestFocus();
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Min Password length should be 6 characters!");
                etPassword.requestFocus();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpPage.this, "Registration created successfully", Toast.LENGTH_LONG).show();
                        String userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("users").document(userID);
                        Map<String, Object> user = new HashMap<>();
                        user.put("Username", username);
                        user.put("Email", email);
                        user.put("Password", password);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "onSuccess: User profile is created for " + userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "OnFailure: " + e.toString());
                            }
                        });
                        Intent mainIntent = new Intent(SignUpPage.this, MainActivity.class);
                        mainIntent.putExtra("username", username);
                        startActivity(mainIntent);
                        SharedPreferences prefs = getSharedPreferences("com.example.fluentmindss", Context.MODE_PRIVATE);
                        prefs.edit().putString("name",username).apply();

                    } else {
                        Toast.makeText(SignUpPage.this, "Registration is Unsuccessful. Please Try Again", Toast.LENGTH_LONG).show();
                    }
                }


            });

    }
}
