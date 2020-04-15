package com.example.teachingaids.studentService.ui.MyClass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.teachingaids.LoginActivity;
import com.example.teachingaids.R;

    public class StuActivity extends AppCompatActivity {
        private ImageView stureturn;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.stu_activity_stu);
            Button signout = (Button)findViewById(R.id.signout);
            stureturn=findViewById(R.id.iv_myinfo);
            stureturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            signout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StuActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

}
