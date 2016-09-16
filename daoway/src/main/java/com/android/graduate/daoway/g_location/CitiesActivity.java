package com.android.graduate.daoway.g_location;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.widget.MyGridView;
import com.android.graduate.daoway.widget.PinnedHeaderExpandableListView;
import com.android.graduate.daoway.widget.SlideView;
import com.android.graduate.daoway.x_http.HttpUtils_city;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
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
    private LocationClient mLocationClient;
    private String cityName;
    private String lat;
    private String lot;
    private MyGridView viewGv;
    private TextView tvCityName;


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
                for (int i = 0; i < keys.size(); i++) {
                    String key = keys.get(i);
                    if (key.equals(letter)) {
                        citiesExList.setSelectedGroup(i);
                    }
                }
            }
        });


    }

    private void setLocationClient() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                String city = bdLocation.getCity();
                cityName = city.substring(0, city.length() - 1);
                tvCityName.setText(cityName);

                double latNum = bdLocation.getLatitude();
                double lotNum = bdLocation.getLongitude();
                lat = String.valueOf(latNum);
                lot = String.valueOf(lotNum);
                mLocationClient.stop();
            }
        });
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(false);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(false);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
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
        keys.add("定位");
        for (int i = 0; i < result.size(); i++) {
            keys.add(result.get(i).getBegin_key());
            String beginKey = result.get(i).getBegin_key();
            List<CitiesBean.ResultBean.CityListBean> city_list = result.get(i).getCity_list();
            mapDatas.put(beginKey, city_list);
        }


        citiesAdapter.notifyDataSetChanged();
        for (int i = 0; i < keys.size(); i++) {
            citiesExList.expandGroup(i);
        }

        citiesExList.setOnHeaderUpdateListener(new PinnedHeaderExpandableListView.OnHeaderUpdateListener() {
            @Override
            public View getPinnedHeader() {
                TextView textView = new TextView(CitiesActivity.this);
                LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setPadding(10, 10, 10, 10);
                textView.setLayoutParams(params);
                textView.setTextSize(14);
                textView.setTextColor(Color.BLUE);
                textView.setBackgroundColor(Color.parseColor("#c1bdbd"));
                return textView;
            }

            @Override
            public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
                String content = keys.get(firstVisibleGroupPos);
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
    public void onClick1() {
        finish();
    }




    class CitiesAdapter extends BaseExpandableListAdapter {
        private Map<String, List<CitiesBean.ResultBean.CityListBean>> mapDatas;
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
            View view=LayoutInflater.from(CitiesActivity.this).inflate(R.layout.city_group_text,null);

            TextView textView = (TextView) view.findViewById(R.id.city_group_text);

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
                ImageView imageView =(ImageView) view.findViewById(R.id.item_cities_refresh_iv);
             tvCityName = (TextView) view.findViewById(R.id.item_cities_location_tv);
                tvCityName.setText("");
                setLocationClient();
                mLocationClient.start();
                tvCityName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra("cityName", cityName);
                        CitiesActivity.this.setResult(1, intent);
                        CitiesActivity.this.finish();
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   view.startAnimation(AnimationUtils.loadAnimation(CitiesActivity.this, R.anim.refresh_loction_btn));
                   tvCityName.setText("正在定位中...");
                   mLocationClient.start();
               }
           });
                return view;
            }
            if (groupPosition == 1) {
                viewGv = (MyGridView) LayoutInflater.from(CitiesActivity.this).inflate(R.layout.item_cities_hot, null, false);
                String key = keys.get(groupPosition);
                List<CitiesBean.ResultBean.CityListBean> cityListBeen = mapDatas.get(key);
                CitiesGridAdapter adapter = new CitiesGridAdapter(CitiesActivity.this, cityListBeen);
                viewGv.setAdapter(adapter);




                return viewGv;
            }
            childView = LayoutInflater.from(CitiesActivity.this).inflate(R.layout.item_cities_child, null, false);

            TextView textView = (TextView) childView.findViewById(R.id.item_cities_child_tv);
          final   String city_name = mapDatas.get(keys.get(groupPosition)).get(childPosition).getCity_name();
            Log.i("east", "getChildView: " + city_name);
            textView.setText(city_name);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("cityName", city_name);
                    CitiesActivity.this.setResult(1, intent);
                    CitiesActivity.this.finish();
                }
            });
            return childView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }


}
