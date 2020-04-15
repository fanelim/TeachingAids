package com.example.teachingaids.studentService.ui.MyTask;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.teachingaids.R;
import com.example.teachingaids.db.Class;
import com.example.teachingaids.db.Exam;
import com.example.teachingaids.db.Stu;
import com.example.teachingaids.tutorService.ui.Class.AddClassActivity;
import com.example.teachingaids.tutorService.ui.Class.StudentActivity;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class MyTaskFragment extends Fragment {


    private Spinner final_spinner;

    private ArrayList<Exam> examList = new ArrayList<>();

    private ArrayList<Class> classList = new ArrayList<>();

    private List<String> class_data_list = new ArrayList<>();

    private List<String> exam_data_list = new ArrayList<>();

    private static List<Exam> selectedexamList = new ArrayList<>();

    private ArrayAdapter<String> class_adapter;

    private Exam selectedexam;

    private TextView title;

    private ListView listView;

    private Boolean written = false;

    private ArrayAdapter<String > exam_adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.stu_task_frag, container, false);
        final_spinner = (Spinner) root.findViewById(R.id.stu_task_spinner);
        listView = root.findViewById(R.id.stu_task_listview);
        initExam();
        initClass();
        showWritten();
        return root;
    }

    private void showWritten() {
        Bundle bundle = getArguments();
        if (bundle == null){
            return;
        }
        String write = bundle.get("exam_write").toString().trim();
    }

    private void initExam() {

        Exam e1 = new Exam();
        e1.setExam_classid(Long.valueOf(1));
        e1.setExamTitle("指出f(x)=1/(1-x),x=1 函数在指定点是否间断，如果间断，指出是哪类间断点。");
        e1.setExamScore(100);
        examList.add(e1);

        Exam e2 = new Exam();
        e2.setExam_classid(Long.valueOf(1));
        e2.setExamTitle("为什么收敛的数列必有界");
        e2.setExamScore(100);
        examList.add(e2);

        Exam e3 = new Exam();
        e3.setExam_classid(Long.valueOf(3));
        e3.setExamTitle("设向量α、β的长度依次为2和3,则向量α+β与α-β的内积(α+β,α-β)= ");
        e3.setExamScore(100);
        examList.add(e3);

       // Adapter exam_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,data_list);
        exam_adapter = new android.widget.ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,exam_data_list);
        listView.setSelection(0);
        listView.setAdapter((ListAdapter) exam_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectexam = exam_data_list.get(position);

                Toast.makeText(getContext(), "选中了" + exam_data_list.get(position), Toast.LENGTH_SHORT).show();
                showTask(selectexam);
            }

            private void showTask(final String examtitle) {
                if (!written) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("提示");
                    builder.setMessage("是否确定答题？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getActivity(), StartExamActivity.class);
                            intent.setAction("startexam");
                            intent.putExtra("exam_title", examtitle);
                            startActivity(intent);

                            written = true;
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
                else {
                    Toast.makeText(getContext(), "已答过此题", Toast.LENGTH_SHORT).show();

                }
            };
        });
        return;
    }

    private void initClass() {

        Class a1 = new Class();
        a1.setClassName("高等数学");
        a1.setId(Long.valueOf(1));
        classList.add(a1);

        Class a2 = new Class();
        a2.setClassName("大学英语");
        a2.setId(Long.valueOf(2));
        classList.add(a2);

        Class a3 = new Class();
        a3.setClassName("线性代数");
        a3.setId(Long.valueOf(3));
        classList.add(a3);

        class_data_list.clear();
        for (Class a : classList){
            class_data_list.add(a.getClassName());
        }
        //适配器 系统默认布局  android.R.layout.simple_spinner_item
        // 可以自定义

        class_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, class_data_list) {

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

        class_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        class_adapter.add(getString(R.string.option_click_to_choose));

        final_spinner.setAdapter(class_adapter);
        final_spinner.setSelection(class_adapter.getCount()); //
        final_spinner.setDropDownVerticalOffset(80);
        final_spinner.setDropDownHorizontalOffset(80);
        final_spinner.setPopupBackgroundResource(R.drawable.view_radius);
        final_spinner.setLayoutMode(Spinner.MODE_DROPDOWN);

        final_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != class_data_list.size()-1) {
                    Toast.makeText(getContext(), "选择了 " + final_spinner.getSelectedItem().toString() + " 班级", Toast.LENGTH_SHORT).show();
                    exam_data_list.clear();
                    selectedexamList.clear();
                    for (Class c: classList) {
                        for (Exam exam : examList) {
                            if (c.getClassName() == final_spinner.getSelectedItem().toString()) {
                                if (c.getId() == exam.getExam_classid())
                                    exam_data_list.add("题目： " + exam.getExamTitle() + " 分值: " + exam.getExamScore());
                                    selectedexamList.add(exam);
                            }
                        }
                    }
                    listView.setSelection(0);
                    exam_adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                class_adapter.add(getString(R.string.option_click_to_choose));
                //      final_spinner.setSelection(adapter.getCount());
                final_spinner.setAdapter(class_adapter);
                ((BaseAdapter) final_spinner.getAdapter()).notifyDataSetChanged();

            }
        });
        return;
    }
}
