package com.example.tngu.weatherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tngu.weatherdemo.gson.Weather;
import com.example.tngu.weatherdemo.util.HttpUtil;
import com.example.tngu.weatherdemo.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private ImageView img_Weather;
    private TextView tv_Yq, tv_Fl, tv_Fx, tv_Wd, tv_Time, tv_City;
    private Button btn_Flash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        requestWeather();
        initView();
        btn_Flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestWeather();
                Toast.makeText(WeatherActivity.this,
                        "刷新成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        img_Weather = findViewById(R.id.img_Weather);
        tv_Yq = findViewById(R.id.tv_Yq);
        tv_Fl = findViewById(R.id.tv_Fl);
        tv_Fx = findViewById(R.id.tv_Fx);
        tv_Wd = findViewById(R.id.tv_Wd);
        tv_Time = findViewById(R.id.tv_Time);
        tv_City = findViewById(R.id.tv_City);
        btn_Flash = findViewById(R.id.btn_Flash);
    }

    //网络请求
    public void requestWeather() {

        final String weatherUrl = "http://www.weather.com.cn/data/sk/101020100.html";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取信息失败",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                //log:查询网络请求是否成功
                if (response.isSuccessful()) {
                    Log.d("wr", "successful");
                    Log.d("wr", "response.code()=" + response.code());
                    Log.d("wr", "response.body().string()=" + responseText);
                }
                final Weather weather = Utility.handleWeather(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null) {
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取信息失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void showWeatherInfo(Weather weather) {
        String city_Name = weather.getCityName();
        String weather_Temp = weather.getCityTemp();
        String weather_Time = weather.getWeatherTime();
        String weather_Wind = weather.getCityWind();
        String wind_Scale = weather.getWindScale();
        String city_AP = weather.getCityPressure();
        tv_City.setText(city_Name);
        tv_Wd.setText(weather_Temp);
        tv_Time.setText(weather_Time);
        tv_Fx.setText(weather_Wind);
        tv_Fl.setText(wind_Scale);
        tv_Yq.setText(city_AP);
        img_Weather.setImageResource(R.drawable.clear_day_blue);


    }
}
