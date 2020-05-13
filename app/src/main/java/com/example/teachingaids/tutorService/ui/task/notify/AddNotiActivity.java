package com.example.teachingaids.tutorService.ui.task.notify;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.teachingaids.R;

public class AddNotiActivity extends AppCompatActivity {

    private EditText mTiltle;
    private EditText mContent;
    private TextView textView;
    private ImageView notireturn, notiadd;


    private boolean isCreate;
    private String before_title;
    private String before_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_newnoti);
        initText();
    }


    private void initText() {
        mTiltle = findViewById(R.id.edit_note_title_et);
        mContent = findViewById(R.id.edit_note_content_et);
        textView = findViewById(R.id.tb_new_noti_title);
        notiadd = findViewById(R.id.iv_notiadd);
        notireturn = findViewById(R.id.iv_notireturn);

        notiadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCreate) {
                    //标题或内容有输入
                    if (mTiltle.getText().toString().equals("") ||
                            mContent.getText().toString().equals("")) {
                        Toast.makeText(AddNotiActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddNotiActivity.this);
                        builder.setTitle("提示");
                        builder.setMessage("是否确定提交？");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.putExtra("title", mTiltle.getText().toString());
                                intent.putExtra("content", mContent.getText().toString());
                                setResult(RESULT_OK, intent);
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
            }

        });

        notireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // 获取点击的NoteCard的信息
        Intent intent = getIntent();
        before_title = intent.getStringExtra("title");
        before_content = intent.getStringExtra("content");

        if (before_title != null && before_content != null) {
            //内容不为空,是修改
            //已在EditText的父布局允许获取焦点，那么EditText的焦点则被隔绝，从而不会自动获取焦点
            isCreate = false;
            mTiltle.setText(before_title);
            mContent.setText(before_content);

            textView.setText("通知浏览");
            notiadd.setVisibility(View.GONE);
        } else {
            //内容为空,是新创建
            isCreate = true;
            //标题获得焦点，自动弹出软键盘
            mTiltle.setFocusable(true);
            mTiltle.setFocusableInTouchMode(true);
            mTiltle.requestFocus(); //强制获取焦点
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

            textView.setText("添加通知");
            notiadd.setVisibility(View.VISIBLE);
        }
    }

}
