package com.example.digitalcampuscanteen;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FitnessFragment extends Fragment {

    TextView excercise_list,trainer_list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_fitness, container, false);
        //write here

        excercise_list = view.findViewById(R.id.excercise_list);
        trainer_list = view.findViewById(R.id.trainer_list);

        excercise_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),ExcercisePage.class));

            }
        });

        trainer_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),TrainerPage.class));

            }
        });



        return view;

    }
}