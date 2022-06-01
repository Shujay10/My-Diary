package com.example.mydiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydiary.struct.GalImgStruct;
import com.example.mydiary.R;

import java.util.ArrayList;

public class GalImgRvAdapter extends RecyclerView.Adapter<GalImgRvAdapter.ExampleHolder>{

    ArrayList<GalImgStruct> list;
    ProgressBar pBar;

    public GalImgRvAdapter(ArrayList<GalImgStruct> list, ProgressBar pBar) {
        this.list = list;
        this.pBar = pBar;
    }

    public GalImgRvAdapter(ArrayList<GalImgStruct> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_recycler,parent,false);

        return new GalImgRvAdapter.ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleHolder holder, int position) {

        holder.iView.setImageBitmap(list.get(position).getImage());
        pBar.setVisibility(ProgressBar.GONE);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ExampleHolder extends RecyclerView.ViewHolder {

        ImageView iView;

        public ExampleHolder(@NonNull View itemView) {
            super(itemView);

            iView = itemView.findViewById(R.id.gallery_image);

        }
    }
}
