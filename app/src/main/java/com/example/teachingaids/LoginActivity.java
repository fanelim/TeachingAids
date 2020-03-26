package com.example.teachingaids;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton rb_1;//学生教师
    private EditText editUser, editPassword;//用户密码输入框
    private Button bt_login,bt_register;//登陆注册按钮
    private ImageView wechat, QQ;//微信QQ
    private String currentUsername, currentPassword;//当前输入

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }

    private void init() {

        editUser = (EditText) findViewById(R.id.ET_username);
        editPassword = (EditText) findViewById(R.id.ET_password);
        rb_1 = (RadioButton) findViewById(R.id.RB_1);

        bt_login = (Button) findViewById(R.id.BT_login);
        bt_register = (Button) findViewById(R.id.BT_register);
        wechat = (ImageView) findViewById(R.id.IV_wechat);
        QQ = (ImageView) findViewById(R.id.IV_QQ);


        // rb_stu.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        bt_register.setOnClickListener(this);
        wechat.setOnClickListener(this);
        QQ.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.BT_login:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String account = editUser.getText().toString().trim();
                        String psw = editPassword.getText().toString().trim();
                        boolean rb1 = rb_1.isChecked();

                        if (account.equals("") || psw.equals("")) {
                            Looper.prepare();
                         Toast.makeText(LoginActivity.this, "Input can not be empty！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                        if (rb1&&account.equals("001t") && psw.equals("123456")) {



                            Looper.prepare();
                            Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,TeaMain.class);
                            intent.putExtra("name",account);
                            startActivity(intent);
                            Looper.loop();



                        } else if (!rb1 &&account.equals("001s") && psw.equals("123456")) {
                            Looper.prepare();
                            Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            //一下代码为跳转学生界面
                          //  Intent intent=new Intent(LoginActivity.this,StuMain.class);
                            //intent.putExtra("name",n);
                            // startActivity(intent);
                        } else {
                            Looper.prepare();
                            Toast.makeText(LoginActivity.this, "Wrong user or password！", Toast.LENGTH_SHORT).show();
                            Looper.loop();

                        }

                        //以上为jdbc登录
                    }
                }).start();

                break;
            case R.id.BT_register:
                /*
                 * 跳转注册页面*/
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.IV_QQ:
                Toast.makeText(this, "QQ login", Toast.LENGTH_SHORT).show();
                break;

            case R.id.IV_wechat:
                Toast.makeText(this, "Wechat login", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
