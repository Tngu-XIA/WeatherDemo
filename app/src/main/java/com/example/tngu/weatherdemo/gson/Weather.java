package com.example.tngu.weatherdemo.gson;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("city")
    private String cityName;

    @SerializedName("temp")
    private String cityTemp;

    @SerializedName("WD")
    private String cityWind;

    @SerializedName("WS")
    private String windScale;

    @SerializedName("time")
    private String weatherTime;

    @SerializedName("AP")
    private String cityPressure;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityTemp() {
        return cityTemp;
    }

    public void setCityTemp(String cityTemp) {
        this.cityTemp = cityTemp;
    }

    public String getCityWind() {
        return cityWind;
    }

    public void setCityWind(String cityWind) {
        this.cityWind = cityWind;
    }

    public String getWindScale() {
        return windScale;
    }

    public void setWindScale(String windScale) {
        this.windScale = windScale;
    }

    public String getWeatherTime() {
        return weatherTime;
    }

    public void setWeatherTime(String weatherTime) {
        this.weatherTime = weatherTime;
    }

    public String getCityPressure() {
        return cityPressure;
    }

    public void setCityPressure(String cityPressure) {
        this.cityPressure = cityPressure;
    }
}
