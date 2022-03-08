package com.example.digitalcampuscanteen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EditItem extends AppCompatActivity {

    EditText edit_name,edit_price,edit_des;
    Button update_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);


        edit_name = findViewById(R.id.edit_name);
        edit_price = findViewById(R.id.edit_price);
        edit_des = findViewById(R.id.edit_des);
        update_item = findViewById(R.id.update_item);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String itemId = getIntent().getStringExtra("id");

        DocumentReference documentReference = db.collection("item").document(itemId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                edit_name.setText(""+value.getString("item_name"));
                edit_des.setText(""+value.getString("item_des"));
                edit_price.setText(""+value.getString("item_price"));

            }
        })
        ;

        update_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edit_name.getText().toString();
                String price = edit_price.getText().toString();
                String des = edit_des.getText().toString();

                db.collection("item")
                        .document(itemId)
                        .update(
                                "item_name",name,
                                "item_price",price,
                                "item_des",des
                        );

                Toast.makeText(getApplicationContext(), "Item Updated Successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                finish();

            }
        });




    }
}