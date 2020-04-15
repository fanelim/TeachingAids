package com.example.teachingaids.studentService;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.teachingaids.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StuMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_stu);
        BottomNavigationView navView = findViewById(R.id.stu_nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.stu_navigation_class, R.id.stu_navigation_task, R.id.stu_navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.stu_nav_host_fragment);
      //  NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
