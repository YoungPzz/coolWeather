package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

//解析和处理服务器返回的json数据 存入数据库
public class Utility {
    public static boolean handleProvinceResponse(String response) throws JSONException {
        if(!TextUtils.isEmpty(response)){
            JSONArray allProvinces = new JSONArray(response);
            for(int i = 0; i < allProvinces.length(); i++){
                JSONObject provinceObject = allProvinces.getJSONObject((i));
                Province province = new Province();
                province.setProvinceName(provinceObject.getString("name"));
                province.setProvinceCode(provinceObject.getInt("id"));
                province.save();
            }
            return true;
        }
        return false;
    }
    public static boolean handleCountyResponse(String response, int cityId) throws JSONException , IOException {
        if(!TextUtils.isEmpty(response)){
            JSONArray allCounties = new JSONArray(response);
            for(int i = 0; i < allCounties.length(); i++){
                JSONObject countyObject = allCounties.getJSONObject((i));
                County county = new County();
                county.setCountyName(countyObject.getString("name"));
                county.setWeatherId(countyObject.getString("weather_id"));
                county.setCityId(cityId);
                county.save();
            }
            return true;
        }
        return false;
    }
    public static boolean handleCityResponse(String response, int provinceId) throws JSONException {
        if(!TextUtils.isEmpty(response)){
            JSONArray allCities = new JSONArray(response);
            for(int i = 0; i < allCities.length(); i++){
                JSONObject cityObject = allCities.getJSONObject((i));
                City city = new City();
                city.setCityName(cityObject.getString("name"));
                city.setCityCode(cityObject.getInt("id"));
                city.setProvinceId(provinceId);
                city.save();
            }
            return true;
        }
        return false;
    }
}
