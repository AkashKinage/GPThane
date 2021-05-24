package com.example.gpthane.Student.Drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.gpthane.R;
import com.example.gpthane.Student.StudentActivity;

public class Contact extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removing ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_contact);

        drawerLayout = findViewById(R.id.drawer_layout);
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
        recreate();
    }

    public void ClickAboutUs(View view){
        StudentActivity.redirectActivity(this,About.class);
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