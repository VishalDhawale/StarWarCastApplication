package com.example.vrs.starwar.service;


import com.example.vrs.starwar.bean.Film;
import com.example.vrs.starwar.bean.People;
import com.example.vrs.starwar.bean.Planet;
import com.example.vrs.starwar.bean.Specie;
import com.example.vrs.starwar.bean.Starship;
import com.example.vrs.starwar.bean.Vehicle;
import com.example.vrs.starwar.parent.BaseActivity;

public interface StarwarDataService {

    public People getPeopleDetails(String url, BaseActivity mActivity) throws Exception;
    public Film getFilmsDetails(String url, BaseActivity mActivity) throws Exception;
    public Planet getPlanetDetails(String url, BaseActivity mActivity) throws Exception;
    public Specie getSpeciesDetails(String url, BaseActivity mActivity) throws Exception;
    public Vehicle getVehicleDetails(String url, BaseActivity mActivity) throws Exception;
    public Starship getStarshipDetails(String url, BaseActivity mActivity) throws Exception;


}