package com.example.fluentmindss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etemailAddress,etPassword;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView tvSignIn,tvForgetPassword,tvAccountCreations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.mainLayout);
        etemailAddress = findViewById(R.id.etemailaddress1);
        etPassword = findViewById(R.id.etpassword1);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);
        tvAccountCreations = findViewById(R.id.tvaccountcreation);
        tvSignIn = findViewById(R.id.tvSignIn);
        tvSignIn.setOnClickListener(this);

        SpannableString accountCreationSpannableString = new SpannableString("Don't have an account yet? Sign up here");
        ClickableSpan accountCreationClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle the click action here (e.g., redirect to another page)
                // Example: Start a new activity
                // startActivity(new Intent(context, YourSignUpActivity.class));
                Intent i = new Intent(MainActivity.this, SignUpPage.class);
                startActivity(i);
            }
        };

// Set the clickable span to the specific text range
        accountCreationSpannableString.setSpan(accountCreationClickableSpan, accountCreationSpannableString.length() - 4, accountCreationSpannableString.length(), 0);

// Set the modified text to your TextView
        tvAccountCreations.setText(accountCreationSpannableString);

// Make the link clickable
        tvAccountCreations.setMovementMethod(LinkMovementMethod.getInstance());
        tvAccountCreations.setHighlightColor(getResources().getColor(android.R.color.transparent)); // Remove the highlight color
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(MainActivity.this, ForgetAccountsPage.class);
            startActivity(i);
            }
        });



        mAuth = FirebaseAuth.getInstance();

    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvaccountcreation) {
            startActivity(new Intent(this, SignUpPage.class));
        } else if (id == R.id.tvSignIn) {
            userLogin();
        } else if (id == R.id.tvForgetPassword) {
            startActivity(new Intent(this, ForgetAccountsPage.class));
        }

    }

    private void userLogin() {
        String email = etemailAddress.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()){
            etemailAddress.setError("Email is Required!");
            etemailAddress.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etemailAddress.setError("Please Provide valid email!");
            etemailAddress.requestFocus();
            return;
        }
        if (password.isEmpty()){
            etPassword.setError("Password is Required!");
            etPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            etPassword.setError("Min Password length should be 6 characters!");
            etPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()){
                        startActivity(new Intent(MainActivity.this,Homepage.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Check your email to verify your account! ", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}