package com.example.teachingaids.tutorService.ui.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.teachingaids.R;
import com.example.teachingaids.db.Exam;

import java.util.ArrayList;
import java.util.List;

import static org.litepal.LitePalApplication.getContext;


public class ExamActivity extends AppCompatActivity {

    private ListView listView;


    private ArrayAdapter<String > adapter;

    private boolean isCreate;

    private List<String> dataList = new ArrayList<>();

    private static List<Exam> examList  = new ArrayList<>();

    private ImageView examfragreturn,examfragadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_exam);
        listView = (ListView)findViewById(R.id.lv_exam);
        initexam();
        adapter = new android.widget.ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        requestExam();
        examfragadd= findViewById(R.id.examfragadd);
        examfragreturn = findViewById(R.id.examfrag_return);
        examfragadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamActivity.this,AddExamActivity.class);
                startActivity(intent);
                finish();
            }
        });
        examfragreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void requestExam() {
        Intent intent = getIntent();
        if("addexam".equals(intent.getAction())) {
            String examScore = intent.getStringExtra("exam_score");
            String examTitle = intent.getStringExtra("exam_title");
            if (examTitle == null) {
                return;
            } else {

                Exam exam = new Exam();
                exam.setExamTitle(examTitle);
                exam.setExamScore(Integer.parseInt(examScore));
                examList.add(exam);
                dataList.add("题目   "+exam.getExamTitle() +"  分值"+exam.getExamScore());
                adapter.notifyDataSetChanged();
                listView.setSelection(0);
            }
        }
        return;
    }

    private void initexam() {
            examList.clear();
            Exam e1 = new Exam();
            e1.setExamTitle("指出f(x)=1/(1-x),x=1 函数在指定点是否间断，如果间断，指出是哪类间断点。");
            e1.setExamScore(100);
            examList.add(e1);

            Exam e2 = new Exam();
            e2.setExamTitle("为什么收敛的数列必有界");
            e2.setExamScore(100);
            examList.add(e2);

            dataList.clear();
            for (Exam exam : examList) {
                dataList.add("题目   "+exam.getExamTitle() +"  分值"+exam.getExamScore());
            }
            // adapter.notifyDataSetChanged();
            listView.setSelection(0);

        return;

    }
}
