package com.example.teachingaids;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static org.litepal.LitePalApplication.getContext;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rb_1,rb_2;//学生教师
    private ImageView returnView; // 返回按钮
    private EditText tel,realname,password,stuid; // 手机号，姓名输入框
    private Button bt_register; // 注册按钮，获取验证码按钮

    private Toolbar mtoolbar;
    private LinearLayout linearLayout;
    private ArrayAdapter<String> mainsub_adapter,subsubject_adapter;
    private Spinner main_spinner,sub_spinner;
    private List<String> mainsub_list = new ArrayList<>();
    private List<String> subsubject_list = new ArrayList<>();

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

        //学部
        main_spinner = (Spinner) findViewById(R.id.mainsubject_spinner);

        mainsub_list.add("人文科学学部");
        mainsub_list.add("社会科学学部");
        mainsub_list.add("理学部");
        mainsub_list.add("工学部");
        mainsub_list.add("信息科学学部");
        mainsub_list.add("医学部");
        mainsub_list.add("跨学科类");

        mainsub_adapter = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_dropdown_item_1line,mainsub_list) {


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                return v;
            }

            @Override
            public int getCount() {
                Log.w(TAG, "获得getCount为：" + super.getCount());
                return super.getCount()-1; // you don't display last item. It is used as hint.
            }

        };

        mainsub_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mainsub_adapter.add("请选择学部");

        main_spinner.setAdapter(mainsub_adapter);
        main_spinner.setSelection(mainsub_adapter.getCount()); //
        main_spinner.setDropDownVerticalOffset(80);
        main_spinner.setDropDownHorizontalOffset(80);
        main_spinner.setPopupBackgroundResource(R.drawable.view_radius);

        main_spinner.setLayoutMode(Spinner.MODE_DROPDOWN);
        main_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position !=mainsub_list.size()-1) {
                    Toast.makeText(getContext(), "选择了 " + main_spinner.getSelectedItem().toString() + " 学部", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mainsub_adapter.add(getString(R.string.option_click_to_choose));
                //      final_spinner.setSelection(adapter.getCount());
                main_spinner.setAdapter(mainsub_adapter);
                ((BaseAdapter) main_spinner.getAdapter()).notifyDataSetChanged();

            }
        });

        //学院
        sub_spinner = (Spinner) findViewById(R.id.subsubject_spinner);

        subsubject_list.add(" 外国语言文学学院");
        subsubject_list.add("文学院");
        subsubject_list.add("国学院");
        subsubject_list.add("马克思主义学院");
        subsubject_list.add("法学院");
        subsubject_list.add("经济与管理学院");
        subsubject_list.add("历史学院");
        subsubject_list.add("艺术学院");
        subsubject_list.add("新闻与传播学院");
        subsubject_list.add("物理科学与技术学院");
        subsubject_list.add("数学与统计学院");
        subsubject_list.add("信息管理学院");
        subsubject_list.add("教育科学研究院");
        subsubject_list.add("政治与公共管理学院");
        subsubject_list.add("社会学院");
        subsubject_list.add("电气与自动化学院");
        subsubject_list.add("动力与机械学院");
        subsubject_list.add("高等研究院");
        subsubject_list.add("资源与环境科学学院");
        subsubject_list.add("生命科学学院");
        subsubject_list.add("化学与分子科学学院");
        subsubject_list.add("计算机学院");
        subsubject_list.add("电子信息学院");
        subsubject_list.add("工业科学研究院");
        subsubject_list.add("水利水电学院");
        subsubject_list.add("土木建筑工程学院");
        subsubject_list.add("城市设计学院");
        subsubject_list.add("医学研究院");
        subsubject_list.add("医学部机关");
        subsubject_list.add("网络安全学院");
        subsubject_list.add("印刷与包装系");
        subsubject_list.add("遥感信息工程学院");
        subsubject_list.add("测绘学院");
        subsubject_list.add("口腔医学院");
        subsubject_list.add("第二临床学院");
        subsubject_list.add("第一临床学院");
        subsubject_list.add("药学院");
        subsubject_list.add("健康学院");
        subsubject_list.add("基础医学院");
        subsubject_list.add("哲学学院");
        subsubject_list.add("弘毅学堂");
        subsubject_list.add("医学职业技术学院");


        subsubject_adapter = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_dropdown_item_1line, subsubject_list) {


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                return v;
            }

            @Override
            public int getCount() {
                Log.w(TAG, "获得getCount为：" + super.getCount());
                return super.getCount()-1; // you don't display last item. It is used as hint.
            }

        };

        subsubject_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        subsubject_adapter.add("请选择学院");

        sub_spinner.setAdapter(subsubject_adapter);
        sub_spinner.setSelection(subsubject_adapter.getCount()); //
        sub_spinner.setDropDownVerticalOffset(80);
        sub_spinner.setDropDownHorizontalOffset(80);
        sub_spinner.setPopupBackgroundResource(R.drawable.view_radius);

        sub_spinner.setLayoutMode(Spinner.MODE_DROPDOWN);
        sub_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position !=subsubject_list.size()-1) {
                    Toast.makeText(getContext(), "选择了 " + main_spinner.getSelectedItem().toString() + " 学院", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mainsub_adapter.add(getString(R.string.option_click_to_choose));
                //      final_spinner.setSelection(adapter.getCount());
                main_spinner.setAdapter(mainsub_adapter);
                ((BaseAdapter) main_spinner.getAdapter()).notifyDataSetChanged();

            }
        });

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
                      //  String stu_id = stuid.getText().toString().trim(); //学生学号
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
