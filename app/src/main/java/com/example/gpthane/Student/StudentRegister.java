package com.example.gpthane.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpthane.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class StudentRegister extends AppCompatActivity {

    private EditText etStudentName, etStudentEnrollmentNo, etStudentPhoneNo, etStudentEmail, etStudentPassword;
    private Button btnStudentRegister;
    private TextView tvStudentLogin;
    private RadioButton rb1stYear, rb2ndYear, rb3rdYear;
    private RadioGroup radioGroup;
    private FirebaseAuth auth;
    private String name, enrollmentno, phone, email, password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_student_register);

        etStudentName = findViewById(R.id.etStudentName);
        etStudentEnrollmentNo = findViewById(R.id.etStudentEnrollmentNo);
        etStudentPhoneNo = findViewById(R.id.etStudentPhoneNo);
        etStudentEmail = findViewById(R.id.etStudentEmail);
        etStudentPassword = findViewById(R.id.etStudentPassword);

        btnStudentRegister = findViewById(R.id.btnStudentRegister);

        tvStudentLogin = findViewById(R.id.tvStudentLogin);

        radioGroup = findViewById(R.id.radioGroup);

        rb1stYear = findViewById(R.id.rb1stYear);
        rb2ndYear = findViewById(R.id.rb2ndYear);
        rb3rdYear = findViewById(R.id.rb3rdYear);

        auth = FirebaseAuth.getInstance();

        tvStudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentLogin.class));
            }
        });

        btnStudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(StudentRegister.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progressbar_processing);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                name = etStudentName.getText().toString();
                enrollmentno = etStudentEnrollmentNo.getText().toString();
                phone = etStudentPhoneNo.getText().toString();
                email = etStudentEmail.getText().toString();
                password = etStudentPassword.getText().toString();

                if(name.isEmpty()){
                    etStudentName.setError("Please Enter Name");
                    etStudentName.requestFocus();
                    return;
                }

                if(enrollmentno.isEmpty()){
                    etStudentEnrollmentNo.setError("Please Enter your Enrollment Number");
                    etStudentEnrollmentNo.requestFocus();
                    return;
                }

                if(enrollmentno.length()<10){
                    etStudentEnrollmentNo.setError("Please Enter valid Enrollment Number");
                    etStudentEnrollmentNo.requestFocus();
                    return;
                }

                if(phone.isEmpty()){
                    etStudentPhoneNo.setError("Please Enter Phone Number");
                    etStudentPhoneNo.requestFocus();
                    return;
                }

                if(phone.length() < 10){
                    etStudentPhoneNo.setError("Please enter a valid Phone Number");
                    etStudentPhoneNo.requestFocus();
                    return;
                }

                if(email.isEmpty()){
                    etStudentEmail.setError("Email is Empty");
                    etStudentEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etStudentEmail.setError("Email is Invalid");
                    etStudentEmail.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    etStudentPassword.setError("Password is Empty");
                    etStudentPassword.requestFocus();
                    return;
                }
                if(password.length() < 6){
                    etStudentPassword.setError("Password must be 6 or more characters");
                    etStudentPassword.requestFocus();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    progressDialog.dismiss();
                                    StudentInfo studentInfo = new StudentInfo(
                                        name,
                                        enrollmentno,
                                        phone,
                                        email
                                    );

                                    if(rb1stYear.isChecked()){
                                        FirebaseDatabase.getInstance().getReference("Students")
                                                .child("1st Year")
                                                .child(name)
                                                .setValue(studentInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(StudentRegister.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                                                }
                                                else{
                                                    Toast.makeText(StudentRegister.this, "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }

                                    if(rb2ndYear.isChecked()){
                                        FirebaseDatabase.getInstance().getReference("Students")
                                                .child("2nd Year")
                                                .child(name)
                                                .setValue(studentInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(StudentRegister.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
//                                                    startActivity(new Intent(getApplicationContext(), TeacherActivity.class));
                                                }
                                                else{
                                                    Toast.makeText(StudentRegister.this, "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }

                                    if(rb3rdYear.isChecked()){
                                        FirebaseDatabase.getInstance().getReference("Students")
                                                .child("3rd Year")
                                                .child(name)
                                                .setValue(studentInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(StudentRegister.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
//                                                    startActivity(new Intent(getApplicationContext(), TeacherActivity.class));
                                                }
                                                else{
                                                    Toast.makeText(StudentRegister.this, "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                                else {
                                    Toast.makeText(StudentRegister.this, "Error!", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
            }
        });
    }
}