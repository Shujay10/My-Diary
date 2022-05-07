package com.example.mydiary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TT_DaysRvAdapter extends RecyclerView.Adapter<TT_DaysRvAdapter.ExampleHolder>{

    ArrayList<String> listcls = new ArrayList<>();
    ArrayList<String> list;
    RecyclerView cll;
    Context ctx;
    String day;

    TT_ClassesRvAdapter adapter;

    TT_ClassesStruct temp = new TT_ClassesStruct();

    FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    ArrayList<TT_ClassesStruct> fetchedData = new ArrayList<>();

    public TT_DaysRvAdapter(ArrayList<String> list, Context ctx,RecyclerView cll) {
        this.list = list;
        this.cll = cll;
        this.ctx = ctx;
        getData();
    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tt_day,parent,false);

        return new TT_DaysRvAdapter.ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleHolder holder, int position) {

        holder.days.setText(list.get(position));

        setAdapterClass(holder);

        holder.days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = holder.days.getText().toString();
                System.out.println(day);
                storeClass(day);
                setAdapterClass(holder);
            }
        });

    }

    private void storeClass(String day){
        listcls.clear();

        //System.out.println(day+" == ");

        for(int i=0;i<5;i++){
            temp = fetchedData.get(i);
            //System.out.println(temp.getDay());
            if(temp.getDay().equals(day)){
                listcls = temp.getPeriods();
                adapter.notifyDataSetChanged();
                //break;
            }
        }
/*
        switch (day){
            case "Monday":
            {
                listcls.add("Maths");



                break;
            }
            case "Tuesday":
            {
                listcls.add("Tamil");
                break;
            }
            case "Wednesday":
            {
                listcls.add("Weather");
                break;
            }
            case "Thursday":
            {
                listcls.add("Telegu");
                break;
            }
            case "Friday":
            {
                listcls.add("French");
                break;
            }
            case "Saturday":
            {
                listcls.add("Sanskrit");
                break;
            }
            default:
            {
                listcls.clear();
                break;
            }
        }
*/
    }

    void getData(){

        String TAG = "IN getData";

        for(int i=0;i<6;i++){
            mStore.collection("Shemford").document("Timetable")
                    .collection("Class "+Student.getGrade()).document(days[i]).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            TT_ClassesStruct temp = documentSnapshot.toObject(TT_ClassesStruct.class);
                            fetchedData.add(temp);

                            //System.out.println(temp);

                        }
                    });

        }



    }

    private void setAdapterClass(@NonNull ExampleHolder holder){


        adapter = new TT_ClassesRvAdapter(listcls);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(ctx);
        cll.setLayoutManager(layoutManager);
        cll.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ExampleHolder extends RecyclerView.ViewHolder{

        TextView days;

        public ExampleHolder(@NonNull View itemView) {
            super(itemView);

            days = itemView.findViewById(R.id.day);

        }

    }
}
