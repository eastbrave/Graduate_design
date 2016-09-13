package com.android.graduate.daoway.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.HomeFragment;
import com.android.graduate.daoway.b_category.CategoryFragment;
import com.android.graduate.daoway.c_cart.CartFragment;
import com.android.graduate.daoway.d_order.OrderFragment;
import com.android.graduate.daoway.e_mine.MineFragment;
import com.android.graduate.daoway.f_search.SearchActivity;
import com.android.graduate.daoway.start.StartActivity;
import com.android.graduate.daoway.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    List<Fragment> fragments = new ArrayList<>();
    RadioButton[] radioArray;
    @BindView(R.id.switch_rg)
    RadioGroup switchRg;
    @BindView(R.id.location_tv)
    TextView locationTv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.action_divider)
    View actionDivider;
    private int cur;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initRadioArray();
        initListener();
    }

    private void initRadioArray() {
        int length = switchRg.getChildCount();
        radioArray = new RadioButton[length];
        for (int i = 0; i < length; i++) {
            radioArray[i] = (RadioButton) switchRg.getChildAt(i);
        }
    }

    private void initListener() {
        switchRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                for (int i = 0; i < radioArray.length; i++) {
                    if (checkedId == radioArray[i].getId()) {
                        initSwitch(i);
                        switch (checkedId) {
                            case R.id.home_rb:
                                locationTv.setVisibility(View.VISIBLE);
                                titleTv.setVisibility(View.INVISIBLE);
                                actionDivider.setVisibility(View.VISIBLE);
                                searchIv.setVisibility(View.VISIBLE);
                                break;
                            case R.id.category_rb:
                                locationTv.setVisibility(View.VISIBLE);
                                titleTv.setVisibility(View.INVISIBLE);
                                actionDivider.setVisibility(View.VISIBLE);
                                searchIv.setVisibility(View.VISIBLE);
                                break;
                            case R.id.cart_rb:
                                locationTv.setVisibility(View.GONE);
                                titleTv.setVisibility(View.VISIBLE);
                                titleTv.setText("购物车");
                                actionDivider.setVisibility(View.INVISIBLE);
                                searchIv.setVisibility(View.GONE);
                                break;
                            case R.id.order_rb:
                                locationTv.setVisibility(View.GONE);
                                titleTv.setVisibility(View.VISIBLE);
                                titleTv.setText("订单");
                                actionDivider.setVisibility(View.INVISIBLE);
                                searchIv.setVisibility(View.GONE);
                                break;
                            case R.id.mine_rb:
                                locationTv.setVisibility(View.GONE);
                                titleTv.setVisibility(View.VISIBLE);
                                titleTv.setText("我的");
                                actionDivider.setVisibility(View.INVISIBLE);
                                searchIv.setVisibility(View.GONE);
                                break;
                        }
                    }
                }
            }
        });

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initSwitch(int i) {
        Fragment fragment = fragments.get(i);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.container_fl, fragment).hide(fragments.get(cur));
        } else {
            transaction.hide(fragments.get(cur)).show(fragment);
        }
        transaction.commit();
        cur = i;
    }

    /*
    * 初始化fragment
    * */
    private void initFragment() {
        fragments.add(HomeFragment.newInstance(null));
        fragments.add(CategoryFragment.newInstance(null));
        fragments.add(CartFragment.newInstance(null));
        fragments.add(OrderFragment.newInstance(null));
        fragments.add(MineFragment.newInstance(null));
        //往FrameLayout中提交第一个fragment；
        Fragment fragment = fragments.get(0);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_fl, fragment);
        transaction.commit();
        cur = 0;
    }


}
