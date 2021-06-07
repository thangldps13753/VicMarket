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
import com.example.vicmarket.activities.ViewAllActivity;
import com.example.vicmarket.models.SanPhamPhoBienModel;

import java.util.List;

public class SanPhamPhoBienAdapter extends RecyclerView.Adapter<SanPhamPhoBienAdapter.ViewHolder> {
    Context context;
    List<SanPhamPhoBienModel> list;

    public SanPhamPhoBienAdapter(Context context, List<SanPhamPhoBienModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SanPhamPhoBienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphamphobien,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamPhoBienAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.img);
        holder.title.setText(list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ViewAllActivity.class);
                i.putExtra("type",list.get(position).getType());
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
        TextView title,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.pop_img);
            title = itemView.findViewById(R.id.pop_tit);
        }
    }
}
