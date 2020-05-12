package com.example.teachingaids.tutorService.ui.Class;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.example.teachingaids.BuildConfig;
import com.example.teachingaids.R;
import com.example.teachingaids.db.Class;
import com.example.teachingaids.db.Stu;
import com.example.teachingaids.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import sdk.TeachApiException;
import sdk.client.ClientConfig;
import sdk.client.DefaultTeachClient;
import sdk.client.TeachClient;
import sdk.param.SignInParam;
import sdk.vo.SubjectVO;


public class ClassFragment extends Fragment {

    public static final int LEVEL_CLASS = 0;

    public static final int LEVEL_STU= 1;

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

    private Button backButton, inviteButton;

    private List<String> dataList = new ArrayList<>();


    private Button add;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tea_class_frag,container,false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        inviteButton = (Button)view.findViewById(R.id.invite_button);
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

        Class c = new Class();
        c.setClassName("微机接口");
        c.setClassContent("16级计科4-6班");
        c.setId(Long.valueOf(3));
        classList.add(c);


        //四名学生，两个课程
        stuList.clear();
        Stu s1 = new Stu();
        s1.setClassId(Long.valueOf(1));
        s1.setStuName("高琦琦");
        s1.setStuId(Long.valueOf(1));
        s1.setTaskgrade(96);
        s1.setSigncount(2);
        s1.setStuPhone("18640402213");
        stuList.add(s1);

        Stu s2 = new Stu();
        s2.setClassId(Long.valueOf(1));
        s2.setStuId(Long.valueOf(2));
        s2.setStuName("金昕昕");
        s2.setSigncount(1);
        s2.setStuPhone("13634152637");
        stuList.add(s2);

        Stu s3 = new Stu();
        s3.setClassId(Long.valueOf(2));
        s3.setStuId(Long.valueOf(3));
        s3.setStuName("王琳琳");
        s3.setSigncount(0);
        s3.setStuPhone("1563472547");
        stuList.add(s3);

        Stu s4 = new Stu();
        s4.setClassId(Long.valueOf(2));
        s4.setStuId(Long.valueOf(4));
        s4.setStuName("宋鹏鹏");
        s4.setStuPhone("13212546273");
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
        queryFromServer();
    }

    private void queryClass(){
        titleText.setText("课堂列表");
        backButton.setVisibility(View.GONE);
        inviteButton.setVisibility(View.GONE);
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
        }
    }

    //根据输入地址从服务器上查数据
    private void queryFromServer() {

        HttpUtil.sendOkHttpRequest("http://192.168.101.6:8080/subject/get/main", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               if (BuildConfig.DEBUG) Log.d("ClassFragment", "e:" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();

                if (BuildConfig.DEBUG) Log.d("ClassFragment", responseText);
            }
        });
    }


    private void queryStu(Class selectedClass){
        backButton.setVisibility(View.VISIBLE);
        inviteButton.setVisibility(View.VISIBLE);
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
        }
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
