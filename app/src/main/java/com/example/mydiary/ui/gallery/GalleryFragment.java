package com.example.mydiary.ui.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mydiary.adapters.GalImgRvAdapter;
import com.example.mydiary.struct.GalImgStruct;
import com.example.mydiary.R;
import com.example.mydiary.databinding.FragmentGalleryBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    RecyclerView rView;
    ImageView iView;
    ProgressBar gallProgress;
    ArrayList<GalImgStruct> list;

    GalImgRvAdapter adapter;
    ArrayList<StorageReference> aRef;
    StorageReference mRef;
    FirebaseStorage storage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rView = root.findViewById(R.id.gallery_recycler);
        gallProgress = root.findViewById(R.id.galPro);
        list = new ArrayList<>();
        aRef = new ArrayList<>();
        storage = FirebaseStorage.getInstance("gs://my-diary-fb6a1.appspot.com/");

        getData();
        setAdapter();

        // TODO : Storage issue , cache the image , Zooming , Filter

        return root;
    }

    private void getData() {

        final long ONE_MEGABYTE = 1024 * 1024;
        StorageReference listRef = storage.getReference().child("Gallery");

        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference prefix : listResult.getPrefixes()) {
                            // All the prefixes under listRef.
                            // You may call listAll() recursively on them.
                        }

                        for (StorageReference item : listResult.getItems()) {
                            // All the items under listRef.
                            aRef.add(item);
                            System.out.println(item);
                        }

                        pass();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Uh-oh, an error occurred!
                    }
                });


    }

    private void pass(){

        System.out.println(aRef.get(0));

        //Glide.with(getContext()).load(aRef.get(0)).into(iView);

/*
        for(int i=0;i<aRef.size();i++){
            try{

                File file = File.createTempFile("temp",".jpg");
                aRef.get(i).getFile(file)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                                //iView.setImageBitmap(bitmap);
                                list.add(new GalImgStruct(bitmap));
                                adapter.notifyDataSetChanged();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        final long ONE_MEGABYTE = 1024 * 1024;

        System.out.println(aRef.size());

        for(int i=0;i<aRef.size();i++){

            //StorageReference islandRef = storage.child(aRef.get(i));

            aRef.get(i).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    // Data for "images/island.jpg" is returns, use this as needed
                    int nh = (int) ( bmp.getHeight() * (512.0 / bmp.getWidth()) );
                    Bitmap scaled = Bitmap.createScaledBitmap(bmp, 512, nh, true);
                    list.add(new GalImgStruct(scaled));
                    adapter.notifyDataSetChanged();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }


            });

        }

    }

    void setAdapter(){

        adapter = new GalImgRvAdapter(list,gallProgress);
        StaggeredGridLayoutManager manager;
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rView.setLayoutManager(manager);
        rView.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}