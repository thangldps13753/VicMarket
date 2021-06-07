package com.example.vicmarket.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vicmarket.R;
import com.example.vicmarket.adapter.NavTheLoaiAdapter;
import com.example.vicmarket.adapter.SanPhamPhoBienAdapter;
import com.example.vicmarket.models.NavTheLoaiModel;
import com.example.vicmarket.models.SanPhamPhoBienModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    ScrollView scrollingView;
    ProgressBar progressBar;
    RecyclerView rcvTheLoai;
    List<NavTheLoaiModel>list;
    NavTheLoaiAdapter adapter;
    FirebaseFirestore db;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);

        scrollingView = root.findViewById(R.id.scrollView_home);
        progressBar = root.findViewById(R.id.progressbar_home);
        progressBar.setVisibility(View.VISIBLE);
        scrollingView.setVisibility(View.GONE);
        rcvTheLoai = root.findViewById(R.id.rcv_theloai);
        db = FirebaseFirestore.getInstance();
        rcvTheLoai.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        list = new ArrayList<>();
        adapter = new NavTheLoaiAdapter(getActivity(),list);
        rcvTheLoai.setAdapter(adapter);

        db.collection("NavTheLoai")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavTheLoaiModel model = document.toObject(NavTheLoaiModel.class);
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
        return root;
    }
}