package com.example.teachingaids.ui.task;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.teachingaids.R;

public class AddNotiActivity extends AppCompatActivity {

    private EditText mTiltle;

    private EditText mContent;

    private boolean isCreate;

    private String before_title;

    private String before_content;

    private long mId;

    private int position_in_the_list;

    private ImageView notiadd,notireturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        initText();
    }


    private void initText(){
        mTiltle = findViewById(R.id.edit_note_title_et);
        mContent = findViewById(R.id.edit_note_content_et);
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
                            /*String after_title = mTiltle.getText().toString();
                            String after_content = mContent.getText().toString();
                            if (!after_title.equals(before_title) || !after_content.equals(before_content)) {
                                // 是修改，且有内容变换
                                int position = LitePal.order("position desc")
                                        .findFirst(NotiCard.class)
                                        .getPosition() + 1; //找到最大位置的
                                NotiCard notiCard = LitePal.find(NotiCard.class,mId);   //在数据库中按ID值查找
                                NotiFragment.notiCardList.remove(position_in_the_list); //删除原先位置的卡片
//                        Log.d("my", "有修改1 "+System.identityHashCode(NoteFragment.noteCardList));
                                // 更新数值
                                // 设置时间格式及获取当前时间
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new Date(System.currentTimeMillis());
                                notiCard.setPosition(position);
                                notiCard.setTitle(after_title);
                                notiCard.setContent(after_content);
                                notiCard.setTime(simpleDateFormat.format(date));
                                notiCard.save();
                                NotiFragment.notiCardList.add(0, notiCard);   //将新增的notecard添加在列表的第一个
                                NotiFragment.notiAdapter.notifyDataSetChanged();
//                        Log.d("my", "有修改2 "+System.identityHashCode(NoteFragment.noteCardList));
                            }*/
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
        mId = intent.getLongExtra("id",0);
        position_in_the_list = intent.getIntExtra("position_in_the_list",0);
        before_title = intent.getStringExtra("title");
        before_content = intent.getStringExtra("content");

        if (before_title != null && before_content != null) {
            //内容不为空,是修改
            //已在EditText的父布局允许获取焦点，那么EditText的焦点则被隔绝，从而不会自动获取焦点
            isCreate = false;
            mTiltle.setText(before_title);
            mContent.setText(before_content);
        }
        else {
            //内容为空,是新创建
            isCreate = true;
            //标题获得焦点，自动弹出软键盘
            mTiltle.setFocusable(true);
            mTiltle.setFocusableInTouchMode(true);
            mTiltle.requestFocus(); //强制获取焦点
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
