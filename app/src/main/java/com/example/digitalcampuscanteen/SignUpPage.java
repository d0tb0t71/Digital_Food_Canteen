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
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpPage extends AppCompatActivity {

    EditText fullname,email,password,confirmpassword,mobile;
    Button signup,gosignin;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        mobile = findViewById(R.id.mobile);

        signup = findViewById(R.id.signup);
        gosignin = findViewById(R.id.gosignin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString();
                String Name = fullname.getText().toString();
                String Pass = password.getText().toString();
                String Confirm_pass = confirmpassword.getText().toString();
                String Mobile = mobile.getText().toString();


                if(Email.length()>0 && Name.length()>0 && Pass.length()>0 && Confirm_pass.length()>0 && Mobile.length()>0){
                    mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){


                                FirebaseUser user = mAuth.getCurrentUser();

                                UserModel userModel = new UserModel(user.getUid(),"",Name,Email,Mobile,"admin");

                                db = FirebaseFirestore.getInstance();


                                db.collection("users")
                                        .document(user.getUid())
                                        .set(userModel);

                                startActivity(new Intent(getApplicationContext(),VerifyEmail.class));

                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Registration Failed\n"+task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Registration Failed !\n"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }







            }
        });

        gosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignInPage.class));
                finish();
            }
        });



    }
}