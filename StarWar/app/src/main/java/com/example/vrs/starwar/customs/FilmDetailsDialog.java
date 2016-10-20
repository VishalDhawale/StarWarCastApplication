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
import com.example.vrs.starwar.bean.Film;
import com.example.vrs.starwar.parent.StarwarParentDialogFragment;


public class FilmDetailsDialog extends StarwarParentDialogFragment {
    private Film film;
    private TextView epesodeId;
    private TextView director;
    private TextView crawl;
    private TextView relDate;
    private TextView producer;


    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.dialog_film_preview, null);
        setWidth(.80);
        setHeight(.80);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        enalbleHeaderCancelButton(film.getTitle(), false);
        if (TextUtils.isEmpty(film.getTitle()))
            setTitleText("Details");
        else
            setTitleText(film.getTitle());
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
        epesodeId = (TextView) view.findViewById(R.id.episode_id);
        crawl = (TextView) view.findViewById(R.id.opening_crawl);
        director = (TextView) view.findViewById(R.id.director);
        producer = (TextView) view.findViewById(R.id.producer);
        relDate = (TextView) view.findViewById(R.id.rel_date);
    }

    private void initMembers() {
    }

    private void initListeners() {
    }

    private void initData() {
        try {
            producer.setText(film.getProducer());
            director.setText(film.getDirector());
            crawl.setText(film.getOpening_crawl());
            epesodeId.setText(film.getEpisode_id());
            relDate.setText(film.getRelease_date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setDetails(Film url) {
        film = url;
    }
}
