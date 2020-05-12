package com.example.teachingaids.tutorService.ui.Class;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingaids.R;
import com.example.teachingaids.studentService.ui.MyClass.StuActivity;
import com.example.teachingaids.tutorService.TeaMain;

public class StudentTestActivity extends AppCompatActivity {

    private EditText test_title,test_content;
    private ImageView returnstu;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_stu_test);
        init();

    }

    private void init() {
        test_title = (EditText) findViewById(R.id.et_test_title);
        test_content = (EditText) findViewById(R.id.et_test_content);
        returnstu = (ImageView) findViewById(R.id.iv_testreturn);
        test_content.setText("解:∵lim<x,1->[1/(1-x)]=+∞,lim<x,1+>[1/(1-x)]=-∞,\n" +
                "∴lim<x,1->[1/(1-x)]=≠lim<x,1+>[1/(1-x)]\n" +
                "∴f(x)在x=1处是第二类间断点。");
        test_title.setText("指出f(x)=1/(1-x),x=1 函数在指定点是否间断，如果间断，指出是哪类间断点");

        returnstu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentTestActivity.this, StudentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
