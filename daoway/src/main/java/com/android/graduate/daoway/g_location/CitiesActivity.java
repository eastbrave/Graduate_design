package com.android.graduate.daoway.g_location;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.widget.MyGridView;
import com.android.graduate.daoway.widget.PinnedHeaderExpandableListView;
import com.android.graduate.daoway.widget.SlideView;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.x_http.HttpUtils_city;

import java.io.ObjectInputStream;
import java.lang.reflect.Field;
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
    private Map<String, List<CitiesBean.ResultBean.CityListBean>> mapDatas = new HashMap<>();
    private CitiesAdapter citiesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }


    private void initListener() {

        citiesExList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

        citiesSV.setOnTouchListener(new SlideView.OnTouchListener() {
            @Override
            public void onTouch(String letter) {
                for (int i = 0; i <keys.size(); i++) {
                    String key = keys.get(i);
                    if(key.equals(letter)){
                        citiesExList.setSelectedGroup(i);
                    }
                }
            }
        });





    }

    private void initData() {
       /* String[] mark = new String[]{ "hot", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N",  "P", "Q", "R", "S", "T",  "W", "X", "Y", "Z"};
        Collections.addAll(keys,mark);//将mark装入keys*/
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        HttpUtils_city.init().queryCitiesBean().enqueue(new Callback<CitiesBean>() {
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
        List<CitiesBean.ResultBean> result = citiesBean.getResult();
        keys.add("*");
        for (int i = 0; i <result.size() ; i++) {
            keys.add(result.get(i).getBegin_key());
            String beginKey=result.get(i).getBegin_key();
            List<CitiesBean.ResultBean.CityListBean> city_list = result.get(i).getCity_list();
            mapDatas.put(beginKey,city_list);
        }


        citiesAdapter.notifyDataSetChanged();
        for (int i = 0; i <keys.size() ; i++) {
            citiesExList.expandGroup(i);
        }

        citiesExList.setOnHeaderUpdateListener(new PinnedHeaderExpandableListView.OnHeaderUpdateListener() {
            @Override
            public View getPinnedHeader() {
                TextView textView = new TextView(CitiesActivity.this);
                LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setPadding(10, 10, 10, 10);
                textView.setLayoutParams(params);
                textView.setTextSize(13);
                textView.setTextColor(Color.parseColor("#000000"));
                textView.setBackgroundColor(Color.WHITE);
                return textView;
            }

            @Override
            public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
                String content =keys.get(firstVisibleGroupPos);
                TextView textView = (TextView) headerView;
                textView.setText(content);
            }
        });



    }

    private void initView() {
        citiesAdapter = new CitiesAdapter(mapDatas);
        citiesExList.setAdapter(citiesAdapter);
        citiesExList.setGroupIndicator(null);

    }

    /*
    * 点击返回键结束当前activity
    * */
    @OnClick(R.id.cities_back_iv)
    public void onClick() {
        finish();
    }


    class CitiesAdapter extends BaseExpandableListAdapter {
        private Map<String, List<CitiesBean.ResultBean.CityListBean>> mapDatas ;

        public CitiesAdapter(Map<String, List<CitiesBean.ResultBean.CityListBean>> mapDatas) {
            this.mapDatas = mapDatas;
        }

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
            List<CitiesBean.ResultBean.CityListBean> cityListBeen = mapDatas.get(key);
            return cityListBeen == null ? 0 : cityListBeen.size();
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
                TextView tvCityName =(TextView) view.findViewById(R.id.item_cities_location_tv);
                tvCityName.setText("武汉");
                return view;
            }
            if (groupPosition == 1) {
                MyGridView view =(MyGridView) LayoutInflater.from(CitiesActivity.this).inflate(R.layout.item_cities_hot, null, false);
                String key = keys.get(groupPosition);
                List<CitiesBean.ResultBean.CityListBean> cityListBeen = mapDatas.get(key);
                CitiesGridAdapter adapter=new CitiesGridAdapter(CitiesActivity.this,cityListBeen);
                view.setAdapter(adapter);
                return view;
            }
            childView=LayoutInflater.from(CitiesActivity.this).inflate(R.layout.item_cities_child,null,false);

            TextView textView = (TextView) childView.findViewById(R.id.item_cities_child_tv);
            String city_name = mapDatas.get(keys.get(groupPosition)).get(childPosition).getCity_name();
           Log.i("east", "getChildView: "+city_name);
            textView.setText(city_name);

            return childView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }


}
