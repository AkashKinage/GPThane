package com.example.gpthane.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.gpthane.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class StudentNotice extends AppCompatActivity {

    RecyclerView recyclerView;
    myadapter adapter;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_student__notice);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        FirebaseRecyclerOptions<model> options =
//                new FirebaseRecyclerOptions.Builder<model>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notice"), model.class)
//                        .build();

//        adapter = new myadapter(options);
//        recyclerView.setAdapter(adapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        String emailstr = user.getEmail();
        Log.d("email",emailstr);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    Log.d("ds",ds.child("name").toString());
                    Log.d("dep",ds.child("department").toString());
                    Log.d("email",ds.child("email").toString());
                    String email = (String) ds.child("email").getValue();
                    if(email.equals(emailstr)){
                        Log.d("result","success");
                        Log.d("dep1",ds.child("department").getValue().toString());
                        department = ds.child("department").getValue().toString();
                        Log.d("department",department);

                        FirebaseRecyclerOptions<model> options =
                                new FirebaseRecyclerOptions.Builder<model>()
                                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notice").child(department), model.class)
                                        .build();

                        adapter = new myadapter(options);
                        adapter.startListening();
                        recyclerView.setAdapter(adapter);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}