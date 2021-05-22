package com.example.gpthane.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gpthane.R;
import com.example.gpthane.Teacher.TeacherActivity;
import com.example.gpthane.Teacher.TeacherUploadNotice;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cvStudentNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        cvStudentNotice = findViewById(R.id.cvStudentNotice);

        cvStudentNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cvStudentNotice:
                Intent intent = new Intent(StudentActivity.this, StudentNotice.class);
                startActivity(intent);
                break;
        }
    }
}