package com.example.teachingaids.studentService.ui.MyClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.teachingaids.R;
import com.example.teachingaids.db.Class;
import com.example.teachingaids.db.Stu;
import com.example.teachingaids.tutorService.ui.Class.ClassFragment;
import com.example.teachingaids.tutorService.ui.Class.StudentActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import static com.example.teachingaids.tutorService.ui.Class.ClassFragment.LEVEL_CLASS;
import static com.example.teachingaids.tutorService.ui.Class.ClassFragment.LEVEL_STU;


public class MyClassFragment extends Fragment {

    private ImageView home,join;

    private static List<Class> classList = new ArrayList<>();

    private static List<Stu> stuList  = new ArrayList<>();

    private List<String> dataList = new ArrayList<>();

    private static List<Stu> selectedstuList = new ArrayList<>();

    private ListView listView;

    private android.widget.ArrayAdapter<String > adapter;

    private Class selectedClass;

    private Stu selectedStu;

    private int currentLevel;

    private Button backButton;

    private TextView titleText;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.stu_class_frag, container, false);
        home=root.findViewById(R.id.iv_myclassinfo);
        join = root.findViewById(R.id.iv_myaddclass);
        titleText = (TextView) root.findViewById(R.id.stu_title_text);
        backButton = (Button) root.findViewById(R.id.stu_back_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(),StuActivity.class);
                startActivity(intent);
            }
        });
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "添加班级", Toast.LENGTH_SHORT).show();
            }
        });

        listView = root.findViewById(R.id.stu_class_listview);
        adapter = new android.widget.ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);

        init();
        return root;
    }

    private void init() {
        classList.clear();
        Class a = new Class();
        a.setClassName("高等数学");
        a.setClassContent("是大一计院的课程");
        a.setId(Long.valueOf(1));
        classList.add(a);

        Class b = new Class();
        b.setClassName("线性代数");
        b.setClassContent("是大二计院的课程");
        b.setId(Long.valueOf(2));
        classList.add(b);

        Class b1 = new Class();
        b1.setClassName("大学英语");
        b1.setClassContent("是大二计院的课程");
        b1.setId(Long.valueOf(2));
        classList.add(b1);


        //四名学生，两个课程
        stuList.clear();
        Stu s1 = new Stu();
        s1.setClassId(Long.valueOf(1));
        s1.setStuName("高1");
        s1.setStuId(Long.valueOf(1));
        s1.setTaskgrade(96);
        s1.setSigncount(2);
        s1.setStuPhone("1111");
        stuList.add(s1);

        Stu s2 = new Stu();
        s2.setClassId(Long.valueOf(2));
        s2.setStuId(Long.valueOf(2));
        s2.setStuName("线1");
        s2.setSigncount(1);
        s2.setStuPhone("2222");
        stuList.add(s2);

        Stu s3 = new Stu();
        s3.setClassId(Long.valueOf(2));
        s3.setStuId(Long.valueOf(3));
        s3.setStuName("线2");
        s3.setSigncount(0);
        s3.setStuPhone("3333");
        stuList.add(s3);

        Stu s4 = new Stu();
        s4.setClassId(Long.valueOf(2));
        s4.setStuId(Long.valueOf(4));
        s4.setStuName("线3");
        s4.setStuPhone("4444");
        s4.setSigncount(0);
        stuList.add(s4);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_CLASS){
                    selectedClass = classList.get(position);
                    selectedstuList.clear();
                    queryStu(selectedClass);

                }/*else if (currentLevel ==  LEVEL_STU){
                    selectedStu = selectedstuList.get(position);
                    for (Stu stu :stuList){
                        if (stu.getStuName() == selectedStu.getStuName()){

                            String stuName = stu.getStuName().toString().trim();
                            String  stuId = stu.getStuId().toString().trim();
                            String stuPhone = stu.getStuPhone().toString().trim();
                            String stucount = String.valueOf(stu.getSigncount());
                            String stugrade = String.valueOf(stu.getTaskgrade());

                        }
                    }

                }*/
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_STU) {
                    queryClass();
                }
            }
        });
        queryClass();
    }

    private void queryClass(){
        titleText.setText("班级列表 ");
        backButton.setVisibility(View.GONE);
        //     classList = DataSupport.findAll(Class.class);
        /*Bundle bundle= MyClassFragment.this.getArguments();
        if(bundle !=null){
            Class aClass = new Class();
            String cname =bundle.getString("class_name");
            String ccontent = bundle.getString("class_content");
            aClass.setClassName(cname);
            aClass.setClassContent(ccontent);
            aClass.setId(id);
            id++;
            classList.add(0, aClass);
        }*/
        if (classList.size() >= 0){
            dataList.clear();
            for (Class classes :classList){
                dataList.add(classes.getClassName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CLASS;
        }/*else {
            String address = "http://guolin.tech/api/china";
            queryFromServer(address,"province");
        }*/
    }

    private void queryStu(Class selectedClass){
        backButton.setVisibility(View.VISIBLE);
        // stuList = DataSupport.where("classid = ?",String.valueOf(selectedStu.getClassId())).find(Stu.class);
        if (stuList.size() > 0){
            dataList.clear();
            for (Stu stu:stuList){
                if(stu.getClassId() == selectedClass.getId()) {
                    dataList.add(stu.getStuName());
                    selectedstuList.add(stu);
                    titleText.setText(selectedClass.getClassName()+" 有" + dataList.size() +"人");
                }
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_STU;
        }/*else {
            int provinceCode = selectedProvince.getProvinceCode();
            int cityCode = selectedCity.getCityCode();
            String address = "http://guolin.tech/api/china/" +provinceCode + "/" + cityCode;
            queryFromServer(address,"county");
        }*/
    }

}
