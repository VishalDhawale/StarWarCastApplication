package com.example.vrs.starwar.customs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.vrs.starwar.R;
import com.example.vrs.starwar.bean.People;
import com.example.vrs.starwar.parent.StarwarParentDialogFragment;


public class ActorDetailsDialog extends StarwarParentDialogFragment {
    private People people;
    private ImageView image;
    private TextView tvHeight;
    private TextView tvHairColor;
    private TextView tvSkinColor;
    private TextView tv_dob;
    private TextView tv_gender;


    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.dialog_actor_preview, null);
        setWidth(.80);
        setHeight(.80);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        enalbleHeaderCancelButton(people.getName(), false);
        if (TextUtils.isEmpty(people.getName()))
            setTitleText("Details");
        else
            setTitleText(people.getName());
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


        tvHeight = (TextView) view.findViewById(R.id.tv_height);
        tvHairColor = (TextView) view.findViewById(R.id.tv_hair_color);
        tvSkinColor = (TextView) view.findViewById(R.id.tv_skin_color);
        tv_dob = (TextView) view.findViewById(R.id.tv_dob);
        tv_gender = (TextView) view.findViewById(R.id.tv_gender);
    }

    private void initMembers() {
    }

    private void initListeners() {
    }

    private void initData() {
        try {
            tvHeight.setText(people.getHeight());
            tvHairColor.setText(people.getHair_color());
            tv_gender.setText(people.getGender());
            tv_dob.setText(people.getBirth_year());
            tvSkinColor.setText(people.getSkin_color());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setDetails(People url) {
        people = url;
    }
}
