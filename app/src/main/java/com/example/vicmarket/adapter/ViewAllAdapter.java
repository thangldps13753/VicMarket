package com.example.vicmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vicmarket.R;
import com.example.vicmarket.activities.ChiTietSanPhamActivity;
import com.example.vicmarket.models.TrangChuTheLoaiModel;
import com.example.vicmarket.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {
    Context context;
    List<ViewAllModel> list;

    public ViewAllAdapter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewall,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.img);
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText(String.valueOf(list.get(position).getPrice() + "/kg"));
        if (list.get(position).getType().equals("eggs")) {
            holder.price.setText(list.get(position).getPrice() + "/trứng");
        }
        if (list.get(position).getType().equals("drinks")) {
            holder.price.setText(list.get(position).getPrice() + "/lon");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChiTietSanPhamActivity.class);
                i.putExtra("details",list.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_viewall);
            title = itemView.findViewById(R.id.title_viewall);
            price = itemView.findViewById(R.id.price_viewall);
        }
    }
}
