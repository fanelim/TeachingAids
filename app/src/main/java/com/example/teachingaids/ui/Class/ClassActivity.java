package com.example.teachingaids.ui.Class;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingaids.R;
import com.google.android.material.appbar.AppBarLayout;

public class ClassActivity extends AppCompatActivity {



    public static void actionStart(Context context, String classTitle, String classContent){
        Intent intent = new Intent(context, AddClassActivity.class);
        intent.putExtra("class_title",classTitle);
        intent.putExtra("class_content",classContent);
        context.startActivity(intent);
        return;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_frag_class);

    }

}
