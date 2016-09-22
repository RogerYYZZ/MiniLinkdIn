package com.example.yangzheming.minilinkedin_viewpager.Adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzheming.minilinkedin_viewpager.ExperienceEditActivity;
import com.example.yangzheming.minilinkedin_viewpager.ExperienceFragment;
import com.example.yangzheming.minilinkedin_viewpager.ProjectEditActivity;
import com.example.yangzheming.minilinkedin_viewpager.ProjectFragment;
import com.example.yangzheming.minilinkedin_viewpager.R;
import com.example.yangzheming.minilinkedin_viewpager.ViewHolder.EducationViewHolder;
import com.example.yangzheming.minilinkedin_viewpager.ViewHolder.ExperienceViewHolder;
import com.example.yangzheming.minilinkedin_viewpager.ViewHolder.ProjectViewHolder;
import com.example.yangzheming.minilinkedin_viewpager.models.Project;
import com.example.yangzheming.minilinkedin_viewpager.utils.DateUtils;
import com.example.yangzheming.minilinkedin_viewpager.utils.StyleUtils;

import java.util.ArrayList;

/**
 * Created by yangzheming on 9/13/16.
 */

public class ProjectAdapter extends RecyclerView.Adapter {

    private ArrayList<Project> data;
    private FragmentActivity activity;

    public ProjectAdapter(ArrayList<Project> data, FragmentActivity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Project project = data.get(position);

        String startDate = DateUtils.dateToString(project.startDate);
        String endDate = DateUtils.dateToString(project.endDate);
        ((ProjectViewHolder)holder).name.setText(project.name+", "+startDate+"~"+endDate);

        ((ProjectViewHolder)holder).details.setText(StyleUtils.formatItems(project.details));

        ((ProjectViewHolder)holder).pro_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProjectEditActivity.class);
                intent.putExtra(ProjectEditActivity.KEY_PRO, project);
                activity.startActivityForResult(intent, ProjectFragment.REQ_CODE_EDIT_PRO_INFO);


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
