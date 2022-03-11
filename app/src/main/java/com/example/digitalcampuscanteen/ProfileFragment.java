package com.example.digitalcampuscanteen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class ProfileFragment extends Fragment {

    View view;
    ImageView profile_pic;
    TextView pfullname,pmobile,pemail;
    Button pedit_btn,logout_btn,my_orders;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_profile, container, false);


        //write here
        pfullname = view.findViewById(R.id.pfullname);
        profile_pic = view.findViewById(R.id.profile_pic);
        pmobile = view.findViewById(R.id.pmobile);
        pemail = view.findViewById(R.id.pemail);

        logout_btn = view.findViewById(R.id.logout_btn);
        pedit_btn = view.findViewById(R.id.pedit_btn);
        my_orders = view.findViewById(R.id.my_orders);

        mAuth =FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        String uid = user.getUid();


        DocumentReference documentReference = db.collection("users").document(uid);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                pfullname.setText(""+value.getString("name"));
                pmobile.setText("Mobile : "+value.getString("mobile"));
                pemail.setText("Email: "+value.getString("email"));


            }
        })
        ;


        my_orders.setOnClickListener(v->{

            startActivity(new Intent(getContext(),MyOrders.class));
            getActivity().finish();

        });

        pedit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),EditProfile.class));

            }
        });

        logout_btn.setOnClickListener(v->{

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getContext(),MainActivity.class));
            getActivity().finish();

        });





        return view;
    }
}