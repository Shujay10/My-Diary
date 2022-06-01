package com.example.mydiary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.mydiary.MainActivity;
import com.example.mydiary.R;
import com.example.mydiary.struct.ClassStruct;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

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
    String[] let = {"m","t","w","th","f","s"};

    final String url = "https://my-diary-fb6a1-default-rtdb.asia-southeast1.firebasedatabase.app/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        toLogin = new Intent(SplashActivity.this, LogInActivity.class);
        toMain = new Intent(SplashActivity.this, MainActivity.class);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance(url);
        mStore = FirebaseFirestore.getInstance();

        // TODO : Check for cache txt file

        //storeUser();
        //storeData();
        //storeHomework();
        //storeEvent();
        //storeClasses();
        splashHandler();

    }

    void storeClasses(){

        int cls = 12;
        String txt;


        //for(int x = 1;x<9;x++){
           // cls = x;
            for(int i=0;i<6;i++){

                txt = let[i];

                ArrayList<String> list = new ArrayList<>();
                list.add("English_"+txt+cls);
                list.add("Science_"+txt+cls);
                list.add("Tamil_"+txt+cls);
                list.add("PT_"+txt+cls);
                list.add("Social_"+txt+cls);
                list.add("Maths_"+txt+cls);
                list.add("Hindi_"+txt+cls);
                list.add("Reading Club_"+txt+cls);

                ClassStruct stu = new ClassStruct(days[i],list);

                mStore.collection("Shemford")
                        .document("Timetable").collection("Class "+cls)
                        .document(days[i]).set(stu);

                if(i==5){
                    Toast.makeText(getApplicationContext(),"Stored "+cls,Toast.LENGTH_SHORT).show();
                }

            }
        //}




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