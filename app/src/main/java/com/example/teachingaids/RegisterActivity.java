package com.example.teachingaids;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.teachingaids.tutorService.TeaMain;
import com.example.teachingaids.tutorService.ui.Class.AddClassActivity;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rb_1,rb_2;//学生教师
    private ImageView returnView; // 返回按钮
    private EditText tel,realname,password,stuid; // 手机号，姓名输入框
    private Button bt_register; // 注册按钮，获取验证码按钮

    private Toolbar mtoolbar;
    private LinearLayout linearLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        init();
        linearLayout = (LinearLayout)findViewById(R.id.layout_stu_id);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });
        rb_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                stuid = (EditText)findViewById(R.id.stu_id);
            }
        });
        boolean rb2 = rb_2.isChecked();
        if (!rb2)
            linearLayout.setVisibility(View.INVISIBLE);

        ImageView back =(ImageView)findViewById(R.id.iv_registerback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void init(){
        tel = (EditText) findViewById(R.id.et_phone_num);
        realname = (EditText) findViewById(R.id.et_realName);
        password = (EditText) findViewById(R.id.et_password);
        bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt_register:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String userTel = tel.getText().toString().trim(); //电话号
                        String userName = realname.getText().toString().trim(); //姓名
                        String userPassword = password.getText().toString().trim(); //密码
                        String stu_id = stuid.getText().toString().trim(); //学生学号
                        if (userTel.equals("") || userName.equals("") || userPassword.equals("")) {
                            Looper.prepare();
                           Toast.makeText(RegisterActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                            Looper.loop();
                        }
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
