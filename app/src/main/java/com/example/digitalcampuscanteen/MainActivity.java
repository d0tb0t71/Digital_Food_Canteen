package com.example.digitalcampuscanteen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button go_sign_in, go_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        go_sign_in = findViewById(R.id.go_sign_in);
        go_sign_up = findViewById(R.id.go_sign_up);

        go_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),SignInPage.class));


            }
        });

        go_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),SignUpPage.class));


            }
        });


    }
}