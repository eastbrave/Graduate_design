package com.android.graduate.daoway.e_mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.graduate.daoway.R;

/**
 * Created by Administrator on 2016/9/5.
 */
public class MineFragment extends Fragment {

    public static MineFragment newInstance(Bundle bundle){
        MineFragment fragment=new MineFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO: 2016/9/5
        View view=inflater.inflate(R.layout.fragment_mine,container,false);
        return view;
    }
}
