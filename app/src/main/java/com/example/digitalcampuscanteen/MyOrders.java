package com.example.digitalcampuscanteen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyOrders extends AppCompatActivity {

    RecyclerView order_recyclerview;
    OrderAdapter orderAdapter;
    ArrayList<OrderModel> list;
    FirebaseFirestore db;
    String myStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        order_recyclerview = findViewById(R.id.order_recycler);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        order_recyclerview.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        orderAdapter = new OrderAdapter(getApplicationContext(), list);
        order_recyclerview.setAdapter(orderAdapter);

        db = FirebaseFirestore.getInstance();

        DocumentReference documentReference1 = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference1.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String st = value.getString("userStatus");

                if (st.equals("admin")) {
                    myStatus = "admin";
                    System.out.println("=======I am =========="+ myStatus);
                } else if (st.equals("customer")) {
                    myStatus = "customer";
                    System.out.println("=========I am ========"+ myStatus);
                }


            }
        })
        ;


        db.collection("orders")
                .document("myOrders")
                .collection("orderList")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Toast.makeText(getApplicationContext(), "Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {

                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                OrderModel orderModel = dc.getDocument().toObject(OrderModel.class);



                                if(myStatus.equals("admin")){
                                    list.add(dc.getDocument().toObject(OrderModel.class));
                                }
                                else{
                                    OrderModel parcelModel = dc.getDocument().toObject(OrderModel.class);

                                    if(parcelModel.getUid().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                                        list.add(dc.getDocument().toObject(OrderModel.class));
                                    }
                                }

                            }

                            orderAdapter.notifyDataSetChanged();

                        }

                    }
                });


    }
}