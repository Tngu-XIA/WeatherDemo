package com.example.tngu.weatherdemo.util;

import android.util.Log;


import com.example.tngu.weatherdemo.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    //利用GSON来解析json数据
    public static Weather handleWeather(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            String jsonString = jsonObject.getString("weatherinfo");
            Gson gson = new Gson();
            Weather weather = gson.fromJson(jsonString,Weather.class);
            //查询数据解析是否正确
            Log.d("w","city="+weather.getCityName());
            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
