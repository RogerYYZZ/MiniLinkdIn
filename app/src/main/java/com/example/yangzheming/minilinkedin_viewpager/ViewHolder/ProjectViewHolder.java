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

public class ProjectViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.pro_name) public TextView name;
    @BindView(R.id.project_details) public TextView details;
    @BindView(R.id.edit_project_btn) public ImageButton pro_btn;

    public ProjectViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
