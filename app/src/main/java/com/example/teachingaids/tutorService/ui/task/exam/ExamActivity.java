package com.example.teachingaids.tutorService.ui.task.exam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.teachingaids.R;
import com.example.teachingaids.db.Exam;
import com.example.teachingaids.db.FakeDataBase;
import com.example.teachingaids.db.Question;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class ExamActivity extends AppCompatActivity {
    public static final int LEVEL_EXAM_LIST = 0;
    public static final int LEVEL_QUESTION_LIST = 1;

    private ImageView examfragreturn, examfragadd;
    private TextView title;
    private TextView subTitle;
    private Button subBackBtn;
    private ListView listView;
    private SimpleAdapter adapter;

    private static final AtomicInteger VAL = new AtomicInteger(0);

    List<Map<String, String>> dataList = new ArrayList<>();
    List<Exam> examList = new ArrayList<>();
    List<Question> questionList = new ArrayList<>();

    private int currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_exam);

        examfragreturn = findViewById(R.id.examfrag_return);
        examfragadd = findViewById(R.id.examfragadd);
        title = findViewById(R.id.exam_ac_title);
        subTitle = findViewById(R.id.tea_exam_title);
        subBackBtn = findViewById(R.id.tea_exam_back_button);
        listView = (ListView) findViewById(R.id.lv_exam);

        examfragreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        examfragadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_EXAM_LIST) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ExamActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("是否需要添加新测验？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            List<Exam> list = FakeDataBase.classId2ExamList.get(1L);
                            Exam exam = new Exam();
                            exam.setExamTitle("test");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                LocalDate now = LocalDate.now();
                                exam.setExamTitle(now.getYear() + "_" + String.format("%02d", now.getMonthValue()) + "_" +
                                        now.getDayOfMonth() + " 测验（" + VAL.incrementAndGet() + "）");
                            }
                            list.add(0, exam);
                            queryExam();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // ignore
                        }
                    });
                    builder.show();
                } else {
                    Intent intent = new Intent(ExamActivity.this, AddExamActivity.class);
                    startActivity(intent);
                }
            }
        });
        subBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryExam();
            }
        });

        Intent intent = getIntent();
        String className = intent.getStringExtra("className");
        title.setText(className + " - 测验");

        adapter = new SimpleAdapter(this, dataList, android.R.layout.simple_list_item_2, new String[]{"title", "score"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_EXAM_LIST) {
                    queryQuestion();
                }
            }
        });
        queryExam();
    }

    private void queryExam() {
        currentLevel = LEVEL_EXAM_LIST;
        subTitle.setText("测验列表");
        subBackBtn.setVisibility(View.GONE);

        dataList.clear();
        for (Exam exam : FakeDataBase.classId2ExamList.get(1L)) {
            Map<String, String> map = new HashMap<>();
            map.put("title", exam.getExamTitle());
            map.put("score", "");
            dataList.add(map);
        }

        adapter.notifyDataSetChanged();
        listView.setSelection(0);
    }

    private void queryQuestion() {
        currentLevel = LEVEL_QUESTION_LIST;
        subTitle.setText("题目列表");
        subBackBtn.setVisibility(View.VISIBLE);

        dataList.clear();
        questionList = FakeDataBase.examId2QuestionList.get(1L);
        for (int i = 0; i < questionList.size(); i++) {
            Question q = questionList.get(i);
            Map<String, String> map = new HashMap<>();
            map.put("title", i + 1 + ". " + q.getTitle());
            map.put("score", "分值: " + q.getScore());
            dataList.add(map);
        }

        adapter.notifyDataSetChanged();
        listView.setSelection(0);
    }


}
