package com.example.digitalcampuscanteen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class FoodDetails extends AppCompatActivity {

    TextView item_price,item_title,item_description;
    Button edit_item,delete_item,add_to_cart;

    String itemName= "" ,itemDes = "" , itemPrice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);


        String itemId = getIntent().getStringExtra("id");

        item_price = findViewById(R.id.item_price);
        item_title = findViewById(R.id.item_title);
        item_description = findViewById(R.id.item_description);
        edit_item = findViewById(R.id.edit_item);
        delete_item = findViewById(R.id.delete_item);
        add_to_cart = findViewById(R.id.add_to_cart);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference1 = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference1.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String st = value.getString("userStatus");

                if(st.equals("admin")){
                    edit_item.setVisibility(View.VISIBLE);
                    delete_item.setVisibility(View.VISIBLE);
                }else if(st.equals("customer")){
                    add_to_cart.setVisibility(View.VISIBLE);
                }


            }
        })
        ;


        DocumentReference documentReference = db.collection("item").document(itemId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                item_title.setText(""+value.getString("item_name"));
                item_description.setText("Description : "+value.getString("item_des"));
                item_price.setText("Item Price : "+value.getString("item_price") + "$");

                itemName = ""+value.getString("item_name");
                itemDes = ""+value.getString("item_des");
                itemPrice = ""+value.getString("item_price");

            }
        })
        ;

        edit_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EditItem.class);
                intent.putExtra("id",itemId);
                startActivity(intent);
                finish();

            }
        });


        delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("item")
                        .document(itemId)
                        .delete();

                Toast.makeText(getApplicationContext(), "Item Deleted Successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                finish();

            }
        });

        delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("item")
                        .document(itemId)
                        .delete();

                Toast.makeText(getApplicationContext(), "Item Deleted Successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                finish();

            }
        });

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartModel cartModel = new CartModel(itemId,itemName,itemPrice,"1");

                db.collection("cart")
                        .document("myCart")
                        .collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .document(itemId)
                        .set(cartModel);

                Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                finish();

            }
        });


    }
}