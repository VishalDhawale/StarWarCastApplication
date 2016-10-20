package com.example.vrs.starwar.customs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vrs.starwar.R;
import com.example.vrs.starwar.bean.Planet;
import com.example.vrs.starwar.parent.StarwarParentDialogFragment;


public class PlanetDetailsDialog extends StarwarParentDialogFragment {
    private Planet planet;
    private ImageView image;
    private TextView tvRotation;
    private TextView tvOrbital;
    private TextView tvDiameter;
    private TextView tvClimate;
    private TextView tvGravity;
    private TextView tvTerrain;
    private TextView tvSurfaceWater;
    private TextView tvPopulation;


    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.dialog_planet_preview, null);
        setWidth(.80);
        setHeight(.80);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        enalbleHeaderCancelButton(planet.getName(), false);

            setTitleText(planet.getName());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    private void init() {
        initViews();
        initMembers();
        initListeners();
        initData();
    }

    private void initViews()
    {
     tvRotation =   (TextView)view.findViewById(R.id.tv_rotation);
     tvOrbital =   (TextView)view.findViewById(R.id.tv_orbital_period);
     tvDiameter =   (TextView)view.findViewById(R.id.tv_diameter);
     tvClimate =   (TextView)view.findViewById(R.id.tv_climate);
     tvGravity =   (TextView)view.findViewById(R.id.tv_gravity);
     tvTerrain =   (TextView)view.findViewById(R.id.tv_terrain);
     tvSurfaceWater =   (TextView)view.findViewById(R.id.tv_surfce_water);
     tvPopulation =   (TextView)view.findViewById(R.id.tv_population);
    }

    private void initMembers() {
    }

    private void initListeners() {
    }

    private void initData() {
        try {
           tvClimate.setText(planet.getClimate());
           tvRotation.setText(planet.getRoatation_period());
           tvDiameter.setText(planet.getDiameter());
           tvSurfaceWater.setText(planet.getSurface_water());
           tvOrbital.setText(planet.getRoatation_period());
           tvGravity.setText(planet.getGravity());
           tvPopulation.setText(planet.getPopulation());
           tvTerrain.setText(planet.getTerrain());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setDetails(Planet data) {
        planet =data;
    }
}
