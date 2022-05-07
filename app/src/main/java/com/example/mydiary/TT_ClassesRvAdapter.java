package com.example.mydiary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydiary.ui.timetable.TimeTable;

import java.util.ArrayList;

public class TT_ClassesRvAdapter extends RecyclerView.Adapter<TT_ClassesRvAdapter.ExampleHolder>{

    ArrayList<String> list;
    Context ctx;

    public TT_ClassesRvAdapter(ArrayList<String> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public TT_ClassesRvAdapter.ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tt_classes,parent,false);

        return new TT_ClassesRvAdapter.ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TT_ClassesRvAdapter.ExampleHolder holder, int position) {
        holder.classes.setText(list.get(position));

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ExampleHolder extends RecyclerView.ViewHolder {

        TextView classes;
        RecyclerView cll;

        public ExampleHolder(@NonNull View itemView) {
            super(itemView);

            classes = itemView.findViewById(R.id.classes);
            cll = itemView.findViewById(R.id.recycler_class);

        }
    }
}
