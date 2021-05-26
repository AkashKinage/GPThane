package com.example.gpthane.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gpthane.R;
import com.example.gpthane.Student.model;
import com.example.gpthane.Student.myadapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayStudents extends AppCompatActivity {

    Spinner spinnerYear;
    RecyclerView rvStudentDisplay;
    adapterStudentDisplay adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_students);

        spinnerYear = findViewById(R.id.spinnerYear);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SelectYear , android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapter1);

        rvStudentDisplay = findViewById(R.id.rvStudentDisplay);
        rvStudentDisplay.setLayoutManager(new LinearLayoutManager(this));

//        spinnerYear.setOnItemSelectedListener(this);

        FirebaseRecyclerOptions<modelStudentDisplay> options =
                        new FirebaseRecyclerOptions.Builder<modelStudentDisplay>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Students").child("1st Year"), modelStudentDisplay.class)
                                .build();

                adapter = new adapterStudentDisplay(options);
                rvStudentDisplay.setAdapter(adapter);
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

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}