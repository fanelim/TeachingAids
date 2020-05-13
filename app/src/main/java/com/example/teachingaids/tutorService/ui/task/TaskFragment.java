package com.example.teachingaids.tutorService.ui.task;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.teachingaids.R;
import com.example.teachingaids.db.Class;
import com.example.teachingaids.db.FakeDataBase;
import com.example.teachingaids.tutorService.ui.task.exam.ExamActivity;
import com.example.teachingaids.tutorService.ui.task.notify.NotiActivity;
import com.example.teachingaids.util.ExcelUtil;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {

    private ImageView noti, exam, setCount, setGrade, export, delete;
    private Spinner final_spinner;

    private List<String> data_list = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tea_task_frag, container, false);

        final_spinner = (Spinner) view.findViewById(R.id.class_spinner);

        noti = (ImageView) view.findViewById(R.id.noty);
        exam = (ImageView) view.findViewById(R.id.exam);
        setCount = (ImageView) view.findViewById(R.id.iv_signin);
        setGrade = (ImageView) view.findViewById(R.id.grade);
        export = (ImageView) view.findViewById(R.id.export);
        delete = (ImageView) view.findViewById(R.id.delete);

        initView();

        // 发布通知
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (final_spinner.getSelectedItemId() == data_list.size() - 1) {
                    Toast.makeText(getContext(), "请先选择课堂", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), NotiActivity.class);
                    intent.putExtra("className", final_spinner.getSelectedItem().toString());
                    startActivity(intent);
                }
            }
        });

        //发布作业
        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (final_spinner.getSelectedItemId() == data_list.size() - 1) {
                    Toast.makeText(getContext(), "请先选择课堂", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), ExamActivity.class);
                    intent.putExtra("className", final_spinner.getSelectedItem().toString());
                    startActivity(intent);
                }
            }
        });

        //开启签到
        setCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (final_spinner.getSelectedItemId() == data_list.size() - 1) {
                    Toast.makeText(getContext(), "请先选择课堂", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "签到已开启, 请学生在 10 分钟内进行", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //评分
        setGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (final_spinner.getSelectedItemId() == data_list.size() - 1) {
                    Toast.makeText(getContext(), "请先选择课堂", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "你选择的是" + final_spinner.getSelectedItem().toString() + "课堂", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //数据导出
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getActivity(),ExportActivity.class);
                startActivity(intent);*/
                if (final_spinner.getSelectedItemId() == data_list.size() - 1) {
                    Toast.makeText(getContext(), "请先选择课堂", Toast.LENGTH_SHORT).show();
                } else {
                    String filePath = "/sdcard/download/excel";
                    File file = new File(filePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String fileName = filePath + "/out.xls";
                    String[] titles = new String[]{"姓名", "学号", "test1", "test2", "test3"};
                    String sheetName = final_spinner.getSelectedItem().toString() + "成绩表";
                    ExcelUtil.initExcel(fileName, sheetName, titles);
                    ExcelUtil.exportOneExcel(fileName);
                    Toast.makeText(getContext(),final_spinner.getSelectedItem().toString() + "-成绩已导出", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //班级解散
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (final_spinner.getSelectedItemId() == data_list.size() - 1) {
                    Toast.makeText(getContext(), "请先选择课堂", Toast.LENGTH_SHORT).show();
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("提示");
                    builder.setMessage("是否确定解散课堂？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getContext(), "解散" + final_spinner.getSelectedItem().toString() + "课堂", Toast.LENGTH_SHORT).show();

                            final_spinner.setLayoutMode(Spinner.MODE_DROPDOWN);
                            data_list.remove(final_spinner.getSelectedItem().toString());
                            ((BaseAdapter) final_spinner.getAdapter()).notifyDataSetChanged();
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

        return view;
    }

    private void initView() {
        for (Class c : FakeDataBase.dataMap.keySet()) {
            data_list.add(c.getClassName());
        }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, data_list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                return v;
            }
            @Override
            public int getCount() {
                return super.getCount() - 1; // you don't display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapter.add(getString(R.string.option_click_to_choose));

        final_spinner.setAdapter(adapter);
        final_spinner.setSelection(adapter.getCount());
        final_spinner.setDropDownVerticalOffset(150);
        final_spinner.setPopupBackgroundResource(R.drawable.view_radius);
        final_spinner.setLayoutMode(Spinner.MODE_DROPDOWN);
        final_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != data_list.size() - 1) {
                    Toast.makeText(getContext(), "选择了 " + final_spinner.getSelectedItem().toString() + " 课堂", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                adapter.add(getString(R.string.option_click_to_choose));
                final_spinner.setAdapter(adapter);
                ((BaseAdapter) final_spinner.getAdapter()).notifyDataSetChanged();
            }
        });

    }
}
