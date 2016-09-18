package com.android.graduate.daoway.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.graduate.daoway.Carts;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.User;
import com.android.graduate.daoway.UserDao;
import com.android.graduate.daoway.a_home.HomeFragment;
import com.android.graduate.daoway.b_category.CategoryFragment;
import com.android.graduate.daoway.c_cart.CartFragment;
import com.android.graduate.daoway.d_order.OrderFragment;
import com.android.graduate.daoway.e_mine.MineFragment;
import com.android.graduate.daoway.f_search.SearchActivity;
import com.android.graduate.daoway.h_login_and_register.LoginActivity;
import com.android.graduate.daoway.start.MapActivity;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.XiaoQuBean;
import com.android.graduate.daoway.z_db.DBUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private boolean isLogin;
    private SharedPreferences sharedPreferences;
    private String village;
    public static int total;
    private LocationClient mLocationClient;
    private String city;
    private String cityName;


    @Override
    protected void onStart() {
        super.onStart();
        initData();


            sp=getSharedPreferences("isLogin",MODE_PRIVATE);
            isLogin = sp.getBoolean("login_key", false);
            if(isLogin){
                //如果是登陆状态，就查询数据库，并更新UI
                String phone=sp.getString("userName",null);
                //判断是已经登陆状态之后再操作数据库
                UserDao userDao = DBUtils.getUserDao(this);
                QueryBuilder<User> builder = userDao.queryBuilder();
                //设置用户查询条件
                builder.where(UserDao.Properties.Phone.eq(phone));
                List<User> list = builder.list();
                User user = list.get(0);
                List<Carts> carts = user.getCarts();
                for (int i = 0; i < carts.size(); i++) {
                    String skuNum = carts.get(i).getSkuNum();
                    int num = Integer.parseInt(skuNum);
                    total+=num;
                }


          /*  if(total==0){
                cartNumTv.setVisibility(View.GONE);
            }else {
                cartNumTv.setVisibility(View.VISIBLE);
                cartNumTv.setText(""+total);
            }*/

            }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initRadioArray();
        initListener();
       initLocation();

    }

    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                city = bdLocation.getCity();
                double latNum = bdLocation.getLatitude();
                double lotNum = bdLocation.getLongitude();
                String lat = String.valueOf(latNum);
                String lot = String.valueOf(lotNum);
                mLocationClient.stop();
                HttpUtils.init().getCommunities(latNum,lotNum).enqueue(new Callback<XiaoQuBean>() {
                    @Override
                    public void onResponse(Call<XiaoQuBean> call, Response<XiaoQuBean> response) {
                        XiaoQuBean.DataBean data = response.body().getData();

                        List<XiaoQuBean.DataBean.CommunitiesBean> communities = data.getCommunities();

                        List<XiaoQuBean.DataBean.CommunitiesBean> communitie = new ArrayList<>();
                        communitie.addAll(communities);
                        int cityID=0;
                        try{
                            cityID= data.getParent().getId();
                        }catch (Exception e){
                            e.printStackTrace();
                            return;
                        }

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

                        SharedPreferences location = getSharedPreferences("location", Context.MODE_PRIVATE);
                        String plot = location.getString("plot", "");

                        for (int i = 0; i < communitie.size(); i++) {
                            if (plot.equals(communitie.get(i).getName())) {
                                return;
                            }
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("当前小区不在您的位置附近 ,是否重新选择小区?")
                                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setClass(MainActivity.this, MapActivity.class);
                                        startActivityForResult(intent, 3);
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.show();
                    }

                    @Override
                    public void onFailure(Call<XiaoQuBean> call, Throwable t) {

                    }
                });
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
        mLocationClient.start();

        
    }


    private void initData() {


        sharedPreferences = getSharedPreferences("location", Context.MODE_PRIVATE);
        village = sharedPreferences.getString("plot", "武汉");
        locationTv.setText(village);
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
                                //如果未登录，就跳转到登陆页面
                                if(!isLogin){
                                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(intent);

                                }



                                break;
                            case R.id.order_rb:
                                locationTv.setVisibility(View.GONE);
                                titleTv.setVisibility(View.VISIBLE);
                                titleTv.setText("订单");
                                actionDivider.setVisibility(View.INVISIBLE);
                                searchIv.setVisibility(View.GONE);
                                //如果未登录，就跳转到登陆页面
                                if(!isLogin){
                                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(intent);

                                }
                                break;
                            case R.id.mine_rb:
                                locationTv.setVisibility(View.GONE);
                                titleTv.setVisibility(View.VISIBLE);
                                titleTv.setText("我的");
                                actionDivider.setVisibility(View.INVISIBLE);
                                searchIv.setVisibility(View.GONE);
                                break;
                        }
                        //切换fragment页面
                        initSwitch(i);
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

  public void onClick(View view){
      Intent intent=new Intent(MainActivity.this, MapActivity.class);
      startActivity(intent);
  }


    /*
    * 对于返回结果的处理
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3 && resultCode == 1) {
            initData();
        }


    }

}
