package com.example.vicmarket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vicmarket.activities.ThanhToanActivity;
import com.example.vicmarket.adapter.MyCartAdapter;
import com.example.vicmarket.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    List<MyCartModel>list;
    MyCartAdapter adapter;
    FirebaseFirestore db;
    RecyclerView rcv_mycart;
    FirebaseAuth auth;
    TextView mycart;
    Button btn_mycart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_my_cart, container, false);
        rcv_mycart = root.findViewById(R.id.rcv_mycart);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        btn_mycart = root.findViewById(R.id.btn_mycart);
        rcv_mycart.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new MyCartAdapter(getActivity(),list);
        rcv_mycart.setAdapter(adapter);
        mycart = root.findViewById(R.id.textView2);
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mReceiver,new IntentFilter("MyOrder"));

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        MyCartModel model = documentSnapshot.toObject(MyCartModel.class);
                        list.add(model);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        btn_mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ThanhToanActivity.class);
                i.putExtra("itemList",(Serializable)list);
                startActivity(i);
            }
        });


        return root;
    }
    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent){
            int  totalBill = intent.getIntExtra("totalAmount",0);
            mycart.setText("Tổng tiền: "+totalBill+" /VND");
        }
    };
}