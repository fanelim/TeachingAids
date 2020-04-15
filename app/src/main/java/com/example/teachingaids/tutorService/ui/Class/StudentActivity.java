package com.example.teachingaids.tutorService.ui.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.teachingaids.R;
import com.example.teachingaids.tutorService.TeaMain;

public class StudentActivity extends AppCompatActivity {

    private ScrollView studentLayout;

    private TextView stuName,stuPhone,stuId,stuGrade,stuCount;

    private ImageView viewreturn;


    SwipeRefreshLayout swipeRefresh;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.tea_activity_student);
        studentLayout = (ScrollView) findViewById(R.id.student_layout);
        stuName = (TextView) findViewById(R.id.show_name);
        stuPhone = (TextView) findViewById(R.id.show_phone);
        stuId = (TextView) findViewById(R.id.show_stuid);
        stuCount = (TextView) findViewById(R.id.show_signcount);
        stuGrade = (TextView) findViewById(R.id.show_grade);

        viewreturn = findViewById(R.id.iv_stureturn);
        viewreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, TeaMain.class);
                startActivity(intent);
                finish();
            }
        });

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_stu_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String infoString = prefs.getString("Info", null);

        //用util解析
       /* final String stuId;

        if (infoString != null) {
            Info info = Utility.handleWeatherResponse(infoString);
            stuId = info.basic.weatherId;
            showInfo(info);
        } else {
            stuId = getIntent().getStringExtra("info_id");
            studentLayout.setVisibility(View.INVISIBLE);
            requestWeather(stuId);
        }*/
            //这里以学生学号为数据进行传递,后期要改
        final String stuId = getIntent().getStringExtra("stu_id");
       // studentLayout.setVisibility(View.INVISIBLE);
        requestInfo(stuId);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestInfo(stuId);
            }
        });

    }

    public void requestInfo(final String  stuid){
        stuPhone.setText(getIntent().getStringExtra("stu_phone"));
        stuName.setText(getIntent().getStringExtra("stu_name"));
        stuId.setText("2016301200001");
        stuCount.setText(getIntent().getStringExtra("stu_count"));
        stuGrade.setText(getIntent().getStringExtra("stu_grade"));

    }

    /*public void requestWeather(final String weatherId) {

        stuPhone.setText(getIntent().getStringExtra("stu_phone"));
        stuName.setText(getIntent().getStringExtra("stu_name"));
        stuId.setText(getIntent().getStringExtra("stu_id"));


       *//* String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=bc0418b57b2d4918819d3974ac1285d9";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();*//*
    }*/
 /*   private void loadBingPic(){
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic",bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }*/

/*
    private void showInfo(Info info) {

        if (info != null && "ok".equals(info.status)) {
            String cityName = info.basic.cityName;
            String updateTime = info.basic.update.updateTime.split(" ")[1];
            String degree = info.now.temperature + "℃";
            String weatherInfo = info.now.more.info;

            stuName.setText(cityName);

            for (Forecast forecast : info.forecastList) {
                View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
                TextView dateText = (TextView) view.findViewById(R.id.date_text);
                TextView infoText = (TextView) view.findViewById(R.id.info_text);
                TextView maxText = (TextView) view.findViewById(R.id.max_text);
                TextView minText = (TextView) view.findViewById(R.id.min_text);
                dateText.setText(forecast.date);
                infoText.setText(forecast.more.info);
                maxText.setText(forecast.temperature.max);
                minText.setText(forecast.temperature.min);
                forecastLayout.addView(view);
            }
            if (info.aqi != null) {
                aqiText.setText(info.aqi.city.aqi);
                pm25Text.setText(info.aqi.city.pm25);
            }

            String comfort = "舒适度" + info.suggestion.comfort.info;
            String carWash = "洗车指数" + info.suggestion.carWash.info;
            String sport = "运动建议" + info.suggestion.sport.info;
            comfortText.setText(comfort);
            carWashText.setText(carWash);
            sportText.setText(sport);
            studentLayout.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, AutoUpdateService.class);
            startService(intent);
        }else {
            Toast.makeText(WeatherActivity.this,"展示天气信息失败",Toast.LENGTH_SHORT).show();
        }
    }*/
}
