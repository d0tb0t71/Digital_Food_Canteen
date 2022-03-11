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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    ArrayList<OrderModel> list;

    public OrderAdapter(Context context, ArrayList<OrderModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.order_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderModel item = list.get(position);

        holder.order_id.setText(item.getOrderID());
        holder.order_status.setText(item.getOrderStatus());

        holder.order_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, OrderDetails.class);
                intent.putExtra("id",item.getOrderID());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView order_id,order_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            order_id = itemView.findViewById(R.id.order_id);
            order_status = itemView.findViewById(R.id.order_status);


        }
    }
}