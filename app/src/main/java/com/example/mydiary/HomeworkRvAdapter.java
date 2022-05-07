package com.example.mydiary;

import static com.example.mydiary.R.*;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeworkRvAdapter extends RecyclerView.Adapter<HomeworkRvAdapter.ExampleHolder>{

    ArrayList<HomeWorkStruct> list;

    public HomeworkRvAdapter(ArrayList<HomeWorkStruct> list) {
        this.list = list;
    }

    public class ExampleHolder extends RecyclerView.ViewHolder {

        TextView subject;
        CheckBox isDone;
        TextView work;

        public ExampleHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(id.subject);
            isDone = itemView.findViewById(id.subjectDone);
            work = itemView.findViewById(id.subjectContent);

        }
    }

    @NonNull
    @Override
    public HomeworkRvAdapter.ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.recycler_homework,parent,false);

        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkRvAdapter.ExampleHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.subject.setText(list.get(position).getSubject());
        holder.work.setText(list.get(position).getWork());

        holder.isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.isDone.isChecked()){
                    holder.subject.setPaintFlags(holder.subject.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.work.setPaintFlags(holder.work.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }else {
                    holder.subject.setPaintFlags(holder.subject.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.work.setPaintFlags(holder.work.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        //return 1;
        return list.size();
    }
}
