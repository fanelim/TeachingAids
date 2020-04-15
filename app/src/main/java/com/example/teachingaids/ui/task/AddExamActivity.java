package com.example.teachingaids.ui.task;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.teachingaids.LoginActivity;
import com.example.teachingaids.R;
import com.example.teachingaids.RegisterActivity;
import com.example.teachingaids.db.Exam;
import com.example.teachingaids.ui.Class.StudentActivity;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddExamActivity extends AppCompatActivity {
    //写题目，写分值，返回，添加成功

    private EditText examtitle,examscore;


    private ImageView addexam,returnexam;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexam);
        init();
    }

    private void init() {
        addexam = (ImageView) findViewById(R.id.iv_classadd);
        returnexam = (ImageView) findViewById(R.id.iv_classreturn);

        addexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examtitle= (EditText)findViewById(R.id.et_examTitle);
                examscore = (EditText)findViewById(R.id.et_examScore);
             //   examscore.setInputType(InputType.TYPE_CLASS_NUMBER);

                if (examtitle.getText().toString().equals("") || examscore.getText().toString().equals("")) {

                    Toast.makeText(AddExamActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddExamActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("是否确定提交？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent =new Intent(AddExamActivity.this,ExamActivity.class);
                    intent.setAction("addexam");
                    intent.putExtra("exam_title",examtitle.getText().toString());
                    intent.putExtra("exam_score",examscore.getText().toString());
                    setResult(0,intent);
                    startActivity(intent);
                    Toast.makeText(AddExamActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
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

        returnexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
