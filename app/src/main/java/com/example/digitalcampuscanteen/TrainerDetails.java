package com.example.digitalcampuscanteen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TrainerDetails extends AppCompatActivity {

    TextView t_name,t_age,t_mobile,t_email,t_des;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_details);

        t_name = findViewById(R.id.t_name);
        t_age = findViewById(R.id.t_age);
        t_mobile = findViewById(R.id.t_mobile);
        t_email = findViewById(R.id.t_email);
        t_des = findViewById(R.id.t_des);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String id = getIntent().getStringExtra("id");

        DocumentReference documentReference1 = db.collection("trainer").document(id);
        documentReference1.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                t_name.setText(""+value.getString("tName"));
                t_age.setText("Age : "+value.getString("tAge"));
                t_mobile.setText("Mobile : "+value.getString("tMobile"));
                t_email.setText("Email : "+value.getString("tEmail"));
                t_des.setText("Short Description : "+value.getString("tDes"));


            }
        })
        ;

    }
}