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
import com.example.vrs.starwar.bean.Specie;
import com.example.vrs.starwar.parent.StarwarParentDialogFragment;


public class SpeciesrDetailsDialog extends StarwarParentDialogFragment {
    private Specie specie;
    private TextView claissification;
    private TextView height;
    private TextView desg;
    private TextView hairColor;
    private TextView skinColor;
    private TextView eyeColor;


    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.dialog_species_preview, null);
        setWidth(.80);
        setHeight(.80);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        enalbleHeaderCancelButton(specie.getName(), false);
        if (TextUtils.isEmpty(specie.getName()))
            setTitleText("Details");
        else
            setTitleText(specie.getName());
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
        claissification = (TextView) view.findViewById(R.id.classification);
        desg = (TextView) view.findViewById(R.id.designation);
        height = (TextView) view.findViewById(R.id.average_height);
        skinColor = (TextView) view.findViewById(R.id.skin_colors);
        hairColor = (TextView) view.findViewById(R.id.hair_colors);
        eyeColor = (TextView) view.findViewById(R.id.eye_colors);
    }

    private void initMembers() {
    }

    private void initListeners() {
    }

    private void initData() {
        try {
            eyeColor.setText(specie.getEye_colors());
            hairColor.setText(specie.getHair_colors());
            claissification.setText(specie.getClassification());
            desg.setText(specie.getDesignation());
            height.setText(specie.getAverage_height());
            skinColor.setText(specie.getSkin_colors());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setDetails(Specie url) {
        specie = url;
    }
}
