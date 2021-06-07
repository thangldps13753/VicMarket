package com.example.vicmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vicmarket.R;
import com.example.vicmarket.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    Context context;
    List<MyCartModel> list;

    int totalPrice = 0;

    public MyCartAdapter(Context context, List<MyCartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mycart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText(String.valueOf(list.get(position).getPrice()));
        holder.date.setText(list.get(position).getDate());
        holder.time.setText(list.get(position).getTime());
        holder.soLuong.setText(list.get(position).getTotalSoLuong());
        holder.tongTien.setText(String.valueOf(list.get(position).getTotalTongTien()));

        totalPrice = totalPrice+ list.get(position).getTotalTongTien();
        Intent i = new Intent("MyOrder");
        i.putExtra("totalAmount",totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,price,date,time,soLuong,tongTien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_mycart);
            price = itemView.findViewById(R.id.price_mycart);
            date = itemView.findViewById(R.id.date_mycart);
            time = itemView.findViewById(R.id.time_mycart);
            soLuong = itemView.findViewById(R.id.soLuong_mycart);
            tongTien = itemView.findViewById(R.id.tongTien_mycart);
        }
    }
}
