package com.example.digitalcampuscanteen;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CartFragment extends Fragment {


    RecyclerView order_rcyclerview;
    Button order_btn;
    CartAdapter parcelAdapter;
    ArrayList<CartModel> list;
    FirebaseFirestore db;


 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     //write here
     View view = inflater.inflate(R.layout.fragment_cart, container, false);

     order_rcyclerview = view.findViewById(R.id.order_rcyclerview);
     order_btn = view.findViewById(R.id.order_btn);

     LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
     layoutManager.setReverseLayout(true);
     layoutManager.setStackFromEnd(true);
     order_rcyclerview.setLayoutManager(layoutManager);

     list = new ArrayList<>();

     parcelAdapter = new CartAdapter(getContext(),list);
     order_rcyclerview.setAdapter(parcelAdapter);

     db = FirebaseFirestore.getInstance();



     db.collection("cart")
             .document("myCart")
             .collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
             .addSnapshotListener(new EventListener<QuerySnapshot>() {
                 @Override
                 public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                     if(error != null){
                         Toast.makeText(getContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                         return;
                     }

                     for(DocumentChange dc : value.getDocumentChanges()){

                         if(dc.getType() == DocumentChange.Type.ADDED){
                             list.add(dc.getDocument().toObject(CartModel.class));
                         }

                         parcelAdapter.notifyDataSetChanged();

                     }

                 }
             });


     order_btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             String orderList = "";
             int totalPrice = 0;

             for (CartModel item : list)
             {
                 String name = "Item Name : "+item.getItem_name()+"\n";
                 String quantity = "Item Quantity : "+item.getItem_quantity()+"\n";
                 String price = item.getItem_price();

                 int p = Integer.parseInt(price);
                 int q = Integer.parseInt(item.getItem_quantity());

                 int pq = p*q;

                 totalPrice += pq;

                 String inPrice = "Price : " + String.valueOf(p * q);


                 orderList+= name;
                 orderList+="\\n";
                 orderList+= quantity;
                 orderList+="\\n";
                 orderList+= inPrice;
                 orderList+="\\n";

                 db.collection("cart")
                         .document("myCart")
                         .collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                         .document(item.getitem_id())
                         .delete();



             }

             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
             long time = timestamp.getTime();
             String orderID = "orderID" + time;


             String total = "Total Price : " + String.valueOf(totalPrice);
             OrderModel orderModel = new OrderModel(orderID,FirebaseAuth.getInstance().getCurrentUser().getUid(),orderList,"placed",total);


             db.collection("orders")
                     .document("myOrders")
                     .collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                     .document(orderID)
                     .set(orderModel);


             list.clear();





             Toast.makeText(getContext(), "Order Placed", Toast.LENGTH_SHORT).show();


         }
     });



     return view;

    }
}