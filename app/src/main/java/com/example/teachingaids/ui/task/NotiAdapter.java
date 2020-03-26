package com.example.teachingaids.ui.task;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachingaids.R;

import java.util.ArrayList;
import java.util.List;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.ViewHolder> {
    private Context mContext;
    private List<NotiCard> mNotiCardList;
    private List<NotiCard> mDeleteList = new ArrayList<>();
    private boolean isEdit = false;
    private OnItemClickListener mOnItemClickListener;

    public NotiAdapter(List<NotiCard> notiCardList){
        mNotiCardList = notiCardList;
    }

    public void addAll(List<NotiCard> notiCardList){
        mNotiCardList.clear();
        mNotiCardList.addAll(notiCardList);
        notifyDataSetChanged();
    }

    public void setIsEdit(boolean misEdit){
        isEdit = misEdit;
    }

    public List<NotiCard> getDeleteList(){
        if (mDeleteList == null) {
            mDeleteList = new ArrayList<>();
        }
        return mDeleteList;
    }

    // 设置接口
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    // 接口定义
    public interface OnItemClickListener {
        void setNoteFragment();
    }

    // ViewHolder定义
    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView mcardview;
        TextView note_title;
        TextView note_content;
        TextView note_time;
        ImageView note_right;
        boolean isSelected;

        public ViewHolder(View view) {
            super(view);
            mcardview = view.findViewById(R.id.note_card);
            note_title = view.findViewById(R.id.note_title);
            note_content = view.findViewById(R.id.note_content);
            note_time = view.findViewById(R.id.note_time);
            note_right = view.findViewById(R.id.note_right);
            isSelected = false;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_note, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        final NotiCard notiCard = mNotiCardList.get(position);
        final int mposition = position;
        String title = notiCard.getTitle();
        String content = notiCard.getContent();
        String time = notiCard.getTime();

        // 判断标题字符串的宽度
        Rect bounds = new Rect();
        TextPaint textPaint = holder.note_title.getPaint();
        int i,temp_width;
        for (i=0;i<title.length()-1;i++)
        {
            textPaint.getTextBounds(title,0,i,bounds);
            temp_width = bounds.width();
            Log.d("my", "onBindViewHolder: " + temp_width);
            if (temp_width > 850 || title.charAt(i) == '\n') // 1000值需改
            {
                title = title.substring(0,i) + "...";
                break;
            }
        }
        holder.note_title.setText(title);
        // 判断内容的宽度
        textPaint = holder.note_content.getPaint();
        for (i=0;i<content.length()-1;i++)
        {
            textPaint.getTextBounds(content,0,i,bounds);
            temp_width = bounds.width();
            Log.d("my", "onBindViewHolder: " + temp_width);
            if (temp_width > 800 || content.charAt(i) == '\n') // 960值需改
            {
                content = content.substring(0,i) + "...";
                break;
            }
        }
        holder.note_content.setText(content);
        holder.note_time.setText(time);

        // 给ViewHolder的卡片设置监听动作
        holder.mcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将卡片的标题和内容传递过去
                //将单击NoteCard的信息传给EditNoteActivity
                Intent intent = new Intent(mContext, AddNotiActivity.class);
                intent.putExtra("id", notiCard.getId());
                intent.putExtra("title", notiCard.getTitle());
                intent.putExtra("content", notiCard.getContent());
                intent.putExtra("position_in_the_list",mposition);   // 将位置信息传送过去，以便删除原先列表中的notecard
                mContext.startActivity(intent);
            }
        });

        // 查看是否处于编辑状态
        if(isEdit) {
            holder.isSelected = false; // 默认是全部没选中
            holder.note_right.setImageResource(R.drawable.ic_no_right);
            holder.note_right.setVisibility(View.VISIBLE);
            holder.mcardview.setEnabled(false); // 编辑状态不能修改信息
        }
        else {
            holder.note_right.setVisibility(View.GONE);
            holder.mcardview.setEnabled(true);
        }

        // 给cradview设置长按动作监听
        holder.mcardview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isEdit = true;
                notifyDataSetChanged();
                mOnItemClickListener.setNoteFragment();
                // notifyDataSetChanged(); // 提示数据发生了变化，需要刷新
                // false表示长按之后允许触发短按事件
                return true;
            }
        });

        // 对选中设置按钮
        holder.note_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.isSelected){
                    holder.note_right.setImageResource(R.drawable.ic_no_right);
                    holder.isSelected = false;
                    mDeleteList.remove(notiCard);
                }
                else {
                    holder.note_right.setImageResource(R.drawable.ic_right);
                    holder.isSelected = true;
                    mDeleteList.add(notiCard);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return mNotiCardList.size();
    }

}

