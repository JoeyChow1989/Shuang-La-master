package com.qx.www.shuang_la_master.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qx.www.shuang_la_master.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuiHuanFragment extends Fragment
{


    public DuiHuanFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dui_huan, container, false);
    }

}