package com.example.yangzheming.minilinkedin_viewpager.Adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzheming.minilinkedin_viewpager.EduFragment;
import com.example.yangzheming.minilinkedin_viewpager.EducationEditActivity;
import com.example.yangzheming.minilinkedin_viewpager.ExperienceEditActivity;
import com.example.yangzheming.minilinkedin_viewpager.ExperienceFragment;
import com.example.yangzheming.minilinkedin_viewpager.R;
import com.example.yangzheming.minilinkedin_viewpager.ViewHolder.EducationViewHolder;
import com.example.yangzheming.minilinkedin_viewpager.ViewHolder.ExperienceViewHolder;
import com.example.yangzheming.minilinkedin_viewpager.models.Experience;
import com.example.yangzheming.minilinkedin_viewpager.utils.DateUtils;
import com.example.yangzheming.minilinkedin_viewpager.utils.StyleUtils;

import java.util.ArrayList;

/**
 * Created by yangzheming on 9/13/16.
 */

public class ExperienceAdapter extends RecyclerView.Adapter {

    private ArrayList<Experience> data;
    private FragmentActivity activity;

    public ExperienceAdapter(ArrayList<Experience> data, FragmentActivity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_item, parent, false);
        return new ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Experience experience = data.get(position);
        String startDate = DateUtils.dateToString(experience.startDate);
        String endDate = DateUtils.dateToString(experience.endDate);
        ((ExperienceViewHolder)holder).company.setText(experience.company);
        ((ExperienceViewHolder)holder).title.setText(experience.title+", "+startDate+"~"+endDate);
        ((ExperienceViewHolder)holder).details.setText(StyleUtils.formatItems(experience.details));
        ((ExperienceViewHolder)holder).exp_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ExperienceEditActivity.class);
                intent.putExtra(ExperienceEditActivity.KEY_EXP, experience);
                activity.startActivityForResult(intent, ExperienceFragment.REQ_CODE_EDIT_EXP_INFO);


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
