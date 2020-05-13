package com.example.teachingaids.tutorService.ui.Class;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teachingaids.R;
import com.example.teachingaids.db.Class;
import com.example.teachingaids.db.Stu;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.teachingaids.db.FakeDataBase.dataMap;


public class ClassFragment extends Fragment {

    public static final int LEVEL_CLASS = 0;
    public static final int LEVEL_STU = 1;

    private TextView titleText;
    private Button backButton;
    private Button addClass;
    private Button inviteButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();
    private List<Class> classList = new ArrayList<>();
    private List<Stu> stuList = new ArrayList<>();

    private int currentLevel;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tea_class_frag, container,false);

        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        inviteButton = view.findViewById(R.id.invite_button);
        addClass = (Button)view.findViewById(R.id.bt_addclass);
        listView = (ListView) view.findViewById(R.id.list_view);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_STU) {
                    queryClass();
                }
            }
        });

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //之后选择跳转
                Intent intent = new Intent(getActivity(), AddClassActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        adapter = new android.widget.ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_CLASS){
                    Class selectedClass = classList.get(position);
                    queryStu(selectedClass);
                } else if (currentLevel ==  LEVEL_STU){
                    Stu selectedStu = stuList.get(position);
                    Intent intent = new Intent(getContext(), StudentActivity.class);
                    intent.putExtra("stuId", selectedStu.getStuId());
                    startActivity(intent);
                }
            }
        });

        queryClass();
    }


    private void queryClass(){
        titleText.setText("课堂列表");
        backButton.setVisibility(View.GONE);
        inviteButton.setVisibility(View.GONE);

        dataList.clear();
        classList.clear();
        for (Class c : dataMap.keySet()) {
            classList.add(c);
            dataList.add(c.getClassName());
        }

        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        currentLevel = LEVEL_CLASS;
    }

    private void queryStu(Class selectedClass){
        stuList = dataMap.get(selectedClass);

        titleText.setText(selectedClass.getClassName() + "有" + stuList.size() + "人");
        backButton.setVisibility(View.VISIBLE);
        inviteButton.setVisibility(View.VISIBLE);

        dataList.clear();
        for (Stu stu : stuList) {
            dataList.add(stu.getStuName());
        }

        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        currentLevel = LEVEL_STU;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String className = data.getStringExtra("className");
                    String classContent = data.getStringExtra("classContent");
                    Class newClass = new Class();
                    newClass.setClassName(className);
                    newClass.setClassContent(classContent);
                    dataMap.put(newClass, new ArrayList<>());
                    queryClass();
                }
                break;
        }
    }
}
