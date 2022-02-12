package com.example.digitalcampuscanteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInPage extends AppCompatActivity {

    Button signup,gosignin;
    EditText log_email,log_pass;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);


        mAuth = FirebaseAuth.getInstance();

        signup = findViewById(R.id.signup);
        gosignin = findViewById(R.id.gosignin);

        log_email = findViewById(R.id.log_email);
        log_pass = findViewById(R.id.log_pass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = log_email.getText().toString();
                String pass = log_pass.getText().toString();


                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        FirebaseUser user = mAuth.getCurrentUser();
                        if(task.isSuccessful() ){
                            if(user.isEmailVerified()){
                                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                            }
                            else
                            {
                                startActivity(new Intent(getApplicationContext(),VerifyEmail.class));
                            }

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Login Failed \n"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}