package com.example.digitalcampuscanteen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;

public class AddItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    FirebaseFirestore db;
    FirebaseAuth mAuth;
    Spinner spinner;
    String spinnerValue = "Select Category";

    TextView itemName,itemPrice,itemDescription;
    Button add_item_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        itemName = findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.itemPrice);
        itemDescription = findViewById(R.id.itemDescription);

        spinner = findViewById(R.id.categorySelectSpinner);
        add_item_btn = findViewById(R.id.add_item_btn);

        spinner.setOnItemSelectedListener(this);
        String[] categoryName = getResources().getStringArray(R.array.category);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categoryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        add_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item_name = ""+ itemName.getText().toString();
                String item_price = "" + itemPrice.getText().toString();
                String item_type = spinnerValue;
                String item_des = itemDescription.getText().toString();

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                long time = timestamp.getTime();
                String item_id = "ItemID" + time;

                ItemModel product = new ItemModel(item_id,item_name,item_price,item_type,item_des);


                db.collection("item")
                        .document(item_id)
                        .set(product);

                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                finish();

            }
        });





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.categorySelectSpinner){
            spinnerValue = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}