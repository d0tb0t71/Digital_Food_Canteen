package com.example.digitalcampuscanteen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class OrderDetails extends AppCompatActivity {

    TextView details_id,order_details,order_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        details_id = findViewById(R.id.details_id);
        order_details = findViewById(R.id.order_details);
        order_price = findViewById(R.id.order_price);

        String id = getIntent().getStringExtra("id");


       FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference = db.collection("orders")
                .document("myOrders")
                .collection("orderList").document(id);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                details_id.setText(""+value.getString("orderID"));
                String order = (value.getString("order"));
                order_price.setText("Order Price : "+value.getString("totalModel"));


                System.out.println(order);

                order = order.replace("\n", System.getProperty("line.separator"));

                order_details.setText(order);


            }
        })
        ;


    }
}