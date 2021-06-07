package com.example.vicmarket.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ScrollingView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vicmarket.R;
import com.example.vicmarket.adapter.SanPhamPhoBienAdapter;
import com.example.vicmarket.adapter.TrangChuTheLoaiAdapter;
import com.example.vicmarket.models.SanPhamPhoBienModel;
import com.example.vicmarket.models.TrangChuTheLoaiModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollingView;
    ProgressBar progressBar;
    FirebaseFirestore db;
    //TRANG CHỦ SẢN PHẨM ITEM;
    List<TrangChuTheLoaiModel> list1;
    TrangChuTheLoaiAdapter adapter1;


    //SẢN PHẨM PHỔ BIẾN ITEM;
    List<SanPhamPhoBienModel> list;
    SanPhamPhoBienAdapter adapter;
    RecyclerView rcvSPPB,rcvTTSP;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();

        scrollingView = root.findViewById(R.id.scrollView_home);
        progressBar = root.findViewById(R.id.progressbar_home);
        progressBar.setVisibility(View.VISIBLE);
        scrollingView.setVisibility(View.GONE);

        rcvSPPB = root.findViewById(R.id.rcv_sppb);
        rcvTTSP = root.findViewById(R.id.rcv_tcsp);

        rcvSPPB.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        list = new ArrayList<>();
        adapter = new SanPhamPhoBienAdapter(getActivity(),list);
        rcvSPPB.setAdapter(adapter);

        db.collection("SanPhamPhoBien")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SanPhamPhoBienModel model = document.toObject(SanPhamPhoBienModel.class);
                                list.add(model);
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollingView.setVisibility(View.VISIBLE);
                           }
                        } else {
                            Toast.makeText(getActivity(), "Erorr"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        rcvTTSP.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        list1 = new ArrayList<>();
        adapter1 = new TrangChuTheLoaiAdapter(getActivity(),list1);
        rcvTTSP.setAdapter(adapter1);
        db.collection("TrangChuTheLoai")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TrangChuTheLoaiModel model1 = document.toObject(TrangChuTheLoaiModel.class);
                                list1.add(model1);
                                adapter1.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollingView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Erorr"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return root;
    }
}