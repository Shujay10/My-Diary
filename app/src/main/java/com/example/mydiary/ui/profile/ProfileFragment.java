package com.example.mydiary.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydiary.R;
import com.example.mydiary.Student;
import com.example.mydiary.databinding.FragmentHomeBinding;
import com.example.mydiary.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    TextView name,grade,rollnum,email;
    TextView parents,phone,school;

    ProgressBar progressBar;

    FirebaseFirestore mStore;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mStore = FirebaseFirestore.getInstance();

        name = root.findViewById(R.id.proName);
        grade = root.findViewById(R.id.proClass);
        rollnum = root.findViewById(R.id.proRoll);
        email = root.findViewById(R.id.proEmail);
        parents = root.findViewById(R.id.proParent);
        phone = root.findViewById(R.id.proPhone);
        school = root.findViewById(R.id.proSchool);
        progressBar = root.findViewById(R.id.proPro_bar);


        try {
            getVal();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return root;
    }

    private void getVal() throws FileNotFoundException {

        try {
            InputStream in = getActivity().openFileInput("Store.txt");
            if (in != null) {
                InputStreamReader tmp=new InputStreamReader(in);
                BufferedReader reader=new BufferedReader(tmp);
                String str;
                StringBuilder buf=new StringBuilder();

                while ((str = reader.readLine()) != null) {
                    buf.append(str);
                }
                in.close();
                String get = buf.toString();
                String[] splitting = get.split(":");
                Student.setRegnol(splitting[0]);
                Student.setSchool(splitting[1]);
                //System.out.println(buf);
            }
        }
        catch (java.io.FileNotFoundException e) {
        }
        catch (Throwable t) {


        }

        String sch = Student.getSchool();

        DocumentReference docRef = mStore.collection(sch).document("Student")
                .collection("StudentList").document(Student.getRegnol());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists()){

                    Student.setName((String) documentSnapshot.get("name"));
                    Student.setEmail((String) documentSnapshot.get("email"));
                    Student.setPhoNoP((String) documentSnapshot.get("phonePrimary"));
                    Student.setParentsName((String) documentSnapshot.get("parentName"));
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

        rollnum.setText(Student.getRegnol());
        email.setText(Student.getEmail());
        parents.setText(Student.getParentsName());
        phone.setText(Student.getPhoNoP());
        school.setText(Student.getSchool());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}