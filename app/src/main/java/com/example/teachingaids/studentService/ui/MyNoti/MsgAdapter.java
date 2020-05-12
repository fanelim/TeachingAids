package com.example.teachingaids.studentService.ui.MyNoti;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teachingaids.R;
import com.example.teachingaids.db.Msg;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.media.CamcorderProfile.get;
import static com.example.teachingaids.R.color.design_default_color_background;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private Context mContext;

    private List<Msg> mMsgList;

    private boolean isread = false;


    public MsgAdapter(Context context, List<Msg> mMsgList)
    {
        this.mContext = context;
        this.mMsgList = mMsgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (mContext != null)
        {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_my_msg,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
        {

            notifyItemChanged(holder.getAdapterPosition());
                int position = holder.getAdapterPosition();
                Msg msg = mMsgList.get(position);
                isread = true;
                Intent intent = new Intent(mContext, MsgActivity.class);
                intent.putExtra(MsgActivity.MSG_TITLE, msg.getTitle());
                intent.putExtra(MsgActivity.MSG_CLASSNAME, msg.getClassName());
                intent.putExtra(MsgActivity.MSG_CONTENR, msg.getContent());
                mContext.startActivity(intent);
                ////延时设置cardview颜色


        }

        });
        if (isread == true){
        holder.cardView.setBackgroundColor(Color.parseColor("#FFEFEFF4"));
        }
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        Msg msg = mMsgList.get(position);
        holder.msgTitle.setText(msg.getTitle());
        holder.msgcontent.setText(msg.getContent());
        holder.className.setText(msg.getClassName());

    }

    @Override
    public int getItemCount()
    {
        return mMsgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView msgTitle;
        TextView className;
        TextView msgcontent;
        ImageView read;

        public ViewHolder(View itemView)
        {
            super(itemView);
            cardView = (CardView) itemView;
            msgTitle = (TextView) itemView.findViewById(R.id.stu_msg_title);
            className = itemView.findViewById(R.id.stu_msg_classtitle);
            msgcontent = itemView.findViewById(R.id.stu_msg_content);
            read = itemView.findViewById(R.id.stu_img_msg);
        }

    }

}
