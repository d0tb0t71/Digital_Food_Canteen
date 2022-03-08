package com.example.digitalcampuscanteen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class FoodFragment extends Fragment {


    View view;
    FloatingActionButton add_item;
    RecyclerView food_recyclerview;
    ItemAdapter itemAdapter;
    ArrayList<ItemModel> list;

    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_food, container, false);

        //write here

        db = FirebaseFirestore.getInstance();
        add_item = view.findViewById(R.id.add_item);
        food_recyclerview = view.findViewById(R.id.food_recyclerview);
        food_recyclerview.setHasFixedSize(true);
        food_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        list = new ArrayList<ItemModel>();
        itemAdapter =new ItemAdapter(getContext(),list);
        food_recyclerview.setAdapter(itemAdapter);


        DocumentReference documentReference = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                String st = value.getString("userStatus");

                if(st.equals("admin")){
                    add_item.setVisibility(View.VISIBLE);
                }

            }
        })
        ;

        db.collection("item")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Toast.makeText(getContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        for(DocumentChange dc : value.getDocumentChanges()){

                            if(dc.getType() == DocumentChange.Type.ADDED){
                                list.add(dc.getDocument().toObject(ItemModel.class));
                            }

                            itemAdapter.notifyDataSetChanged();

                        }

                    }
                });



        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),AddItem.class));

            }
        });


        return view;
    }
}