package com.example.vicmarket.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vicmarket.R;
import com.example.vicmarket.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView imgCTSP,imgAdd,imgDelete;
    TextView tvPrice,tvSoLuong;

    int totalSoLuong = 1;
    int totalPrice = 0;

    Button btnAddtoCart;
    Toolbar toolbar;
    ViewAllModel viewAllModel = null;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        imgCTSP = findViewById(R.id.img_ctsp);
        imgAdd = findViewById(R.id.img_add_item);
        imgDelete = findViewById(R.id.img_minus_item);
        toolbar = findViewById(R.id.toolbar_ctsp);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("details");
        if(object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }

        tvPrice = findViewById(R.id.price_spct);
        tvSoLuong = findViewById(R.id.tv_soLuong);
        btnAddtoCart = findViewById(R.id.btn_addtocart);

        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(imgCTSP);
            tvPrice.setText(String.valueOf(viewAllModel.getPrice())+"/kg");

            totalPrice = viewAllModel.getPrice()*totalSoLuong;

            if(viewAllModel.getType().equals("egg")){
                tvPrice.setText("Giá: "+viewAllModel.getPrice()+"/trứng");
                totalPrice = viewAllModel.getPrice()*totalSoLuong;
            }
            if(viewAllModel.getType().equals("drinks")){
                tvPrice.setText("Giá: "+viewAllModel.getPrice()+"/lon");
                totalPrice = viewAllModel.getPrice()*totalSoLuong;
            }
        }


/// THEM SO LUONG

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalSoLuong < 10){
                    totalSoLuong++;
                    tvSoLuong.setText(String.valueOf(totalSoLuong));
                    totalPrice = viewAllModel.getPrice()*totalSoLuong;
                }
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalSoLuong > 1){
                    totalSoLuong--;
                    tvSoLuong.setText(String.valueOf(totalSoLuong));
                    totalPrice = viewAllModel.getPrice()*totalSoLuong;
                }
            }
        });
        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void addToCart(){
        String saveCurrentDate,saveCurrentTime;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("title",viewAllModel.getTitle());
        cartMap.put("date",saveCurrentDate);
        cartMap.put("price", tvPrice.getText().toString());
        cartMap.put("time",saveCurrentTime);
        cartMap.put("totalSoLuong",tvSoLuong.getText().toString());
        cartMap.put("totalTongTien",totalPrice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ChiTietSanPhamActivity.this, "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}