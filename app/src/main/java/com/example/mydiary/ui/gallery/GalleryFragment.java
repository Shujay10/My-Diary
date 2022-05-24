package com.example.mydiary.ui.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mydiary.GalImgRvAdapter;
import com.example.mydiary.GalImgStruct;
import com.example.mydiary.R;
import com.example.mydiary.databinding.FragmentGalleryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    RecyclerView rView;
    ArrayList<GalImgStruct> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rView = root.findViewById(R.id.gallery_recycler);
        list = new ArrayList<>();

        getData();
        setAdapter();

        return root;
    }

    private void getData() {

        list.add(new GalImgStruct(R.drawable.pon1));

    }

    void setAdapter(){

        StaggeredGridLayoutManager manager;
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rView.setLayoutManager(manager);
        rView.setAdapter(new GalImgRvAdapter(list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}