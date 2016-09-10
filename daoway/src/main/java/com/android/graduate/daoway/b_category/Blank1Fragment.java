package com.android.graduate.daoway.b_category;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.graduate.daoway.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Blank1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Blank1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Blank1Fragment extends Fragment {



    public static Blank1Fragment newInstance(Bundle args) {
        Blank1Fragment fragment = new Blank1Fragment();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank1, container, false);
    }

}
