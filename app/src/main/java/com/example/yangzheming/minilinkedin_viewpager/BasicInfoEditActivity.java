package com.example.yangzheming.minilinkedin_viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.yangzheming.minilinkedin_viewpager.models.BasicInfo;

import butterknife.BindView;

/**
 * Created by yangzheming on 9/13/16.
 */

public class BasicInfoEditActivity extends EditBaseActivity<BasicInfo>{

    public static final String KEY_BASIC_INFO = "key_basic_info";

    @BindView(R.id.basic_info_name_edit)  EditText name_edit;
    @BindView(R.id.basic_info_email_edit) EditText email_edit;
    @BindView(R.id.basic_info_address_edit) EditText address_edit;


    @Override
    protected void saveAndExit(@Nullable BasicInfo data) {

        if (data == null) {
            data = new BasicInfo();
        }

        data.name = name_edit.getText().toString();
        data.email = email_edit.getText().toString();
        data.address = address_edit.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(KEY_BASIC_INFO, data);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }



    @Override
    protected void setUIForEdit(@Nullable BasicInfo data) {
        name_edit.setText(data.name);
        email_edit.setText(data.email);
        address_edit.setText(data.address);
    }

    @Override
    protected void setupUIForCreate() {

    }

    @Override
    protected BasicInfo initializeDate() {
        return getIntent().getParcelableExtra(KEY_BASIC_INFO);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.basic_info_edit;
    }
}
