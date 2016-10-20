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
import com.example.vrs.starwar.bean.Starship;
import com.example.vrs.starwar.parent.StarwarParentDialogFragment;


public class StarshipDetailsDialog extends StarwarParentDialogFragment {
    private Starship starship;

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
        view = inflater.inflate(R.layout.dialog_starship_preview, null);
        setWidth(.80);
        setHeight(.80);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        enalbleHeaderCancelButton(starship.getName(), false);
        if (TextUtils.isEmpty(starship.getName()))
            setTitleText("Details");
        else
            setTitleText(starship.getName());
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
            tv_model.setText(starship.getModel());
            tv_length.setText(starship.getLength());
            tv_passanger.setText(starship.getPassengers());
            tv_capacity.setText(starship.getCargo_capacity());
            tv_manufacture.setText(starship.getManufacturer());
            tv_cost.setText(starship.getCost_in_credits());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setDetails(Starship vehicle) {
        this.starship = vehicle;
    }
}
