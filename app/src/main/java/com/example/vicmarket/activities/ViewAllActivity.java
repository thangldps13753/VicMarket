package com.example.vicmarket.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.vicmarket.R;
import com.example.vicmarket.adapter.ViewAllAdapter;
import com.example.vicmarket.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    RecyclerView rcv_viewall;
    ViewAllAdapter viewAllAdapter;
    List<ViewAllModel> viewAllModelList;
    FirebaseFirestore firestore;
    Toolbar toolbar;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        toolbar = findViewById(R.id.toolbar2);
        progressBar = findViewById(R.id.progressbar_viewall);
        progressBar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        rcv_viewall = findViewById(R.id.rcv_viewall);
        rcv_viewall.setVisibility(View.GONE);
        rcv_viewall.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this,viewAllModelList);
        rcv_viewall.setAdapter(viewAllAdapter);

        if(type != null && type.equalsIgnoreCase("fruits")){
            firestore.collection("AllProducts").whereEqualTo("type","fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel model = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(model);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


        if(type != null && type.equalsIgnoreCase("drinks")){
            firestore.collection("AllProducts").whereEqualTo("type","drinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel model = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(model);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rcv_viewall.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("meats")){
            firestore.collection("AllProducts").whereEqualTo("type","meats").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel model = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(model);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rcv_viewall.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("fruits")){
            firestore.collection("AllProducts").whereEqualTo("type","fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel model = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(model);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rcv_viewall.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("vegetables")){
            firestore.collection("AllProducts").whereEqualTo("type","vegetables").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel model = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(model);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rcv_viewall.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("eggs")){
            firestore.collection("AllProducts").whereEqualTo("type","eggs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel model = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(model);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rcv_viewall.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("fishs")){

            firestore.collection("AllProducts").whereEqualTo("type","fishs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel model = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(model);
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        rcv_viewall.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

