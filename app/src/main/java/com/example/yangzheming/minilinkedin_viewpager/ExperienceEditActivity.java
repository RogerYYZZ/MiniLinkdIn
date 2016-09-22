package com.example.yangzheming.minilinkedin_viewpager;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yangzheming.minilinkedin_viewpager.models.Experience;
import com.example.yangzheming.minilinkedin_viewpager.utils.DateUtils;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by yangzheming on 9/14/16.
 */

public class ExperienceEditActivity extends EditBaseActivity<Experience> {


    @BindView(R.id.exp_company_edit)  EditText company;
    @BindView(R.id.exp_title_edit) EditText title;
    @BindView(R.id.comp_start_edit) EditText startDate;
    @BindView(R.id.comp_end_edit) EditText endDate;
    @BindView(R.id.comp_detail_edit) EditText details;
    @BindView(R.id.company_delete) TextView delete;

    public static final String KEY_EXP = "key_exp";
    public static final String KEY_EXP_ID = "key_exp_id";

    @Override
    protected void saveAndExit(@Nullable Experience data) {
        if (data == null) {
            data = new Experience();
        }
        data.company = company.getText().toString();
        data.title = title.getText().toString();
        data.startDate = DateUtils.stringToDate(startDate.getText().toString());
        data.endDate = DateUtils.stringToDate(endDate.getText().toString());
        data.details = Arrays.asList(TextUtils.split((details).getText().toString(), "\n"));

        Intent intent = new Intent();
        intent.putExtra(KEY_EXP, data);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    @Override
    protected void setUIForEdit(@Nullable final Experience data) {


        company.setText(data.company);
        title.setText(data.title);
        startDate.setText(DateUtils.dateToString(data.startDate));
        endDate.setText(DateUtils.dateToString(data.endDate));
        details.setText(data.details.get(0));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(KEY_EXP_ID, data.id);
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
    protected Experience initializeDate() {
        return getIntent().getParcelableExtra(KEY_EXP);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.experience_edit;
    }
}
