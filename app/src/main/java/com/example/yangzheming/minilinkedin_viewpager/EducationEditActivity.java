package com.example.yangzheming.minilinkedin_viewpager;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangzheming.minilinkedin_viewpager.models.Education;
import com.example.yangzheming.minilinkedin_viewpager.utils.DateUtils;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by yangzheming on 9/14/16.
 */

public class EducationEditActivity extends EditBaseActivity<Education>{

    @BindView(R.id.edu_school_edit) EditText school;
    @BindView(R.id.edu_major_edit) EditText major;
    @BindView(R.id.edu_start_edit) EditText startDate;
    @BindView(R.id.edu_end_edit) EditText endDate;
    @BindView(R.id.edu_course_edit) EditText courses;
    @BindView(R.id.education_delete) TextView delete;

    public static final String KEY_EDU = "key_edu";
    public static final String KEY_EDUCATION_ID = "education_id";

    @Override
    protected void saveAndExit(@Nullable Education data) {
        if (data == null) {
            data = new Education();
        }

        data.school = school.getText().toString();
        data.major = major.getText().toString();
        data.startDate = DateUtils.stringToDate(startDate.getText().toString());
        data.endDate = DateUtils.stringToDate(endDate.getText().toString());
        data.courses = Arrays.asList(TextUtils.split((courses).getText().toString(), "\n"));

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EDU, data);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    @Override
    protected void setUIForEdit(@Nullable final Education data) {
        school.setText(data.school);
        major.setText(data.major);
        startDate.setText(DateUtils.dateToString(data.startDate));
        endDate.setText(DateUtils.dateToString(data.endDate));
        courses.setText(TextUtils.join("\n", data.courses));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(KEY_EDUCATION_ID, data.id);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    protected void setupUIForCreate() {
        delete.setVisibility(View.GONE);
    }

    @Override
    protected Education initializeDate() {
        return getIntent().getParcelableExtra(KEY_EDU);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.education_edit;
    }
}
