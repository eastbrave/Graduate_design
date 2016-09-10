package com.android.graduate.daoway.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.b_category.ClassDetailFirstFragment;
import com.android.graduate.daoway.b_category.ClassDetailSecondFragment;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.y_bean.CategoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassDetailActivity extends BaseActivity {

    @BindView(R.id.ClassDetail_image)
    ImageView ClassDetailImage;
    @BindView(R.id.ClassDetail_rg)
    RadioGroup ClassDetailRg;
    @BindView(R.id.ClassDetail_search)
    ImageView ClassDetailSearch;
    @BindView(R.id.ClassDetail_show)
    FrameLayout ClassDetailShow;
    @BindView(R.id.ClassDetail_rbt)
    RadioButton ClassDetailRbt;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager;
    private int cur;
    private RadioButton[] radioArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        ButterKnife.bind(this);
        initFragment();
        initRg();
        initListen();
    }

    private void initListen() {
        ClassDetailRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                for (int i = 0; i < radioArray.length; i++) {
                    if (checkedId == radioArray[i].getId()) {
                        initSwitch(i);
                    }
                }
            }
        });
    }

    private void initSwitch(int i) {
        Fragment fragment = fragments.get(i);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.ClassDetail_show, fragment).hide(fragments.get(cur));
        } else {
            transaction.hide(fragments.get(cur)).show(fragment);
        }
        transaction.commit();
        cur = i;
    }

    private void initRg() {
        int length = ClassDetailRg.getChildCount();
        radioArray = new RadioButton[length];
        for (int i = 0; i < length; i++) {
            radioArray[i] = (RadioButton) ClassDetailRg.getChildAt(i);
        }
    }

    private void initFragment() {
        Intent intent = getIntent();
        CategoryBean.DataBean bean = (CategoryBean.DataBean) intent.getSerializableExtra("bean");

        CategoryBean.DataBean.TagsBean tags = (CategoryBean.DataBean.TagsBean) intent.getSerializableExtra("tags");
        String name = bean.getName();
        ClassDetailRbt.setText(name);

        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        bundle.putSerializable("tags",tags);
        ClassDetailFirstFragment classDetailFirstFragment = ClassDetailFirstFragment.newInstance(bundle);
        String id = bean.getId();
        Bundle bundle1=new Bundle();
        bundle1.putString("id",id);
        ClassDetailSecondFragment classDetailSecondFragment = ClassDetailSecondFragment.newInstance(bundle1);
        fragments.add(classDetailFirstFragment);
        fragments.add(classDetailSecondFragment);
        //往FrameLayout中提交第一个fragment；
        Fragment fragment = fragments.get(0);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.ClassDetail_show, fragment);
        transaction.commit();
        cur = 0;
    }
}
