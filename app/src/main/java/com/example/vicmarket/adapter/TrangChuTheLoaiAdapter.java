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
import com.example.vicmarket.models.TrangChuTheLoaiModel;

import java.util.List;

public class TrangChuTheLoaiAdapter extends RecyclerView.Adapter<TrangChuTheLoaiAdapter.ViewHolder> {
    Context context;
    List<TrangChuTheLoaiModel> list;

    public TrangChuTheLoaiAdapter(Context context, List<TrangChuTheLoaiModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TrangChuTheLoaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trangchutheloai,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrangChuTheLoaiAdapter.ViewHolder holder, int position) {
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
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.tv_img);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
}
