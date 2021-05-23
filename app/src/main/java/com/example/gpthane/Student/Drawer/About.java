package com.example.gpthane.Student.Drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.gpthane.R;
import com.example.gpthane.Student.StudentActivity;

public class About extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

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