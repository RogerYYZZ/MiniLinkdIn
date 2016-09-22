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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzheming.minilinkedin_viewpager.Adapter.ExperienceAdapter;
import com.example.yangzheming.minilinkedin_viewpager.Adapter.ProjectAdapter;
import com.example.yangzheming.minilinkedin_viewpager.models.Experience;
import com.example.yangzheming.minilinkedin_viewpager.models.Project;
import com.example.yangzheming.minilinkedin_viewpager.utils.ModelUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by yangzheming on 9/12/16.
 */

public class ProjectFragment extends Fragment{

    public static final int REQ_CODE_EDIT_PRO_INFO = 103;


    private ArrayList<Project> projects;
    private ProjectAdapter adapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edu_exp_pro_list_fragment, container, false);
        //  fakeData();
        projects = getArguments().getParcelableArrayList(MainActivity.MODEL_PROJECTS);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProjectEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDIT_PRO_INFO);


            }
        });
        return view;


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        adapter = new ProjectAdapter(projects, getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ_CODE_EDIT_PRO_INFO) {
                String pro_id = data.getStringExtra(ProjectEditActivity.KEY_PRO_ID);
                if (pro_id != null) {
                    delete(pro_id);

                } else {
                    Project pro = data.getParcelableExtra(ProjectEditActivity.KEY_PRO);

                    update(pro);

                }
            }
        }
    }

    private void delete(String pro_id) {

        for (int i = 0; i < projects.size(); i++) {
            Project e = projects.get(i);
            if (TextUtils.equals(e.id, pro_id)) {
                projects.remove(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
        ModelUtils.save(getContext(), MainActivity.MODEL_PROJECTS, projects);

    }
    private void update(Project pro) {
        boolean found = false;
        for (int i = 0; i < projects.size(); i++) {
            Project e = projects.get(i);
            if (TextUtils.equals(e.id, pro.id)) {
                found = true;
                projects.set(i, pro);
                break;
            }
        }
        if (found == false)
            projects.add(pro);

        adapter.notifyDataSetChanged();
        ModelUtils.save(getContext(), MainActivity.MODEL_PROJECTS, projects);
    }
}
