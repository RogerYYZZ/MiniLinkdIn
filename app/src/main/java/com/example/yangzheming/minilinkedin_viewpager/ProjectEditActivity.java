package com.example.yangzheming.minilinkedin_viewpager;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yangzheming.minilinkedin_viewpager.models.Project;
import com.example.yangzheming.minilinkedin_viewpager.utils.DateUtils;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by yangzheming on 9/14/16.
 */

public class ProjectEditActivity extends EditBaseActivity<Project> {

    @BindView(R.id.pro_name_edit) EditText name;
    @BindView(R.id.pro_start_edit) EditText startDate;
    @BindView(R.id.pro_end_edit) EditText endDate;
    @BindView(R.id.pro_detail_edit) EditText details;
    @BindView(R.id.pro_delete) TextView delete;

    public static final String KEY_PRO = "key_pro";
    public static final String KEY_PRO_ID = "key_pro_id";

    @Override
    protected void saveAndExit(@Nullable Project data) {
        if (data == null) {
            data = new Project();
        }
        data.name = name.getText().toString();
        data.startDate = DateUtils.stringToDate(startDate.getText().toString());
        data.endDate = DateUtils.stringToDate(endDate.getText().toString());
        data.details = Arrays.asList(TextUtils.split((details).getText().toString(), "\n"));

        Intent intent = new Intent();
        intent.putExtra(KEY_PRO, data);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    @Override
    protected void setUIForEdit(@Nullable final Project data) {

        name.setText(data.name);
        startDate.setText(DateUtils.dateToString(data.startDate));
        endDate.setText(DateUtils.dateToString(data.endDate));
        details.setText(data.details.get(0));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(KEY_PRO_ID, data.id);
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
    protected Project initializeDate() {
        return getIntent().getParcelableExtra(KEY_PRO);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.project_edit;
    }
}
