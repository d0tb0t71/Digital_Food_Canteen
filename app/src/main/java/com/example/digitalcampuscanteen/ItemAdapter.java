package com.example.digitalcampuscanteen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context context;
    ArrayList<ItemModel> list;

    public ItemAdapter(Context context, ArrayList<ItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemModel item = list.get(position);

        holder.itemTitle.setText(item.getItem_name());
        holder.itemPrice.setText(item.getItem_price()+"৳");

        holder.itemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, FoodDetails.class);
                intent.putExtra("id",item.getItem_id());
                context.startActivity(intent);

            }
        });

        holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartModel cartModel = new CartModel(item.getItem_id(), item.getItem_name(), item.getItem_price(), "1");

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("cart")
                        .document("myCart")
                        .collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .document(item.getItem_id())
                        .set(cartModel);


                Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle,itemPrice;
        ImageView add_cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            add_cart = itemView.findViewById(R.id.add_cart);


        }
    }
}

