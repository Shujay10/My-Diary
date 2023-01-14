package com.example.mydiary.ui.profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydiary.R;
import com.example.mydiary.Student;
import com.example.mydiary.databinding.FragmentProfileBinding;
import com.example.mydiary.struct.StoreStruct;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    SwipeRefreshLayout refreshLayout;

    TextView name,grade,rollnum,email;
    TextView parents,phone,birthday,school;

    ProgressBar progressBar;

    FirebaseFirestore mStore;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mStore = FirebaseFirestore.getInstance();
        refreshLayout = root.findViewById(R.id.profileReload);

        name = root.findViewById(R.id.proName);
        grade = root.findViewById(R.id.proClass);
        rollnum = root.findViewById(R.id.proRoll);
        email = root.findViewById(R.id.proEmail);
        parents = root.findViewById(R.id.proParent);
        phone = root.findViewById(R.id.proPhone);
        birthday = root.findViewById(R.id.proBirthday);
        school = root.findViewById(R.id.proSchool);
        progressBar = root.findViewById(R.id.proPro_bar);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                getValOnline();

            }
        });

        getVal();

        return root;
    }

    private void getVal(){
        SharedPreferences prefs =  getContext().getSharedPreferences("UserData", MODE_PRIVATE);
        Gson gson = new Gson();
        String usrDat = prefs.getString("UserData","");
        StoreStruct tr1 = gson.fromJson(usrDat, StoreStruct.class);

        Student.setName(tr1.getName());
        Student.setEmail(tr1.getEmail());
        Student.setRegno(tr1.getRegNo());
        Student.setPhoNoP(tr1.getPhoNoP());
        Student.setPhoNoS(tr1.getPhoNoS());
        Student.setParentsName(tr1.getParentsName());
        Student.setBirthday(tr1.getBirthday());
        Student.setGrade(tr1.getGrade());
        Student.setSchool(tr1.getSchool());
        setVal();

    }

    private void getValOnline() {

        SharedPreferences prefs =  getContext().getSharedPreferences("UserData", MODE_PRIVATE);
        Gson gson = new Gson();
        String usrDat = prefs.getString("RegSchool","");
        StoreStruct tr1 = gson.fromJson(usrDat, StoreStruct.class);
        Student.setRegno(tr1.getRegNo());
        Student.setSchool(tr1.getSchool());

        String sch = Student.getSchool();

        DocumentReference docRef = mStore.collection(sch).document("Human resources")
                .collection("StudentList").document(Student.getRegno());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists()){

                    Student.setName((String) documentSnapshot.get("name"));
                    Student.setEmail((String) documentSnapshot.get("email"));
                    Student.setPhoNoP((String) documentSnapshot.get("phPri"));
                    Student.setParentsName((String) documentSnapshot.get("parentName"));
                    Student.setBirthday((String) documentSnapshot.get("dateOfBirth"));
                    Student.setGrade((String) documentSnapshot.get("grade"));
                    Student.setSchool(sch);
                    setVal();

                }else {
                    boolean isGood = false;
                    Toast.makeText(getContext(),"Data Not Found Firestore",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void setVal() {

        progressBar.setVisibility(View.GONE);
        name.setText(Student.getName());

        grade.setText("Grade "+Student.getGrade()+"th");

        rollnum.setText(Student.getRegno());
        email.setText(Student.getEmail());
        parents.setText(Student.getParentsName());
        phone.setText(Student.getPhoNoP());
        birthday.setText(Student.getBirthday());
        school.setText(Student.getSchool());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}