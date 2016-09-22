package com.example.yangzheming.minilinkedin_viewpager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzheming.minilinkedin_viewpager.BasicInfoEditActivity;
import com.example.yangzheming.minilinkedin_viewpager.EduFragment;
import com.example.yangzheming.minilinkedin_viewpager.EducationEditActivity;
import com.example.yangzheming.minilinkedin_viewpager.MainActivity;
import com.example.yangzheming.minilinkedin_viewpager.R;
import com.example.yangzheming.minilinkedin_viewpager.ViewHolder.EducationViewHolder;
import com.example.yangzheming.minilinkedin_viewpager.models.Education;
import com.example.yangzheming.minilinkedin_viewpager.utils.DateUtils;
import com.example.yangzheming.minilinkedin_viewpager.utils.StyleUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangzheming on 9/13/16.
 */

public class EducationAdapter extends RecyclerView.Adapter {

    private ArrayList<Education> data;
    private FragmentActivity activity;

    public EducationAdapter(ArrayList<Education> data, FragmentActivity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_item, parent, false);

        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Education education = data.get(position);
        String startDate = DateUtils.dateToString(education.startDate);
        String endDate = DateUtils.dateToString(education.endDate);
        ((EducationViewHolder)holder).school.setText(education.school);
        ((EducationViewHolder)holder).major.setText(education.major+", "+startDate+"~"+endDate);
        ((EducationViewHolder)holder).courses.setText(StyleUtils.formatItems(education.courses));
        ((EducationViewHolder)holder).edu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EducationEditActivity.class);
                intent.putExtra(EducationEditActivity.KEY_EDU, education);
                activity.startActivityForResult(intent, EduFragment.REQ_CODE_EDIT_EDU_INFO);


            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
