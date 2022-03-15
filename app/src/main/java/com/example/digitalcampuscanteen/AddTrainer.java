package com.example.digitalcampuscanteen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.sql.Timestamp;

public class AddTrainer extends AppCompatActivity {

    EditText tName,tAge,tMobile,tEmail,tDes;
    Button add_trainer_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trainer);

        tName = findViewById(R.id.tName);
        tAge = findViewById(R.id.tAge);
        tMobile = findViewById(R.id.tMobile);
        tEmail = findViewById(R.id.tEmail);
        tDes = findViewById(R.id.tDes);

        add_trainer_btn = findViewById(R.id.add_trainer_btn);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


                String status = "" + value.getString("userStaus");

                if(status.equals("customer")){
                    add_trainer_btn.setVisibility(View.GONE);
                }


            }
        })
        ;


        add_trainer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = tName.getText().toString();
                String age = tAge.getText().toString();
                String mobile = tMobile.getText().toString();
                String email = tEmail.getText().toString();
                String des = tDes.getText().toString();


                if(name.length()>5 && age.length()>0 && mobile.length()>10 && email.length()>5 && des.length()>3){

                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    long time = timestamp.getTime();
                    String trainer_id = "T" + time;

                    TrainerModel trainerModel = new TrainerModel(trainer_id,name,age,mobile,email,des);


                    db.collection("trainer")
                            .document(trainer_id)
                            .set(trainerModel);

                    startActivity(new Intent(getApplicationContext(),TrainerPage.class));
                    finish();
                }else
                {
                    Toast.makeText(getApplicationContext(), "Enter correct information", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}