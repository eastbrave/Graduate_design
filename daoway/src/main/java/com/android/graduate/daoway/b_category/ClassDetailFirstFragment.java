package com.android.graduate.daoway.b_category;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.y_bean.CategoryBean;
import com.android.graduate.daoway.y_bean.ClassDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ClassDetailFirstFragment extends Fragment {
    List<ClassDetailBean.DataBean.ItemsBean> items;
    CategoryBean.DataBean.TagsBean tags;
    CategoryBean.DataBean bean;
    @BindView(R.id.ClassDetail_first_tab)
    TabLayout ClassDetailFirstTab;
    @BindView(R.id.ClassDetail_first_vp)
    ViewPager ClassDetailFirstVp;
    @BindView(R.id.ClassDetail_first_image)
    ImageView ClassDetailFirstImage;
    private Context mContext;
    List<Fragment> fragments = new ArrayList<>();
    List<String> tabs = new ArrayList<>();
    private VpAdapter vpAdapter;

    private String name;

    public static ClassDetailFirstFragment newInstance(Bundle args) {
        ClassDetailFirstFragment fragment = new ClassDetailFirstFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_detail_first, container, false);
        ButterKnife.bind(this, view);
        initBean();
        initTab();
        initVP();
        setVp();
        ClassDetailFirstTab.setupWithViewPager(ClassDetailFirstVp);
        for (int i = 0; i <tabs.size() ; i++) {
            if (tabs.get(i).equals(name)) {
                ClassDetailFirstTab.getTabAt(i).select();
            }
        }
        return view;
    }

    private void setVp() {
        vpAdapter = new VpAdapter(getChildFragmentManager());
        ClassDetailFirstVp.setAdapter(vpAdapter);
    }

    private void initTab() {
        tabs.add("全部");
        for (int i = 0; i < bean.getTags().size(); i++) {
            tabs.add(bean.getTags().get(i).getName());
        }
    }
    private void initVP() {
        Bundle bundle1=new Bundle();
        bundle1.putSerializable("bean",bean);
        AllFragment allFragment = AllFragment.newInstance(bundle1);
        fragments.add(allFragment);
        for (int i = 0; i < bean.getTags().size(); i++) {
            Bundle bundle=new Bundle();
            bundle.putString("name",tabs.get(i+1));
            VpFragment vpFragment = VpFragment.newInstance(bundle);

            fragments.add(vpFragment);
        }
    }

    @OnClick(R.id.ClassDetail_first_image)
    public void onClick() {
        ClassDetailFirstTab.getTabAt(tabs.size()-1).select();
    }

    class VpAdapter extends FragmentStatePagerAdapter {

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }

    private void initBean() {
        Bundle arguments = getArguments();
        bean = (CategoryBean.DataBean) arguments.getSerializable("bean");


         tags = (CategoryBean.DataBean.TagsBean) arguments.getSerializable("tags");
        try {
            name = tags.getName();
        }catch (Exception e){
            name="全部";
        }



    }



}
