package com.example.teachingaids.tutorService.ui.Class;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingaids.BuildConfig;
import com.example.teachingaids.R;
import com.example.teachingaids.tutorService.TeaMain;


public class AddClassActivity extends AppCompatActivity {

    private EditText className,classContent;
    private Button addClass;
    private ImageView back;
    private TextView textName,textContent;
    private Long id = Long.valueOf(3);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_newclass);
        className = (EditText) findViewById(R.id.et_classname);
        classContent = (EditText) findViewById(R.id.et_classcontent);

        ImageView add = (ImageView)findViewById(R.id.iv_newclassadd);
        ImageView back =(ImageView)findViewById(R.id.iv_newclassreturn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddClassActivity.this,TeaMain.class);
                startActivity(intent);
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = className.getText().toString().trim();
                String Content = classContent.getText().toString().trim();

                if (Name.equals("") || Content.equals("")){
                    Toast.makeText(AddClassActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();
                    if (BuildConfig.DEBUG) Log.d("AddClassActivity", "班级建立失败");                                    Looper.loop();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddClassActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("是否确定提交？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //跳转回去
                            String after_name = className.getText().toString();
                            String after_content = classContent.getText().toString();

                            ClassFragment classFragment = new ClassFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("class_name", after_name);
                            bundle.putString("class_content", after_content);
                            classFragment.setArguments(bundle);
                            //  onBackPressed();
                            Intent intent = new Intent(AddClassActivity.this, TeaMain.class);
                            startActivity(intent);
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

    }

}