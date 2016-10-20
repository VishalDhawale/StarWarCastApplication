package com.example.vrs.starwar.service;

import java.lang.reflect.Type;

import com.example.vrs.starwar.bean.Film;
import com.example.vrs.starwar.bean.People;
import com.example.vrs.starwar.bean.Planet;
import com.example.vrs.starwar.bean.Specie;
import com.example.vrs.starwar.bean.Starship;
import com.example.vrs.starwar.bean.Vehicle;
import com.example.vrs.starwar.parent.BaseActivity;
import com.example.vrs.starwar.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StarwarDataServiceImpl implements StarwarDataService {
    private final Gson gson;
    private HttpUtil utilObj;

    public StarwarDataServiceImpl() {
        utilObj = new HttpUtil();
        gson = new Gson();
    }


    @Override
    public People getPeopleDetails(String url, BaseActivity mActivity) throws Exception {
        String URL =url;
        People response=null;
        String result = utilObj.doGet(URL);
        try {
            Type classType = new TypeToken<People>() {
            }.getType();
              response = gson.fromJson(result, classType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Film getFilmsDetails(String url, BaseActivity mActivity) throws Exception {
        String URL =url;
        Film response=null;
        String result = utilObj.doGet(URL);
        try {
            Type classType = new TypeToken<Film>() {
            }.getType();
            response = gson.fromJson(result, classType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Planet getPlanetDetails(String url, BaseActivity mActivity) throws Exception {
        String URL =url;
        Planet response=null;
        String result = utilObj.doGet(URL);
        try {
            Type classType = new TypeToken<Planet>() {
            }.getType();
            response = gson.fromJson(result, classType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Specie getSpeciesDetails(String url, BaseActivity mActivity) throws Exception {
        String URL =url;
        Specie response=null;
        String result = utilObj.doGet(URL);
        try {
            Type classType = new TypeToken<Specie>() {
            }.getType();
            response = gson.fromJson(result, classType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Vehicle getVehicleDetails(String url, BaseActivity mActivity) throws Exception {
        String URL =url;
        Vehicle response=null;
        String result = utilObj.doGet(URL);
        try {
            Type classType = new TypeToken<Vehicle>() {
            }.getType();
            response = gson.fromJson(result, classType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Starship getStarshipDetails(String url, BaseActivity mActivity) throws Exception {
        String URL =url;
        Starship response=null;
        String result = utilObj.doGet(URL);
        try {
            Type classType = new TypeToken<Starship>() {
            }.getType();
            response = gson.fromJson(result, classType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
