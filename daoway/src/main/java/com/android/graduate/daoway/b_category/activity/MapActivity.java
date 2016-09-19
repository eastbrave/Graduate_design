package com.android.graduate.daoway.b_category.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.g_location.CitiesActivity;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.XiaoQuBean;
import com.android.graduate.daoway.z_constant.PlotListAdapter;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends BaseActivity {

    @BindView(R.id.map_view)
    TextureMapView map;
    @BindView(R.id.map_city)
    TextView city;
    @BindView(R.id.map_text_myshow)
    TextView mapTextMyshow;
    @BindView(R.id.map_search)
    TextView search;
    @BindView(R.id.map_cancel)
    TextView cancel;
    @BindView(R.id.map_list)
    ListView mapList;
    private BaiduMap mBaiduMap;
    private Marker marker;
    private LocationClient mLocationClient;
    private double latNum;
    private double lotNum;
    private String cityName;
    private PlotListAdapter mAdapter;
    private List<XiaoQuBean.DataBean.CommunitiesBean> communitie = new ArrayList<>();
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        initLocation();
        setLocationClient();
        setlistener();
        mLocationClient.start();
        sharedPreferences = getSharedPreferences("location", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void setAdapter() {
        mAdapter = new PlotListAdapter(this,R.layout.nearby_plot_lv_item,communitie);
        mapList.setAdapter(mAdapter);
    }

    private void initData(double latNum, double lotNum) {
        this.latNum = latNum;
        this.lotNum = lotNum;
        HttpUtils.init().getCommunities(latNum, lotNum).enqueue(new Callback<XiaoQuBean>() {
            @Override
            public void onResponse(Call<XiaoQuBean> call, Response<XiaoQuBean> response) {
                XiaoQuBean.DataBean data = response.body().getData();
                cityName = data.getParent().getCity();
                communitie = data.getCommunities();
                int cityID = data.getParent().getId();
                List<XiaoQuBean.DataBean.BaiduCommunitiesBean> baiduCommunities =
                        data.getBaiduCommunities();
                //将baiduCommunitiesBean转换成communitiesBean,并加入数据列表
                for (int i = 0; i < baiduCommunities.size(); i++) {
                    XiaoQuBean.DataBean.BaiduCommunitiesBean baiduCommunitiesBean = baiduCommunities.get(i);
                    XiaoQuBean.DataBean.CommunitiesBean communitiesBean = new XiaoQuBean.DataBean.CommunitiesBean();
                    communitiesBean.setAddr(baiduCommunitiesBean.getAddr());
                    communitiesBean.setName(baiduCommunitiesBean.getName());
                    communitiesBean.setLot(baiduCommunitiesBean.getX());
                    communitiesBean.setLat(baiduCommunitiesBean.getY());
                    communitiesBean.setId(cityID);
                    communitie.add(communitiesBean);

                }
                    setAdapter();
                }


            @Override
            public void onFailure(Call<XiaoQuBean> call, Throwable t) {

            }
        });

    }

    private void setlistener() {
        setMapListener();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapActivity.this.finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, PlotSearchActivity.class);
                intent.putExtra("cityName", cityName);
                startActivityForResult(intent, 2);
            }
        });

        mapList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XiaoQuBean.DataBean.CommunitiesBean communitiesBean = communitie.get(position);
                editor.putString("plot", communitiesBean.getName());
                editor.putString("cityName", cityName);
                editor.putString("lot", String.valueOf(communitiesBean.getLot()));
                editor.putString("lat",String.valueOf(communitiesBean.getLat()));
                editor.putString("id", String.valueOf(communitiesBean.getId()));
                editor.commit();
                setResult(1);
                finish();
            }
        });
    }

    private void setMapListener() {
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                LatLng target = mapStatus.target;
                latNum=target.latitude;
                lotNum=target.longitude;
                marker.setPosition(target);
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                LatLng target = mapStatus.target;
                latNum=target.latitude;
                lotNum=target.longitude;
                initData( latNum,lotNum);
            }
        });

    }

    private void initLocation() {
        LatLng point = new LatLng(0, 0);
        mBaiduMap = map.getMap();
        //取消缩放按钮
        map.showZoomControls(false);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.map_state_edit_icon);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        marker = (Marker)(mBaiduMap.addOverlay(option));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == 1) {
                    String cityName = data.getStringExtra("cityName");
                    Log.d("pidan", "onActivityResult: "+cityName);
                    this.cityName=cityName;
                    MapActivity.this.city.setText(this.cityName);
                    LatLng point = new LatLng(latNum, lotNum);
                    MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(point, 16);
                    mBaiduMap.setMapStatus(msu);
                }
                break;
            case 2:
                if (resultCode == 1) {
                    setResult(1);
                    finish();
                }
                break;

        }
    }

    private void setLocationClient() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                latNum = bdLocation.getLatitude();
                lotNum = bdLocation.getLongitude();
                String city = bdLocation.getCity();
                cityName = city.substring(0, city.length() - 1);
                MapActivity.this.city.setText(cityName);
                LatLng point = new LatLng(latNum, lotNum);
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(point, 16);
                mBaiduMap.setMapStatus(msu);
//                lat=String.valueOf(latNum);
//                lot=String.valueOf(lotNum);
                initData( latNum,lotNum);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        map.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.map_text_myshow)
    public void onClick1() {
        mLocationClient.start();
    }

    @OnClick(R.id.map_city)
    public void onClick2() {
        Intent intent = new Intent(MapActivity.this, CitiesActivity.class);
        startActivityForResult(intent, 1);
    }
}
