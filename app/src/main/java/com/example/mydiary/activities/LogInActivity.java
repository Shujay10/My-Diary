package com.example.mydiary.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mydiary.MainActivity;
import com.example.mydiary.R;
import com.example.mydiary.struct.StoreStruct;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.io.OutputStreamWriter;

public class LogInActivity extends AppCompatActivity {

    EditText sclName,reNo,email,pass;
    ProgressBar proBar;
    boolean isGood = true;

    Button login;

    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    // TODO : Forgot password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        sclName = findViewById(R.id.SchoolName);
        reNo = findViewById(R.id.regNo);
        email = findViewById(R.id.emailId);
        pass = findViewById(R.id.passWord);
        login = findViewById(R.id.logIn);
        proBar = findViewById(R.id.progressBar);
        proBar.setVisibility(View.GONE);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                proBar.setVisibility(View.VISIBLE);
                validate();

            }
        });


    }

    private boolean validate(){

        boolean isOk = true;


        if(getSchool().isEmpty()){
            sclName.setError("Required");
            isOk = false;
        }

        if(getRegNo().isEmpty()){
            reNo.setError("Required");
            isOk = false;
        }

        if(getEmail().isEmpty()){
            email.setError("Required");
            isOk = false;
        }

        if(getPass().isEmpty()){
            pass.setError("Required");
            isOk = false;
        }

        if(isOk){
             setValues();
        }

        if(!isGood){
            isOk = false;
        }

        // TODO create a class with static var for school,regno
        // and get details of student from database and store it in the class

        return isOk;

    }

    private void setValues() {

        DocumentReference docRef = mStore.collection(getSchool())
                .document("Human resources").collection("StudentList").document(getRegNo());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists()){

                    String name = (String) documentSnapshot.get("name");
                    String email = (String) documentSnapshot.get("email");
                    String grade = (String) documentSnapshot.get("grade");
                    String phoNoP = (String) documentSnapshot.get("phPri");
                    String phoNoS = (String) documentSnapshot.get("phSec");
                    String birthday = (String) documentSnapshot.get("dateOfBirth");
                    String ParentsName = (String) documentSnapshot.get("parentName");
                    String regNo = (String) documentSnapshot.get("rollNo");
                    String school = (String) documentSnapshot.get("school");

                    StoreStruct store = new StoreStruct(name,grade,email,phoNoP,phoNoS,birthday,ParentsName,regNo,school);

                    Gson gson = new Gson();

                    String storeUser = gson.toJson(store);
                    SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("UserData",storeUser);
                    editor.apply();

                    putValue(email);

                }else {
                    //boolean isGood = false;
                    proBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"User Not Found",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void putValue(String email1) {

        System.out.println(email1 +" == "+getEmail());

        if(!email1.equals(getEmail())){
            isGood = false;
            email.setError("Not a match");
        }

        if(isGood){

            StoreStruct userData = new StoreStruct(getRegNo(),getSchool());
            Gson gson = new Gson();

            String storeUser = gson.toJson(userData);
            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("RegSchool",storeUser);
            editor.apply();

            auth();
        }else {
            Toast.makeText(getApplicationContext(),"Something wrong",Toast.LENGTH_SHORT).show();
            isGood = true;
        }

    }

    private void auth() {

        String email = getEmail();
        String password = getPass();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            proBar.setVisibility(View.GONE);

                            Toast.makeText(LogInActivity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            proBar.setVisibility(View.GONE);
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }

    private String getSchool(){

        return sclName.getText().toString();
    }

    private String getRegNo(){

        return reNo.getText().toString();
    }

    private String getEmail(){

        return email.getText().toString();
    }

    private String getPass(){

        return pass.getText().toString();
    }


}