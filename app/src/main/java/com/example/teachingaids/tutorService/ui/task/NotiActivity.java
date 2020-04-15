package com.example.teachingaids.tutorService.ui.task;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingaids.R;

public class NotiActivity extends AppCompatActivity {
    //private Toolbar mtoolbar;

    private ImageView returnTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_noti);
        returnTask = findViewById(R.id.iv_notifrag_return);
       //mtoolbar = findViewById(R.id.tb_noti);
        returnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

/*
    //设置toolbar，并添加Back键
    private void setToolbar(){

        setSupportActionBar(mtoolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_left_circle);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
        }
        return true;
    }*/
}
