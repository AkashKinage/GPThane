package com.example.gpthane.Student.Drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.gpthane.R;
import com.example.gpthane.Student.StudentActivity;

import java.util.ArrayList;

public class About extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageSlider imgslider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_about);


        drawerLayout = findViewById(R.id.drawer_layout);

        imgslider = findViewById(R.id.imgslider);

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel("https://lh5.googleusercontent.com/p/AF1QipOAzDXwRLeSpFm_mSnJCNPcV6k1mNcpbT1RpxOc=w203-h114-k-no",null));
        images.add(new SlideModel("https://lh5.googleusercontent.com/p/AF1QipMk8V58bwe-9BvcEB9w2YFKBZCRor06f3Qhmj8l=s643-k-no",null));
        images.add(new SlideModel("https://lh5.googleusercontent.com/p/AF1QipNn14_cy-Ftow7ioH50F58y8tjxLCM-vZZFXmNk=s635-k-no",null));
        images.add(new SlideModel("https://lh5.googleusercontent.com/p/AF1QipP-gJlLuQhIWB6CKLQOApkPfkkq8YbXoakZ8R74=s644-k-no",null));

        imgslider.setImageList(images);
    }

    public void ClickMenu(View view){
        StudentActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        StudentActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        StudentActivity.redirectActivity(this,StudentActivity.class);
    }

    public void ClickContactUs(View view){
        StudentActivity.redirectActivity(this,Contact.class);
    }

    public void ClickAboutUs(View view){
        recreate();
    }

    public void ClickLogout(View view){
        StudentActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StudentActivity.closeDrawer(drawerLayout);
    }

}