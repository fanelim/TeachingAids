package com.example.teachingaids.tutorService;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.teachingaids.R;
import com.example.teachingaids.tutorService.ui.Class.ClassFragment;
import com.example.teachingaids.tutorService.ui.home.HomeFragment;
import com.example.teachingaids.tutorService.ui.task.TaskFragment;
import com.example.teachingaids.util.NoSlideViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeaMain extends AppCompatActivity {
    private NoSlideViewPager viewPager;
    private BottomNavigationView navigationView;
    private MenuItem menuItem;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.stu_navigation_class:
                            viewPager.setCurrentItem(0);
                            return true;
                        case R.id.tea_navigation_task:
                            viewPager.setCurrentItem(1);
                            return true;
                        case R.id.tea_navigation_home:
                            viewPager.setCurrentItem(2);
                            return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tea);

        navigationView = findViewById(R.id.tea_nav_view);
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        viewPager = findViewById(R.id.tea_vp);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Fragment myfragment = null;
                switch (position) {
                    case 0:
                        myfragment = new ClassFragment();
                        break;
                    case 1:
                        myfragment = new TaskFragment();
                        break;
                    case 2:
                        myfragment = new HomeFragment();
                        break;
                }
                return myfragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // ignore
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false); // 将原先的设为取消
                } else {
                    navigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // ignore
            }
        });
    }

}


