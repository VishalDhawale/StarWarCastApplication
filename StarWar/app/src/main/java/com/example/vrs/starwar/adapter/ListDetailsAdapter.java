package com.example.vrs.starwar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.vrs.starwar.LauncherDetailsActivity;
import com.example.vrs.starwar.R;
import com.example.vrs.starwar.parent.BaseActivity;

import java.util.ArrayList;


/**
 * Created by Vishal Dhawale on 10/18/2016.
 */
public class ListDetailsAdapter extends RecyclerView.Adapter<ListDetailsAdapter.ViewHolder> {

    ArrayList<String> list = new ArrayList<>();
    private BaseActivity mContext;


    public ListDetailsAdapter(BaseActivity mContext, ArrayList<String> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.cardtitle.setText(list.get(position));
        holder.llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LauncherDetailsActivity) mContext).callasync(list.get(position));

            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cardimage;
        TextView cardtitle;
        LinearLayout llView;


        public ViewHolder(View itemView) {
            super(itemView);

            llView = (LinearLayout) itemView.findViewById(R.id.rel);
            cardimage = (ImageView) itemView.findViewById(R.id.cardimage);
            cardtitle = (TextView) itemView.findViewById(R.id.cardtitle);

        }
    }


}

