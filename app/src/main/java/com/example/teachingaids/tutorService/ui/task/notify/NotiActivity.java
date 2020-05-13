package com.example.teachingaids.tutorService.ui.task.notify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingaids.R;

public class NotiActivity extends AppCompatActivity {
    private ImageView returnTask;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_noti);

        textView = findViewById(R.id.tb_noti_text);
        returnTask = findViewById(R.id.iv_notifrag_return);

        Intent intent = getIntent();
        String className = intent.getStringExtra("className");
        textView.setText(className + " - 通知");

        returnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
