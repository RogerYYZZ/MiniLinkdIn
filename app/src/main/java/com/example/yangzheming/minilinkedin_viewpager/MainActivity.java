package com.example.yangzheming.minilinkedin_viewpager;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yangzheming.minilinkedin_viewpager.models.BasicInfo;
import com.example.yangzheming.minilinkedin_viewpager.models.Education;
import com.example.yangzheming.minilinkedin_viewpager.models.Experience;
import com.example.yangzheming.minilinkedin_viewpager.models.Project;
import com.example.yangzheming.minilinkedin_viewpager.utils.ModelUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


//    public static final String BASIC_INFO_MESSAGE = "basic information";
//    public static final String EDUCATION_MESSAGE = "educations";
//    public static final String EXPERIENCE_MESSAGE = "experiences";
//    public static final String PROJECT_MESSAGE = "projects";

    public static final String MODEL_BASIC_INFO = "Basic Information";
    public static final String MODEL_EDUCATIONS = "educations";
    public static final String MODEL_EXPERIENCES = "experiences";
    public static final String MODEL_PROJECTS = "projects";

    private TabLayout tabLayout;
    private BasicInfo basic_info;
    private ArrayList<Education> educations;
    private ArrayList<Experience> experiences;
    private ArrayList<Project> projects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaddata();

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new NumberPagerAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.view_pager_tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackgroundResource(R.color.tab_bg);

        setupTab();

        getSupportActionBar().hide();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setupTab() {

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_white_24px);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_school_white_24px);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_work_white_24px);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_assignment_white_24px);
    }


    private class NumberPagerAdapter extends FragmentPagerAdapter {
        public NumberPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                BasicInfoFragment basicFragment = new BasicInfoFragment();
                basicFragment.setArguments(setupBundle(position));
                return basicFragment;
            } else if (position == 1) {
                EduFragment eduFragment = new EduFragment();
                eduFragment.setArguments(setupBundle(position));
                return eduFragment;
            } else if (position == 2) {
                ExperienceFragment experienceFragment = new ExperienceFragment();
                experienceFragment.setArguments(setupBundle(position));
                return experienceFragment;
            } else {

                ProjectFragment projectFragment = new ProjectFragment();
                projectFragment.setArguments(setupBundle(position));
                return projectFragment;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

    }
    private void loaddata() {
        BasicInfo savedBasicInfo = ModelUtils.read(this,
                MODEL_BASIC_INFO,
                new TypeToken<BasicInfo>(){});
        basic_info = savedBasicInfo == null ? new BasicInfo() : savedBasicInfo;

        ArrayList<Education> savedEducation = ModelUtils.read(this,
                MODEL_EDUCATIONS,
                new TypeToken<ArrayList<Education>>(){});
        educations = savedEducation == null ? new ArrayList<Education>() : savedEducation;

        ArrayList<Experience> savedExperience = ModelUtils.read(this,
                MODEL_EXPERIENCES,
                new TypeToken<ArrayList<Experience>>(){});
        experiences = savedExperience == null ? new ArrayList<Experience>() : savedExperience;

        ArrayList<Project> savedProjects = ModelUtils.read(this,
                MODEL_PROJECTS,
                new TypeToken<ArrayList<Project>>(){});
        projects = savedProjects == null ? new ArrayList<Project>() : savedProjects;

    }

    private Bundle setupBundle(int position) {
        Bundle bundle = new Bundle();
        if (position == 0) {
            bundle.putParcelable(MODEL_BASIC_INFO, basic_info);
        } else if (position == 1) {
            bundle.putParcelableArrayList(MODEL_EDUCATIONS, educations);

        } else if (position == 2) {
            bundle.putParcelableArrayList(MODEL_EXPERIENCES, experiences);
        } else bundle.putParcelableArrayList(MODEL_PROJECTS, projects);

        return bundle;

    }


    private void fakeData() {
        basic_info = new BasicInfo();
        basic_info.name = "Zheming Yang";
        basic_info.email = "zhemingyang2010@gmail.com";
        basic_info.address = "160 plsasant street";
    }
}
