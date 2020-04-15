package com.example.teachingaids.studentService.ui.MyTask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teachingaids.R;
import com.example.teachingaids.db.Exam;
import com.example.teachingaids.tutorService.TeaMain;
import com.example.teachingaids.tutorService.ui.Class.AddClassActivity;
import com.example.teachingaids.tutorService.ui.Class.ClassFragment;

public class StartExamActivity extends AppCompatActivity {

    private ImageView examretuen,examsubmiit;

    private TextView examtitle;

    private EditText examwrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startexam);

        examretuen = findViewById(R.id.stu_startexam_return);
        examsubmiit = findViewById(R.id.stu_startexam_submit);
        examtitle = findViewById(R.id.stu_startexam_title);
        examwrite = findViewById(R.id.stu_startexam_write);

        examretuen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        examsubmiit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (examwrite != null) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(StartExamActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("是否提交答案？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            MyTaskFragment myTaskFragment = new MyTaskFragment();
                            Bundle bundle = new Bundle();

                            String write = examwrite.toString().trim();
                            bundle.putString("exam_write",write);
                            bundle.putInt("is_write", 2);
                            myTaskFragment.setArguments(bundle);
                            //  onBackPressed();
                            finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
            }
        });

        requestExam();
    }


    private void requestExam() {
        Intent intent = getIntent();
        if("startexam".equals(intent.getAction())) {
            String examTitle = intent.getStringExtra("exam_title");
            if (examTitle == null) {
                return;
            } else {
                examtitle.setText(examTitle);
            }
        }
        return;
    }


}
