package com.android.graduate.daoway.g_location;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.widget.PinnedHeaderExpandableListView;
import com.android.graduate.daoway.widget.SlideView;
import com.android.graduate.daoway.x_http.HttpUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/6.
 */
public class CitiesActivity extends BaseActivity {
    @BindView(R.id.cities_back_iv)
    ImageView backIv;
    @BindView(R.id.cities_ex_list)
    PinnedHeaderExpandableListView citiesExList;
    @BindView(R.id.cities_slideView)
    SlideView citiesSV;
    private List<String> keys = new ArrayList<>();
    private Map<String, List<CitiesBean.DataBean>> mapDatas = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_cities);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    private void initListener() {

    }

    private void initData() {
        String[] mark = new String[]{ "hot", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N",  "P", "Q", "R", "S", "T",  "W", "X", "Y", "Z"};
        Collections.addAll(keys,mark);//将mark装入keys

        HttpUtils.init().queryCitiesBean().enqueue(new Callback<CitiesBean>() {
            @Override
            public void onResponse(Call<CitiesBean> call, Response<CitiesBean> response) {
                formatResult(response.body());
            }

            @Override
            public void onFailure(Call<CitiesBean> call, Throwable t) {

            }
        });
    }

    private void formatResult(CitiesBean citiesBean) {
        CitiesBean.DataBean data = citiesBean.getData();

        for (int i = 0; i <keys.size() ; i++) {


        }
    }

    private void initView() {

    }

    /*
    * 点击返回键结束当前activity
    * */
    @OnClick(R.id.cities_back_iv)
    public void onClick() {
        finish();
    }


    class CitiesAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return keys == null ? 0 : keys.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (groupPosition <= 1) {
                return 1;
            }
            String key = keys.get(groupPosition);
            List<CitiesBean.DataBean> dataBeen = mapDatas.get(keys);
            return dataBeen == null ? 0 : dataBeen.size();
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean b, View groupView, ViewGroup viewGroup) {
            TextView textView = (TextView) groupView;
            if (textView == null) {
                textView = new TextView(CitiesActivity.this);
            }
            textView.setText(keys.get(groupPosition));
            return textView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean b, View childView, ViewGroup parent) {
            if (groupPosition == 0) {
                View view = LayoutInflater.from(CitiesActivity.this).inflate(R.layout.item_cities_location, null, false);
                return view;
            }
            if (groupPosition == 1) {
                View view = LayoutInflater.from(CitiesActivity.this).inflate(R.layout.item_cities_hot, null, false);

                return view;
            }

            TextView textView = (TextView) childView;
            if (textView == null) {
                textView = new TextView(CitiesActivity.this);
            }


            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }


}
