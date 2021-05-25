package com.example.gpthane.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gpthane.R;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cdUploadMotice, cvUploadPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        cdUploadMotice = findViewById(R.id.cdUploadNotice);
        cvUploadPdf = findViewById(R.id.cvUploadPdf);

        cdUploadMotice.setOnClickListener(this);
        cvUploadPdf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cdUploadNotice:
                Intent intent = new Intent(TeacherActivity.this, TeacherUploadNotice.class);
                startActivity(intent);
                break;

            case R.id.cvUploadPdf:
                intent = new Intent(TeacherActivity.this, UploadPDFActivity1.class);
                startActivity(intent);
                break;
        }
    }
}