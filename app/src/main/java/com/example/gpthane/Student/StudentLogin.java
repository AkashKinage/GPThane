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
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpthane.R;
import com.example.gpthane.Teacher.TeacherLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class StudentLogin extends AppCompatActivity {

    private EditText etStudentEmail, etStudentPassword;
    private TextView tvStudentRegister;
    private Button btnStudentLogin;
    private FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_student_login);

        etStudentEmail = findViewById(R.id.etStudentEmail);
        etStudentPassword = findViewById(R.id.etStudentPassword);

        tvStudentRegister = findViewById(R.id.tvStudentRegister);

        btnStudentLogin = findViewById(R.id.btnStudentLogin);

        auth = FirebaseAuth.getInstance();

        tvStudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentRegister.class));
            }
        });

        btnStudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(StudentLogin.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progressbar);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                String email = etStudentEmail.getText().toString();
                String password = etStudentPassword.getText().toString();

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
                else{
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                progressDialog.dismiss();

                                Toast.makeText(StudentLogin.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                            }
                            else{
                                Toast.makeText(StudentLogin.this, "Login Unsuccessful!", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }
}