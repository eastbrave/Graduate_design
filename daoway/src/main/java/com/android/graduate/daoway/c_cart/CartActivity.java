package com.android.graduate.daoway.c_cart;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.android.graduate.daoway.Carts;
import com.android.graduate.daoway.CartsDao;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.z_db.DBUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/17.
 */
public class CartActivity extends BaseActivity {

    @BindView(R.id.cart_lv)
    ListView cartLv;

    private SharedPreferences sp;
    List<String> keys=new ArrayList<>();
    Map<String,List<Carts>> mapDatas=new HashMap<>();
    private CartAdapter cartAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();//获取后台数据库信息
        initAdapter();
    }

    private void initData() {
        sp=getSharedPreferences("isLogin",Context.MODE_PRIVATE);
        boolean login_key = sp.getBoolean("login_key", false);
        if(!login_key){
            return;
        }
        mapDatas.clear();
        keys.clear();
        CartsDao cartsDao = DBUtils.getCartsDao(this);
        QueryBuilder<Carts> builder = cartsDao.queryBuilder();
        List<Carts> carts =builder .list();
        //整理购物车信息
        for (int i = 0; i <carts.size() ; i++) {
            String shopName = carts.get(i).getShopName();

            if(!mapDatas.containsKey(shopName)) {
                //不存在的店铺名
                keys.add(shopName);
                CartsDao cartsDao2 = DBUtils.getCartsDao(this);
                QueryBuilder<Carts> builder2 = cartsDao2.queryBuilder();
                builder2.where(CartsDao.Properties.ShopName.eq(shopName));
                List<Carts> list = builder2.list();
                mapDatas.put(shopName, list);
            }
        }


    }

    private void initAdapter() {
        cartAdapter = new CartAdapter(this,keys,mapDatas);
        cartLv.setAdapter(cartAdapter);
    }



}
