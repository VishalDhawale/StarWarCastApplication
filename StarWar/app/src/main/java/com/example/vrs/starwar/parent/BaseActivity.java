package com.example.vrs.starwar.parent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vrs.starwar.bean.Film;
import com.example.vrs.starwar.bean.People;
import com.example.vrs.starwar.bean.Planet;
import com.example.vrs.starwar.bean.Specie;
import com.example.vrs.starwar.bean.Starship;
import com.example.vrs.starwar.bean.Vehicle;
import com.example.vrs.starwar.customs.ActorDetailsDialog;
import com.example.vrs.starwar.customs.FilmDetailsDialog;
import com.example.vrs.starwar.customs.PlanetDetailsDialog;
import com.example.vrs.starwar.customs.ProgressAlertDialog;
import com.example.vrs.starwar.customs.SpeciesrDetailsDialog;
import com.example.vrs.starwar.customs.StarshipDetailsDialog;
import com.example.vrs.starwar.customs.VehicleDetailsDialog;
import com.example.vrs.starwar.util.APPConstant;


public class BaseActivity extends AppCompatActivity {

    protected BaseActivity mActivity;
    private StarwarApplication mApp;
    private ProgressAlertDialog progressDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        initView();
        initMember();
        initData();
        initListner();
    }

    private void initMember() {

        mActivity = BaseActivity.this;
        mApp = (StarwarApplication) getApplication();
    }

    private void initListner() {

    }

    private void initData() {

    }

    private void initView() {

    }

    public void showSignatureDialog(String title) {


    }

    public void showProgress(Activity mActivity, String message) {
        progressDialog1 = new ProgressAlertDialog(mActivity, message);
        progressDialog1.setTitle("Processing");
        progressDialog1.setCancelable(false);
        progressDialog1.show();
    }

    public void dismissProgress() {
        if (progressDialog1 != null && progressDialog1.isShowing()) {
            progressDialog1.dismiss();
        }
    }

    public void showActorDialog(People url) {
        if (!APPConstant.isDiaolgShownLevel) {

            APPConstant.isDiaolgShownLevel = true;
            ActorDetailsDialog im = new ActorDetailsDialog();
            im.setDetails(url);
            im.show(mActivity.getFragmentManager(),
                    "Image Preview");
        }
    }

    public void showVehicleDialog(Vehicle url) {
        if (!APPConstant.isDiaolgShownLevel) {

            APPConstant.isDiaolgShownLevel = true;
            VehicleDetailsDialog im = new VehicleDetailsDialog();
            im.setDetails(url);
            im.show(mActivity.getFragmentManager(),
                    "Image Preview");
        }
    }

    public void showFilmsDialog(Film url) {
        if (!APPConstant.isDiaolgShownLevel) {

            APPConstant.isDiaolgShownLevel = true;
            FilmDetailsDialog im = new FilmDetailsDialog();
            im.setDetails(url);
            im.show(mActivity.getFragmentManager(),
                    "Image Preview");
        }
    }

    public void showSpeciesDialog(Specie url) {
        if (!APPConstant.isDiaolgShownLevel) {

            APPConstant.isDiaolgShownLevel = true;
            SpeciesrDetailsDialog im = new SpeciesrDetailsDialog();
            im.setDetails(url);
            im.show(mActivity.getFragmentManager(),
                    "Image Preview");
        }
    }

    public void showStarshipsDialog(Starship url) {
        if (!APPConstant.isDiaolgShownLevel) {

            APPConstant.isDiaolgShownLevel = true;
            StarshipDetailsDialog im = new StarshipDetailsDialog();
            im.setDetails(url);
            im.show(mActivity.getFragmentManager(),
                    "Image Preview");
        }
    }

    public void showPlanetDialog(Planet url) {
        if (!APPConstant.isDiaolgShownLevel) {

            APPConstant.isDiaolgShownLevel = true;
            PlanetDetailsDialog im = new PlanetDetailsDialog();
            im.setDetails(url);
            im.show(mActivity.getFragmentManager(),
                    "Image Preview");
        }
    }

}
