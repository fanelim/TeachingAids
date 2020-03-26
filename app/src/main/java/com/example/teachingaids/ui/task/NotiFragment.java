package com.example.teachingaids.ui.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachingaids.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class NotiFragment extends Fragment implements NotiAdapter.OnItemClickListener{

    private RecyclerView mrecyclerView;

    private FloatingActionButton fab;

    public static NotiAdapter notiAdapter;

    public static List<NotiCard> notiCardList = new ArrayList<>();

    private boolean isDelete = false;

    private List<NotiCard> deleteCardList;



    private ImageView imreturn;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        // 卡片式布局设置
        mrecyclerView = view.findViewById(R.id.note_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mrecyclerView.setLayoutManager(layoutManager);


        // 判断是否第一次登陆及初始化列表
        isFirstLogin();
        readDatabaseAndSetList();
        // 设置监听器
        notiAdapter.setOnItemClickListener(this);
        //悬浮按钮设置
        fab = view.findViewById(R.id.note_flb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDelete) {
                    // 删除操作
                    deleteCardList = notiAdapter.getDeleteList();
                    for (NotiCard a : deleteCardList) {
                        long id = a.getId();
                        LitePal.delete(NotiCard.class, id);
                        notiCardList.remove(a);
                    }
                    notiAdapter.setIsEdit(false); // 退出编辑状态
                    notiAdapter.notifyDataSetChanged();
                    // 悬浮按钮变回原状
                    fab.setImageResource(R.drawable.ic_brush);
                    isDelete = false;
                } else {
                    Intent intent = new Intent(getActivity(), AddNotiActivity.class);
                    startActivityForResult(intent, 1);   //请求码只要是一个唯一值即可
                }
            }
        });

return view;
    }

    private void returnFragment(TaskFragment taskFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.task_fragment,taskFragment);
        transaction.commit();
    }


    // 判断是否是第一次使用，显示note操作指南
    private void isFirstLogin(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        boolean first = sharedPreferences.getBoolean("isfirst",true);
        if (first){
            // 设置时间格式及获取当前时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());

            NotiCard notiCard = new NotiCard();
            notiCard.setPosition(1);
            notiCard.setTitle("***Note使用指南***");
            notiCard.setContent("1、点击悬浮按钮，新建Note\n2、点触Note即可编辑\n3、Note支持长按批量删除操作\n");
            notiCard.setTime(simpleDateFormat.format(date));
            notiCard.save();
            //修改first数据
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isfirst",false);
            editor.apply();
        }
    }

    //初始化列表
    private void readDatabaseAndSetList(){
        notiCardList = LitePal.order("position desc").find(NotiCard.class);
        notiAdapter = new NotiAdapter(notiCardList);
        mrecyclerView.setAdapter(notiAdapter);
     //   Log.d("my", "初始化列表1 "+System.identityHashCode(notiAdapter.mNoteCardList));
    //   Log.d("my", "初始化列表2 "+System.identityHashCode(noteCardList));
    }

    // startActivityForResult的反馈
    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String title = data.getStringExtra("title");
                    String content = data.getStringExtra("content");
                    // 获取最大位置
                    int position;
                    if(LitePal.findAll(NotiCard.class).size() != 0) // 列表不为空
                        position = LitePal.order("position desc").findFirst(NotiCard.class).getPosition() + 1;
                    else
                        position = 1;
                    NotiCard notiCard = new NotiCard();
                    notiCard.setPosition(position);
                    notiCard.setTitle(title);
                    notiCard.setContent(content);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date(System.currentTimeMillis());
                    notiCard.setTime(simpleDateFormat.format(date));
                    notiCard.save();
                    notiCardList.add(0, notiCard);   //将新增的notecard添加在列表的第一个
                    notiAdapter.notifyDataSetChanged();
//                    Log.d("my", "新建 "+System.identityHashCode(noteCardList));

                }
                break;
            default:
        }
    }

    // 当编辑时设置NoteFragment相关的界面
    @Override
    public void setNoteFragment(){
        fab.setImageResource(R.drawable.ic_trash);
        isDelete = true;
    }



}
