package com.example.yangzheming.minilinkedin_viewpager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangzheming.minilinkedin_viewpager.models.BasicInfo;
import com.example.yangzheming.minilinkedin_viewpager.utils.ModelUtils;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * Created by yangzheming on 9/12/16.
 */

public class BasicInfoFragment extends Fragment {

    private static final int REQ_CODE_EDIT_BASIC_INFO = 100;
    private static final String MODEL_BASIC_INFO = "Basic Information";


    private ImageButton basic_info_edit;
    private TextView basic_info_name;
    private TextView basic_info_email;
    private TextView basic_info_address;
    private BasicInfo basicInfo;



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        basic_info_name.setText(basicInfo.name);
        basic_info_email.setText(basicInfo.email);
        basic_info_address.setText(basicInfo.address);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_info_show, container, false);
        basic_info_edit = (ImageButton) view.findViewById(R.id.basic_btn);
        basic_info_name = (TextView) view.findViewById(R.id.basic_info_name);
        basic_info_email = (TextView) view.findViewById(R.id.basic_info_email);
        basic_info_address = (TextView) view.findViewById(R.id.basic_info_address);

        basicInfo = (BasicInfo) getArguments().get(MainActivity.MODEL_BASIC_INFO);

        basic_info_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BasicInfoEditActivity.class);
                intent.putExtra(BasicInfoEditActivity.KEY_BASIC_INFO, basicInfo);
                startActivityForResult(intent, REQ_CODE_EDIT_BASIC_INFO);


            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQ_CODE_EDIT_BASIC_INFO) {

            BasicInfo updateBasic = data.getParcelableExtra(BasicInfoEditActivity.KEY_BASIC_INFO);

            updateBasicInfo(updateBasic);
        }
    }

    private void updateBasicInfo(BasicInfo updateBasic) {
        ModelUtils.save(getContext(), MODEL_BASIC_INFO, updateBasic);

        this.basicInfo = updateBasic;
        onViewCreated(getView(),getArguments());


    }


}
