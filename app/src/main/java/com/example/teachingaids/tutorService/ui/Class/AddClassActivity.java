package com.example.teachingaids.tutorService.ui.Class;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingaids.R;


public class AddClassActivity extends AppCompatActivity {

    private EditText className, classContent;
    private ImageView back, add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_newclass);

        className = (EditText) findViewById(R.id.et_classname);
        classContent = (EditText) findViewById(R.id.et_classcontent);
        back =(ImageView)findViewById(R.id.iv_newclassreturn);
        add = (ImageView)findViewById(R.id.iv_newclassadd);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = className.getText().toString().trim();
                String Content = classContent.getText().toString().trim();

                if (Name.equals("") || Content.equals("")){
                    Toast.makeText(AddClassActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddClassActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("是否确定提交？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //跳转回去
                            String after_name = className.getText().toString();
                            String after_content = classContent.getText().toString();
                            Intent intent = new Intent();
                            intent.putExtra("className", after_name);
                            intent.putExtra("classContent", after_content);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // ignore
                        }
                    });
                    builder.show();
                }
            }
        });

    }

}