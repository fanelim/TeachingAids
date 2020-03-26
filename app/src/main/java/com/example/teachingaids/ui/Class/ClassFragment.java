package com.example.teachingaids.ui.Class;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.example.teachingaids.R;
import com.example.teachingaids.TeaMain;
import com.example.teachingaids.db.Class;
import com.example.teachingaids.db.Stu;

import java.util.ArrayList;
import java.util.List;


public class ClassFragment extends Fragment {

    public static final int LEVEL_CLASS = 0;

    public static final int LEVEL_STU= 1;

    private ProgressDialog progressDialog;


    private ListView listView;

    private android.widget.ArrayAdapter<String > adapter;

    private TextView titleText;

    private static List<Class> classList = new ArrayList<>();

    private static List<Stu> stuList  = new ArrayList<>();

    private static List<Stu> selectedstuList = new ArrayList<>();

    private Class selectedClass;

    private Stu selectedStu;

    private int currentLevel;

    private Long id = Long.valueOf(3);

    private Button backButton;

    private List<String> dataList = new ArrayList<>();


    private Button add;


/*
    *//**
     4      * 静态工厂方法需要一个str型的值来初始化fragment的参数，
     5      * 然后返回新的fragment到调用者
     6      */
   /* public static ClassFragment newInstance(String title, String content) {
        ClassFragment f = new ClassFragment();
        Bundle args = new Bundle();
        args.putString("class_name", title);
        args.putString("class_content", content);
        f.setArguments(args);
        return f;
    }*/


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.class_frag,container,false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        init();

        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new android.widget.ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,dataList);
        add = (Button)view.findViewById(R.id.bt_addclass);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //之后选择跳转
                Intent intent=new Intent(getActivity(),AddClassActivity.class);
                startActivityForResult(intent,2);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
        return view;
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

                }else if (currentLevel ==  LEVEL_STU){
                    selectedStu = selectedstuList.get(position);
                    for (Stu stu :stuList){
                        if (stu.getStuName() == selectedStu.getStuName()){

                            String stuName = stu.getStuName().toString().trim();
                            String  stuId = stu.getStuId().toString().trim();
                            String stuPhone = stu.getStuPhone().toString().trim();
                            String stucount = String.valueOf(stu.getSigncount());
                            String stugrade = String.valueOf(stu.getTaskgrade());

                            Intent intent = new Intent(getActivity(),StudentActivity.class);
                            //这是学生队列中的个人信息
                            intent.putExtra("stu_phone",stuPhone);
                            intent.putExtra("stu_name",stuName);
                            intent.putExtra("stu_id",stuId);
                            intent.putExtra("stu_count",stucount);
                            intent.putExtra("stu_grade",stugrade);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
/*

                    stuList.get(position).getStuName() =selectedStu.getStuName();
                    String stuName = stuList.get(position).getStuName();
                    String  stuId = stuList.get(position).getStuId().toString();
                    String stuPhone = stuList.get(position).getStuPhone();
                    String stucount = String.valueOf(stuList.get(position).getSigncount());
                    String stugrade = String.valueOf(stuList.get(position).getTaskgrade());
*/
/*
                    Intent intent = new Intent(getActivity(),StudentActivity.class);
                    //这是学生队列中的个人信息
                    intent.putExtra("stu_phone",stuPhone);
                    intent.putExtra("stu_name",stuName);
                    intent.putExtra("stu_id",stuId);
                    intent.putExtra("stu_count",stucount);
                    intent.putExtra("stu_grade",stugrade);
                    startActivity(intent);
                    getActivity().finish();*/

                }
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
        titleText.setText(" ");
        backButton.setVisibility(View.GONE);
   //     classList = DataSupport.findAll(Class.class);
        Bundle bundle= ClassFragment.this.getArguments();
        if(bundle !=null){
            Class aClass = new Class();
            String cname =bundle.getString("class_name");
            String ccontent = bundle.getString("class_content");
            aClass.setClassName(cname);
            aClass.setClassContent(ccontent);
            aClass.setId(id);
            id++;
            classList.add(0, aClass);
        }
        if (classList.size() > 0){
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

    public void refresh(String classTitle, String classContent) {
        Class aclass = new Class();
        aclass.setClassName(classTitle);
        aclass.setClassContent(classContent);
        aclass.setId(id);
        id++;
        classList.add(aclass);

        if (classList.size() > 0) {
            dataList.clear();

            for (Class classes : classList) {
                dataList.add(classes.getClassName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CLASS;

        }
    }
}
