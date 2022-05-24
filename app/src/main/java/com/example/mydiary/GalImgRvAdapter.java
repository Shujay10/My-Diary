package com.example.mydiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalImgRvAdapter extends RecyclerView.Adapter<GalImgRvAdapter.ExampleHolder>{

    ArrayList<GalImgStruct> list;

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

        holder.iView.setImageResource(list.get(position).getImage());

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
