package com.example.digitalcampuscanteen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    ArrayList<CartModel> list;

    public CartAdapter(Context context, ArrayList<CartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_cart_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CartModel cartModel = list.get(position);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        holder.name.setText(""+cartModel.getItem_name());
        holder.price.setText("Item Price : "+cartModel.getItem_price());


        cartModel.setItem_quantity(holder.quantity_tv.getText().toString());



        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = holder.quantity_tv.getText().toString();
                int quantity = Integer.parseInt(s);


                quantity++;

                holder.quantity_tv.setText(String.valueOf(quantity));
                cartModel.setItem_quantity(String.valueOf(quantity));

            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = holder.quantity_tv.getText().toString();
                int quantity = Integer.parseInt(s);

                quantity--;


                holder.quantity_tv.setText(String.valueOf(quantity));
                cartModel.setItem_quantity(String.valueOf(quantity));

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,quantity_tv;
        Button plus,minus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            plus = itemView.findViewById(R.id.item_plus);
            minus = itemView.findViewById(R.id.item_minus);
            quantity_tv = itemView.findViewById(R.id.quantity_tv);


        }
    }
}
