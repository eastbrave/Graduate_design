package com.android.graduate.daoway.b_category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.graduate.daoway.R;


public class Blank3Fragment extends Fragment {



    public static Blank3Fragment newInstance(Bundle args) {
        Blank3Fragment fragment = new Blank3Fragment();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank3, container, false);
    }

}
