package com.example.gpthane.Teacher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gpthane.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class TeacherUploadNotice extends AppCompatActivity {

    private ImageView imageView;
    private Button btnChooseImage, btnUploadImage;
    private EditText etNoticeTitle;
    private ProgressBar progressBar;
    private String emailstr;
    private Uri imageUri;

    private static final int PICK_IMAGE_REQUEST = 1;

    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    private StorageReference storageReference;
    private DatabaseReference databaseReference, teacherRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_upload_notice);

        imageView = findViewById(R.id.imageView);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        etNoticeTitle = findViewById(R.id.etNoticeTitle);
        progressBar = findViewById(R.id.progressBar);

        storageReference = FirebaseStorage.getInstance().getReference("Notice Image");
        databaseReference = FirebaseDatabase.getInstance().getReference("Notice");

        user = FirebaseAuth.getInstance().getCurrentUser();
        emailstr = user.getEmail();

        firebaseDatabase = FirebaseDatabase.getInstance();
        teacherRef = firebaseDatabase.getReference("Teachers");

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImage();
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

        private void UploadImage() {
//        if (imageUri != null){
//            StorageReference filereference = storageReference.child(System.currentTimeMillis() +"."+getFileExtension(imageUri));
//
//            filereference.putFile(imageUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            progressBar.setVisibility(View.GONE);
//                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
//                            while (!urlTask.isSuccessful());
//                            Uri downloadUrl = urlTask.getResult();
//                            Toast.makeText(TeacherUploadNotice.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//                            UploadImg uploadImg = new UploadImg(etNoticeTitle.getText().toString().trim(),
//                                    downloadUrl.toString());
//                            String uploadId = databaseReference.push().getKey();
//                            databaseReference.child(uploadId).setValue(uploadImg);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull @NotNull Exception e) {
//                            Toast.makeText(TeacherUploadNotice.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//        else {
//            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
//        }



            if (imageUri != null){
                StorageReference filereference = storageReference.child(System.currentTimeMillis() +"."+getFileExtension(imageUri));

                filereference.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressBar.setVisibility(View.GONE);
                                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                                while (!urlTask.isSuccessful());
                                Uri downloadUrl = urlTask.getResult();
                                Toast.makeText(TeacherUploadNotice.this, "Upload Successful", Toast.LENGTH_SHORT).show();

                                teacherRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()){
                                            Log.d("ds",ds.child("department").toString());
                                            String email = (String) ds.child("email").getValue();
                                            if(email.equals(emailstr)){
                                                Log.d("result","success");
                                                Log.d("result",ds.child("department").getValue().toString());
                                                String department = ds.child("department").getValue().toString();
                                                Log.d("department",department);

                                                UploadImg uploadImg = new UploadImg(etNoticeTitle.getText().toString().trim(),
                                                        downloadUrl.toString());
                                                String uploadId = databaseReference.push().getKey();
                                                databaseReference.child(department).child(uploadId).setValue(uploadImg);
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(TeacherUploadNotice.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
            }
    }

    private void ChooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST || resultCode == RESULT_OK
        || data != null || data.getData() != null){
            imageUri = data.getData();

            Picasso.get().load(imageUri).into(imageView);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}