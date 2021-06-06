package com.example.gpthane.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gpthane.R;
import com.example.gpthane.Student.model;
import com.example.gpthane.Student.myadapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class DisplayStudents extends AppCompatActivity {

    Spinner spinnerYear;
    RecyclerView rvStudentDisplay;
    adapterStudentDisplay adapter;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_display_students);

//        spinnerYear = findViewById(R.id.spinnerYear);
//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SelectYear , android.R.layout.simple_spinner_item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerYear.setAdapter(adapter1);

        rvStudentDisplay = findViewById(R.id.rvStudentDisplay);
        rvStudentDisplay.setLayoutManager(new LinearLayoutManager(this));



        user = FirebaseAuth.getInstance().getCurrentUser();
        String emailstr = user.getEmail();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    Log.d("ds", ds.toString());
                    String year = ds.child("year").toString();
                    Log.d("dsyear", ds.child("year").toString());
                    FirebaseRecyclerOptions<modelStudentDisplay> options =
                            new FirebaseRecyclerOptions.Builder<modelStudentDisplay>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference("Students"), modelStudentDisplay.class)
                                    .build();

                    adapter = new adapterStudentDisplay(options);
                    adapter.startListening();
                    rvStudentDisplay.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

//        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String choice = parent.getItemAtPosition(position).toString();
//                switch (position){
//                    case 0:
//                        databaseReference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                            for (DataSnapshot ds: snapshot.getChildren()){
//                                Log.d("ds", ds.toString());
//                                String year = ds.child("year").toString();
//                                Log.d("dsyear", ds.child("year").toString());
//                                FirebaseRecyclerOptions<modelStudentDisplay> options =
//                                        new FirebaseRecyclerOptions.Builder<modelStudentDisplay>()
//                                                .setQuery(FirebaseDatabase.getInstance().getReference("Students").orderByChild("year").equalTo("1st Year"), modelStudentDisplay.class)
//                                                .build();
//
//                                adapter = new adapterStudentDisplay(options);
//                                adapter.startListening();
//                                rvStudentDisplay.setAdapter(adapter);
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                        }
//                    });
//
//                    case 1:
//                        databaseReference.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                                for (DataSnapshot ds: snapshot.getChildren()){
//                                    Log.d("ds", ds.toString());
//                                    String year = ds.child("year").toString();
//                                    Log.d("dsyear", ds.child("year").toString());
//                                    FirebaseRecyclerOptions<modelStudentDisplay> options =
//                                            new FirebaseRecyclerOptions.Builder<modelStudentDisplay>()
//                                                    .setQuery(FirebaseDatabase.getInstance().getReference("Students").orderByChild("year").equalTo("2nd Year"), modelStudentDisplay.class)
//                                                    .build();
//
//                                    adapter = new adapterStudentDisplay(options);
//                                    adapter.startListening();
//                                    rvStudentDisplay.setAdapter(adapter);
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                            }
//                        });
//
//                    case 2:
//                        databaseReference.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                                for (DataSnapshot ds: snapshot.getChildren()){
//                                    Log.d("ds", ds.toString());
//                                    String year = ds.child("year").toString();
//                                    Log.d("dsyear", ds.child("year").toString());
//                                    FirebaseRecyclerOptions<modelStudentDisplay> options =
//                                            new FirebaseRecyclerOptions.Builder<modelStudentDisplay>()
//                                                    .setQuery(FirebaseDatabase.getInstance().getReference("Students").orderByChild("year").equalTo("3rd Year"), modelStudentDisplay.class)
//                                                    .build();
//
//                                    adapter = new adapterStudentDisplay(options);
//                                    adapter.startListening();
//                                    rvStudentDisplay.setAdapter(adapter);
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                            }
//                        });
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        FirebaseRecyclerOptions<modelStudentDisplay> options =
//                        new FirebaseRecyclerOptions.Builder<modelStudentDisplay>()
//                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Students").child("1st Year"), modelStudentDisplay.class)
//                                .build();
//
//                adapter = new adapterStudentDisplay(options);
//                rvStudentDisplay.setAdapter(adapter);
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////        String choice = parent.getItemAtPosition(position).toString();
//
//        switch (position){
//            case 0:
//                FirebaseRecyclerOptions<modelStudentDisplay> options =
//                        new FirebaseRecyclerOptions.Builder<modelStudentDisplay>()
//                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Students").child("1st Year"), modelStudentDisplay.class)
//                                .build();
//
//                adapter = new adapterStudentDisplay(options);
//                rvStudentDisplay.setAdapter(adapter);
//
//            case 1:
//                options =
//                        new FirebaseRecyclerOptions.Builder<modelStudentDisplay>()
//                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Students").child("2nd Year"), modelStudentDisplay.class)
//                                .build();
//
//                adapter = new adapterStudentDisplay(options);
//                rvStudentDisplay.setAdapter(adapter);
//
//            case 2:
//                options =
//                        new FirebaseRecyclerOptions.Builder<modelStudentDisplay>()
//                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Students").child("3rd Year"), modelStudentDisplay.class)
//                                .build();
//
//                adapter = new adapterStudentDisplay(options);
//                rvStudentDisplay.setAdapter(adapter);
//
//
//        }
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

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