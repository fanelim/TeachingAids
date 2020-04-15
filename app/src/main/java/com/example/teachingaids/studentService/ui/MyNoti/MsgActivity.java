package com.example.teachingaids.studentService.ui.MyNoti;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.teachingaids.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MsgActivity extends AppCompatActivity {

    public static final String MSG_TITLE = "msg_title";

    public static final String MSG_CLASSNAME = "msg_classname";

    public static final String MSG_CONTENR = "msg_content";

    private TextView classname,msgcontent,msgtitle;

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        init();

    }

    private void init() {
        classname=findViewById(R.id.stu_msg_classname);
        imageView=findViewById(R.id.stu_msg_return);
        msgtitle =findViewById(R.id.stu_msg_classt);
        msgcontent=findViewById(R.id.stu_msg_classc);


        //获取跳转过来的数据
        Intent intent = getIntent();
        String msgTitle = intent.getStringExtra(MSG_TITLE);
        String className = intent.getStringExtra(MSG_CLASSNAME);
        String msgContent = intent.getStringExtra(MSG_CONTENR);

        classname.setText(className);
        msgtitle.setText(msgTitle);
        msgcontent.setText(msgContent);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });
    }


}
