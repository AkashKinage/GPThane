package com.example.gpthane.Teacher;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpthane.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class TeacherLogin extends AppCompatActivity {

    private EditText etTeacherEmail, etTeacherPassword;
    private TextView tvTeacherRegister;
    private Button btnTeacherLogin;
    private FirebaseAuth auth;
    //private ProgressBar pd;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_teacher_login);

        etTeacherEmail = findViewById(R.id.etTeacherEmail);
        etTeacherPassword = findViewById(R.id.etPassword);

        tvTeacherRegister = findViewById(R.id.tvTeacherRegister);

        btnTeacherLogin = findViewById(R.id.btnTeacherLogin);

        auth = FirebaseAuth.getInstance();

        //pd = new ProgressBar(this);

        tvTeacherRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TeacherRegister.class));
            }
        });

        btnTeacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pd.setVisibility(View.VISIBLE);

                progressDialog = new ProgressDialog(TeacherLogin.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progressbar);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                String email = etTeacherEmail.getText().toString();
                String password = etTeacherPassword.getText().toString();

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
                    etTeacherPassword.setError("Password is Empty");
                    etTeacherPassword.requestFocus();
                    return;
                }
                if(password.length() < 6){
                    etTeacherPassword.setError("Password must be 6 or more characters");
                    etTeacherPassword.requestFocus();
                    return;
                }
                else{
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //pd.setVisibility(View.GONE);

                                progressDialog.dismiss();

                                Toast.makeText(TeacherLogin.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), TeacherActivity.class));
                            }
                            else{
                                Toast.makeText(TeacherLogin.this, "Login Unsuccessful!", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }
}
