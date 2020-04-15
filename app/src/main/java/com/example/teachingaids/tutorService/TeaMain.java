package com.example.teachingaids.tutorService;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.teachingaids.R;
import com.example.teachingaids.tutorService.ui.Class.ClassFragment;
import com.example.teachingaids.tutorService.ui.home.HomeFragment;
import com.example.teachingaids.tutorService.ui.task.TaskFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeaMain extends AppCompatActivity {

    //默认选择第一个fragment
    private int lastSelectedPosition = 0;

    private BottomNavigationView navView;

    private ClassFragment classFragment;
    private HomeFragment homeFragment;
    private TaskFragment taskFragment;

    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tea);
         navView = findViewById(R.id.tea_nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.tea_navigation_class, R.id.tea_navigation_task,R.id.tea_navigation_home)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.tea_nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);


    }

}


