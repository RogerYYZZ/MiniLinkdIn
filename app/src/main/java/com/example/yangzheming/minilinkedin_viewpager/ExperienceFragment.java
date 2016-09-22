package com.example.yangzheming.minilinkedin_viewpager;

import android.app.Activity;
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

import com.example.yangzheming.minilinkedin_viewpager.Adapter.EducationAdapter;
import com.example.yangzheming.minilinkedin_viewpager.Adapter.ExperienceAdapter;
import com.example.yangzheming.minilinkedin_viewpager.models.Education;
import com.example.yangzheming.minilinkedin_viewpager.models.Experience;
import com.example.yangzheming.minilinkedin_viewpager.utils.ModelUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by yangzheming on 9/12/16.
 */

public class ExperienceFragment extends Fragment{
    public static final int REQ_CODE_EDIT_EXP_INFO = 102;


    private ArrayList<Experience> experiences;
    private ExperienceAdapter adapter;




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edu_exp_pro_list_fragment, container, false);
        //  fakeData();
        experiences = getArguments().getParcelableArrayList(MainActivity.MODEL_EXPERIENCES);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExperienceEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDIT_EXP_INFO);


            }
        });
        return view;


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        adapter = new ExperienceAdapter(experiences, getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ_CODE_EDIT_EXP_INFO) {
                String exp_id = data.getStringExtra(ExperienceEditActivity.KEY_EXP_ID);
                if (exp_id != null) {
                    delete(exp_id);

                } else {
                    Experience exp = data.getParcelableExtra(ExperienceEditActivity.KEY_EXP);

                    update(exp);

                }
            }
        }
    }

    private void delete(String exp_id) {

        for (int i = 0; i < experiences.size(); i++) {
            Experience e = experiences.get(i);
            if (TextUtils.equals(e.id, exp_id)) {
                experiences.remove(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
        ModelUtils.save(getContext(), MainActivity.MODEL_EXPERIENCES, experiences);

    }
    private void update(Experience exp) {
        boolean found = false;
        for (int i = 0; i < experiences.size(); i++) {
            Experience e = experiences.get(i);
            if (TextUtils.equals(e.id, exp.id)) {
                found = true;
                experiences.set(i, exp);
                break;
            }
        }
        if (found == false)
            experiences.add(exp);

        adapter.notifyDataSetChanged();
        ModelUtils.save(getContext(), MainActivity.MODEL_EXPERIENCES, experiences);
    }
}
