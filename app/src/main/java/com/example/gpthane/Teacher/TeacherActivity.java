package com.example.gpthane.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.gpthane.MainActivity;
import com.example.gpthane.R;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cdUploadMotice, cvUploadPdf, cvStudentsDisplay, logout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_teacher);

        cdUploadMotice = findViewById(R.id.cdUploadNotice);
        cvUploadPdf = findViewById(R.id.cvUploadPdf);
        cvStudentsDisplay = findViewById(R.id.cvStudentsDisplay);
        logout = findViewById(R.id.logout);

        auth = FirebaseAuth.getInstance();

        cdUploadMotice.setOnClickListener(this);
        cvUploadPdf.setOnClickListener(this);
        cvStudentsDisplay.setOnClickListener(this);
        logout.setOnClickListener(this);
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

            case R.id.cvStudentsDisplay:
                intent = new Intent(TeacherActivity.this, DisplayStudents.class);
                startActivity(intent);
                break;

            case R.id.logout:
                auth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
        }
    }
}