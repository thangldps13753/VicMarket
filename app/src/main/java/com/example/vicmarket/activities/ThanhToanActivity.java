package com.example.vicmarket.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.vicmarket.R;
import com.example.vicmarket.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");
        if(list != null && list.size() >0){
            for(MyCartModel model : list){
                final HashMap<String,Object> cartMap = new HashMap<>();
                cartMap.put("title",model.getTitle());
                cartMap.put("date",model.getDate());
                cartMap.put("price", model.getPrice());
                cartMap.put("time",model.getTime());
                cartMap.put("totalSoLuong",model.getTotalSoLuong());
                cartMap.put("totalTongTien",model.getTotalTongTien());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(ThanhToanActivity.this, "CẢM ƠN BẠN ĐÃ MUA HÀNG!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}