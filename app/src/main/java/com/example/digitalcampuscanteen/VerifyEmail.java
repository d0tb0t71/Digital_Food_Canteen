package com.example.digitalcampuscanteen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyEmail extends AppCompatActivity {


    Button verified_btn;
    TextView verify_email_tv;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);


        verified_btn = findViewById(R.id.verified_btn);
        verify_email_tv = findViewById(R.id.verify_email_tv);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        verified_btn.setOnClickListener(v->{





            user.reload();


            if(user.isEmailVerified()){
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
            }
            else{
                user.sendEmailVerification();
                Toast.makeText(getApplicationContext(), "An email has ben sent please verify", Toast.LENGTH_LONG).show();
            }






        });



    }
}