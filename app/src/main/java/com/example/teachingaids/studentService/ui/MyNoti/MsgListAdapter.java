package com.example.teachingaids.studentService.ui.MyNoti;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.teachingaids.R;
import com.example.teachingaids.db.Msg;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.media.CamcorderProfile.get;
import static com.example.teachingaids.R.color.design_default_color_background;

public class MsgListAdapter extends ArrayAdapter<Msg> {

    private Context mContext;

    private List<Msg> mMsgList;

    private CardView cardView;
    private TextView msgTitle;
    private TextView className;
    private TextView msgcontent;
    private ImageView read;
    private int recourceId;

    //ViewHolder viewHolder;


    public MsgListAdapter(Context context, int textViewResourceId, List<Msg> objects)
    {
       super(context,textViewResourceId,objects);
        recourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Msg msg = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(recourceId,parent,false);
       if (convertView == null)
       {
           view = LayoutInflater.from(getContext()).inflate(recourceId,parent,false);
        /*   viewHolder = new ViewHolder();
           viewHolder.read = */
       }else {
           view = convertView;
       }
        msgTitle = (TextView) view.findViewById(R.id.stu_msg_title);
        className = view.findViewById(R.id.stu_msg_classtitle);
        msgcontent = view.findViewById(R.id.stu_msg_content);
        read = view.findViewById(R.id.stu_img_msg);
        return view;



    }
}
