package com.android.graduate.daoway.start;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.b_category.Blank1Fragment;
import com.android.graduate.daoway.b_category.Blank2Fragment;
import com.android.graduate.daoway.b_category.Blank3Fragment;
import com.android.graduate.daoway.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassDetailitemActivity extends BaseActivity {

    @BindView(R.id.tab_classDetail_item)
    TabLayout tabClassDetailItem;
    @BindView(R.id.vp_classDetail_item)
    ViewPager vpClassDetailItem;
   List<String> tabs=new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detailitem);
        ButterKnife.bind(this);
        initTab();
        initVP();
        setVp();
        tabClassDetailItem.setupWithViewPager(vpClassDetailItem);
    }

    private void setVp() {
        vpAdapter = new VpAdapter(getSupportFragmentManager());
        vpClassDetailItem.setAdapter(vpAdapter);
    }

    private void initVP() {
        Blank1Fragment blank1Fragment = Blank1Fragment.newInstance(null);
        Blank2Fragment blank2Fragment = Blank2Fragment.newInstance(null);
        Blank3Fragment blank3Fragment = Blank3Fragment.newInstance(null);
        fragments.add(blank1Fragment);
        fragments.add(blank2Fragment);
        fragments.add(blank3Fragment);
    }

    private void initTab() {
        tabs.add("服务");
        tabs.add("详情");
        tabs.add("评价");
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

}
