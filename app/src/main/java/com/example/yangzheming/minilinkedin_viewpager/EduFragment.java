package com.example.yangzheming.minilinkedin_viewpager;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yangzheming.minilinkedin_viewpager.Adapter.EducationAdapter;
import com.example.yangzheming.minilinkedin_viewpager.models.Education;
import com.example.yangzheming.minilinkedin_viewpager.utils.DateUtils;
import com.example.yangzheming.minilinkedin_viewpager.utils.ModelUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangzheming on 9/12/16.
 */

public class EduFragment extends Fragment {

    public static final int REQ_CODE_EDIT_EDU_INFO = 101;


    private ArrayList<Education> educations;
    private EducationAdapter adapter;




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edu_exp_pro_list_fragment, container, false);
     //  fakeData();
        educations = getArguments().getParcelableArrayList(MainActivity.MODEL_EDUCATIONS);

        Log.d("educations", educations.toString());
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EducationEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDIT_EDU_INFO);


            }
        });
            return view;


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        adapter = new EducationAdapter(educations, getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ_CODE_EDIT_EDU_INFO) {
                String edu_id = data.getStringExtra(EducationEditActivity.KEY_EDUCATION_ID);
                if (edu_id != null) {
                    delete(edu_id);

                } else {
                    Education edu = data.getParcelableExtra(EducationEditActivity.KEY_EDU);

                    update(edu);

                }
            }
        }
    }

    private void delete(String edu_id) {

        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            if (TextUtils.equals(e.id, edu_id)) {
                educations.remove(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
        ModelUtils.save(getContext(), MainActivity.MODEL_EDUCATIONS, educations);

    }
    private void update(Education edu) {
        boolean found = false;
        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            if (TextUtils.equals(e.id, edu.id)) {
                found = true;
                educations.set(i, edu);
                break;
            }
        }
        if (found == false)
            educations.add(edu);

        adapter.notifyDataSetChanged();
        ModelUtils.save(getContext(), MainActivity.MODEL_EDUCATIONS, educations);
    }



    //    private void fakeData() {
//        educations = new ArrayList<Education>();
//        Education a = new Education();
//        a.school = "WPI";
//        a.major = "Computer Engineering";
//        a.startDate = DateUtils.stringToDate("09/2014");
//        a.endDate = DateUtils.stringToDate("08/2016");
//        a.courses = new ArrayList<String>();
//        a.courses.add("Algorithms");
//        a.courses.add("Operating System");
//        educations.add(a);
//    }
}
