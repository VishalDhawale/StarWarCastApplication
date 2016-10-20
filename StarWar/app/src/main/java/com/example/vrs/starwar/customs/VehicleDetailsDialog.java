package com.example.vrs.starwar.customs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vrs.starwar.R;
import com.example.vrs.starwar.bean.Vehicle;
import com.example.vrs.starwar.parent.StarwarParentDialogFragment;


public class VehicleDetailsDialog extends StarwarParentDialogFragment {
    private Vehicle vehicle;
    private TextView tv_model;
    private TextView tv_passanger;
    private TextView tv_cost;
    private TextView tv_capacity;
    private TextView tv_manufacture;
    private TextView tv_length;


    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.dialog_vehicle_preview, null);
        setWidth(.80);
        setHeight(.80);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        enalbleHeaderCancelButton(vehicle.getName(), false);
        if (TextUtils.isEmpty(vehicle.getName()))
            setTitleText("Details");
        else
            setTitleText(vehicle.getName());
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

    private void initViews() {


        tv_model = (TextView) view.findViewById(R.id.tv_model);
        tv_manufacture = (TextView) view.findViewById(R.id.tv_manufacture);
        tv_capacity = (TextView) view.findViewById(R.id.tv_capacity);
        tv_passanger = (TextView) view.findViewById(R.id.tv_passanger);
        tv_length = (TextView) view.findViewById(R.id.tv_length);
        tv_cost = (TextView) view.findViewById(R.id.tv_cost);
    }

    private void initMembers() {
    }

    private void initListeners() {
    }

    private void initData() {
        try {
            tv_model.setText(vehicle.getModel());
            tv_length.setText(vehicle.getLength());
            tv_passanger.setText(vehicle.getPassengers());
            tv_capacity.setText(vehicle.getCargo_capacity());
            tv_manufacture.setText(vehicle.getManufacturer());
            tv_cost.setText(vehicle.getCost_in_credits());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setDetails(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
