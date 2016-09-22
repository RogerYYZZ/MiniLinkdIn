package com.example.yangzheming.minilinkedin_viewpager.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangzheming.minilinkedin_viewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangzheming on 9/13/16.
 */

public class EducationViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.education_school) public TextView school;
    @BindView(R.id.education_major) public TextView major;
    @BindView(R.id.education_course) public TextView courses;
    @BindView(R.id.education_btn) public ImageButton edu_btn;

    public EducationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
