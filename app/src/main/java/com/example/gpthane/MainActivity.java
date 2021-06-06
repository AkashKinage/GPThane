package com.example.gpthane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.gpthane.Student.StudentActivity;
import com.example.gpthane.Student.StudentRegister;
import com.example.gpthane.Student.model;
import com.example.gpthane.Student.myadapter;
import com.example.gpthane.Teacher.TeacherActivity;
import com.example.gpthane.Teacher.TeacherRegister;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private Button btnTeacher, btnStudent;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference studentReference, teacherReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btnTeacher = findViewById(R.id.btnTeacher);
        btnStudent = findViewById(R.id.btnStudent);

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TeacherRegister.class));
            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentRegister.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null) {
            String emailstr = user.getEmail();
            Log.d("email",emailstr);

            firebaseDatabase = FirebaseDatabase.getInstance();
            studentReference = firebaseDatabase.getReference("Students");
            teacherReference = firebaseDatabase.getReference("Teachers");

            teacherReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()){
                        String email = (String) ds.child("email").getValue();
                        if(email.equals(emailstr)){
                            Log.d("result","success");
                            startActivity(new Intent(getApplicationContext(), TeacherActivity.class));
                            finish();
                        }
                        else{
                            Log.d("result","unsuccess");
                            studentReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot ds : snapshot.getChildren()){
                                        String email = (String) ds.child("email").getValue();
                                        if (email.equals(emailstr)){
                                            Log.d("result","success");
                                            startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                                            finish();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }
    }
}