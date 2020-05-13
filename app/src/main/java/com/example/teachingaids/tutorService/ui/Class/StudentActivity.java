package com.example.teachingaids.tutorService.ui.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.teachingaids.R;
import com.example.teachingaids.db.Exam;
import com.example.teachingaids.db.FakeDataBase;
import com.example.teachingaids.db.Stu;
import com.example.teachingaids.tutorService.TeaMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentActivity extends AppCompatActivity {
    private TextView nameTextView, stuIdTextView, phoneTextView, signCountTextView;
    private ImageView backBtn;

    private ListView listView;
    private SimpleAdapter simpleAdapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_student);

        nameTextView = findViewById(R.id.show_name);
        stuIdTextView = findViewById(R.id.show_stuid);
        phoneTextView = findViewById(R.id.show_phone);
        signCountTextView = findViewById(R.id.show_signcount);
        backBtn = findViewById(R.id.iv_stureturn);
        listView = findViewById(R.id.stu_test_view);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        Long stuId = intent.getLongExtra("stuId", 1L);
        Stu stu = FakeDataBase.stuId2Stu.get(stuId);
        List<Exam> examList = FakeDataBase.stuId2ExamList.get(stuId);
        nameTextView.setText(stu.getStuName());
        stuIdTextView.setText("" + stu.getStuId());
        phoneTextView.setText(stu.getStuPhone());
        signCountTextView.setText("" + stu.getSigncount());

        List<Map<String, String>> res = new ArrayList<>();
        for (Exam exam : examList) {
            Map<String, String> map = new HashMap<>();
            map.put("testName", exam.getExamTitle());
            map.put("testScore", "" + exam.getExamScore());
            res.add(map);
        }

        simpleAdapter = new SimpleAdapter(this, res, android.R.layout.simple_list_item_2, new String[]{"testName", "testScore"},
                new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }

}
