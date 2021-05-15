package com.example.gpthane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class TeacherRegister extends AppCompatActivity {

    EditText etTeacherName, etTeacherUniqueId, etPassword, etTeacherEmail, etTeacherPhoneNo;
    Button btnTeacherRegister;
    TextView tvTeacherLogin;
    String UniqueId = "GPTTeacher123";
    private FirebaseAuth auth;
    private String name, email, phone, enteredUniqueId, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        etTeacherName = findViewById(R.id.etTeacherName);
        etTeacherUniqueId = findViewById(R.id.etTeacherUniqueId);
        etPassword = findViewById(R.id.etPassword);
        etTeacherEmail = findViewById(R.id.etTeacherEmail);
        etTeacherPhoneNo = findViewById(R.id.etTeacherPhoneNo);

        auth = FirebaseAuth.getInstance();

        btnTeacherRegister = findViewById(R.id.btnTeacherRegister);

        tvTeacherLogin = findViewById(R.id.tvTeacherLogin);

        tvTeacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeacherRegister.this, "Login", Toast.LENGTH_SHORT).show();
            }
        });

        btnTeacherRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etTeacherName.getText().toString();
                email = etTeacherEmail.getText().toString();
                phone = etTeacherPhoneNo.getText().toString();
                password = etPassword.getText().toString();
                enteredUniqueId = etTeacherUniqueId.getText().toString();

                if(enteredUniqueId.equals("gpt456")) {

                    if(name.isEmpty()){
                        etTeacherName.setError("Name is Empty");
                        etTeacherName.requestFocus();
                        return;
                    }
                    if(email.isEmpty()){
                        etTeacherEmail.setError("Email is Empty");
                        etTeacherEmail.requestFocus();
                        return;
                    }
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        etTeacherEmail.setError("Email is Invalid");
                        etTeacherEmail.requestFocus();
                        return;
                    }
                    if(password.isEmpty()){
                        etPassword.setError("Password is Empty");
                        etPassword.requestFocus();
                        return;
                    }
                    if(password.length() < 6){
                        etPassword.setError("Password must be 6 or more characters");
                        etPassword.requestFocus();
                        return;
                    }
                    if(phone.isEmpty()){
                        etTeacherPhoneNo.setError("Phone is Empty");
                        etTeacherPhoneNo.requestFocus();
                        return;
                    }
                    if(phone.length() != 10){
                        etTeacherPhoneNo.setError("Phone Number is less than 10 digits");
                        etTeacherPhoneNo.requestFocus();
                        return;
                    }

                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        TeacherInfo teacherInfo = new TeacherInfo(
                                                name,
                                                email,
                                                phone
                                        );

                                        FirebaseDatabase.getInstance().getReference("Teachers")
                                                .child(name)
                                                .setValue(teacherInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(TeacherRegister.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                                }
                                                else{
                                                    Toast.makeText(TeacherRegister.this, "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                    else {
                                        Toast.makeText(TeacherRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(TeacherRegister.this, "Please Enter Correct Unique ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}