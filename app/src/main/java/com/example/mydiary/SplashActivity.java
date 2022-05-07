package com.example.mydiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    private static int splashTimer = 1500;
    //private static int splashTimer = 10000;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore mStore;

    FirebaseDatabase database;

    Intent toLogin;
    Intent toMain;

    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    final String url = "https://my-diary-fb6a1-default-rtdb.asia-southeast1.firebasedatabase.app/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        toLogin = new Intent(SplashActivity.this,LogInActivity.class);
        toMain = new Intent(SplashActivity.this,MainActivity.class);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance(url);
        mStore = FirebaseFirestore.getInstance();

        //storeUser();
        //storeData();
        //storeHomework();
        //storeEvent();
        //storeClasses();
        splashHandler();

    }

    void storeClasses(){

        int cls = 10;
        String txt = "s";
        int i = 5;
        ArrayList<String> list = new ArrayList<>();
        list.add("English_"+txt+cls);
        list.add("Science_"+txt+cls);
        list.add("Tamil_"+txt+cls);
        list.add("PT_"+txt+cls);
        list.add("Social_"+txt+cls);
        list.add("Maths_"+txt+cls);
        list.add("Hindi_"+txt+cls);
        list.add("Reading Club_"+txt+cls);

        TT_ClassesStruct stu = new TT_ClassesStruct(days[i],list);

        mStore.collection("Shemford")
                .document("Timetable").collection("Class "+cls)
                .document(days[i]).set(stu);

    }

    private void splashHandler(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(mUser == null)
                    startActivity(toLogin);
                else
                    startActivity(toMain);

            }
        },splashTimer);

    }

}