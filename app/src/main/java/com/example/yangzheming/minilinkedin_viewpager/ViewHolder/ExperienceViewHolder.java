package com.example.yangzheming.minilinkedin_viewpager.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yangzheming.minilinkedin_viewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangzheming on 9/13/16.
 */

public class ExperienceViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.experience_company) public TextView company;
    @BindView(R.id.experience_title) public TextView title;
    @BindView(R.id.experience_details) public TextView details;
    @BindView(R.id.edit_experience_btn) public ImageButton exp_btn;

    public ExperienceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
