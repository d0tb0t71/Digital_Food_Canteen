package com.example.digitalcampuscanteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    ImageView food_btn,fitness_btn,cart_btn,profile_btn;
    TextView food_tv,fitness_tv,cart_tv,profile_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        food_btn = findViewById(R.id.food_btn);
        fitness_btn = findViewById(R.id.fitness_btn);
        cart_btn = findViewById(R.id.cart_btn);
        profile_btn = findViewById(R.id.profile_btn);


        food_tv = findViewById(R.id.food_tv);
        fitness_tv = findViewById(R.id.fitness_tv);
        cart_tv = findViewById(R.id.cart_tv);
        profile_tv = findViewById(R.id.profile_tv);


        replaceFragment(new FoodFragment());


        food_btn.setOnClickListener(v->{

            replaceFragment(new FoodFragment());

        });
        fitness_btn.setOnClickListener(v->{

            replaceFragment(new FitnessFragment());

        });
        cart_btn.setOnClickListener(v->{

            replaceFragment(new CartFragment());

        });
        profile_btn.setOnClickListener(v->{

            replaceFragment(new ProfileFragment());

        });
    }

    void replaceFragment(Fragment fragment){


        String frag = fragment.getClass().toString();

        if(frag.contains("FoodFragment")){
            food_tv.setVisibility(View.VISIBLE);
            fitness_tv.setVisibility(View.GONE);
            cart_tv.setVisibility(View.GONE);
            profile_tv.setVisibility(View.GONE);

        }
        else if(frag.contains("FitnessFragment")){
            food_tv.setVisibility(View.GONE);
            fitness_tv.setVisibility(View.VISIBLE);
            cart_tv.setVisibility(View.GONE);
            profile_tv.setVisibility(View.GONE);


        }else if(frag.contains("CartFragment")){
            food_tv.setVisibility(View.GONE);
            fitness_tv.setVisibility(View.GONE);
            cart_tv.setVisibility(View.VISIBLE);
            profile_tv.setVisibility(View.GONE);
        }
        else if(frag.contains("ProfileFragment")){
            food_tv.setVisibility(View.GONE);
            fitness_tv.setVisibility(View.GONE);
            cart_tv.setVisibility(View.GONE);
            profile_tv.setVisibility(View.VISIBLE);

        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }
}