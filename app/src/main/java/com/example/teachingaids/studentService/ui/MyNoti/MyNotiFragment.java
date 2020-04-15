package com.example.teachingaids.studentService.ui.MyNoti;

import android.graphics.Color;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.example.teachingaids.R;
import com.example.teachingaids.db.Class;
import com.example.teachingaids.db.Exam;
import com.example.teachingaids.db.Msg;
import com.example.teachingaids.db.Stu;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class MyNotiFragment extends Fragment {


    private Spinner final_spinner;

    private List<String> msg_data_list = new ArrayList<>();

    private List<String> class_data_list = new ArrayList<>();

    private MsgAdapter msg_adapter;

    private TextView title;

    private List<Msg> msgList = new ArrayList<>();

    private List<Class> classList = new ArrayList<>();

    private static List<Msg> selectedmsgList = new ArrayList<>();


    private ArrayAdapter<String> class_adapter;

    private RecyclerView recyclerView;

    private Boolean read = false;

    // 方案列表


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.stu_noti_frag, container, false);
        final_spinner = (Spinner) root.findViewById(R.id.stu_noti_spinner);
        initClass();
        initMsg();

        selectedmsgList.clear();

     //   MsgListAdapter adapter = new MsgListAdapter(getContext(),R.layout.list_item_my_msg,selectedmsgList);


        msg_adapter = new MsgAdapter(getContext(), selectedmsgList);
        recyclerView = (RecyclerView) root.findViewById(R.id.stu_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(msg_adapter);

        return root;
    }

    private void initMsg() {

        Msg m1 = new Msg();
        m1.setClassName("高等数学");
        m1.setTitle("关于上课时间");
        m1.setContent("这周不上课");
        m1.setImageId(R.drawable.ic_unreadmsg);
        m1.setId(1);
        msgList.add(m1);

        Msg m12 = new Msg();
        m12.setClassName("大学英语");
        m12.setTitle("关于上课时间");
        m12.setContent("这周四上两个小时");
        m12.setImageId(R.drawable.ic_unreadmsg);
        m12.setId(1);
        msgList.add(m12);

        Msg m123 = new Msg();
        m123.setClassName("大学英语");
        m123.setTitle("关于考试内容");
        m123.setContent("大一整本书");
        m123.setImageId(R.drawable.ic_unreadmsg);
        m123.setId(1);
        msgList.add(m123);
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

        Class a4 = new Class();
        a4.setClassName("线性代数");
        a4.setId(Long.valueOf(4));
        classList.add(a4);


        class_data_list.clear();
        for (Class a : classList) {
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
                return super.getCount() - 1; // you don't display last item. It is used as hint.
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
                if (position != class_data_list.size() - 1) {
                    Toast.makeText(getContext(), "选择了 " + final_spinner.getSelectedItem().toString() + " 班级", Toast.LENGTH_SHORT).show();
                    msg_data_list.clear();
                    selectedmsgList.clear();
                    for (Class c : classList) {
                        for (Msg msg : msgList) {
                            if (c.getClassName() == final_spinner.getSelectedItem().toString()) {
                                if (c.getClassName() == msg.getClassName())
                                    //    msg_data_list.add("题目： " + exam.getExamTitle() + " 分值: " + exam.getExamScore());
                                    selectedmsgList.add(0,msg);
                            }
                        }
                    }
                    //     recyclerViews.setSelection(0);
                    msg_adapter.notifyDataSetChanged();
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
