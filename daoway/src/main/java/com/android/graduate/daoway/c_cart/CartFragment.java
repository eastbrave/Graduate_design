package com.android.graduate.daoway.c_cart;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.graduate.daoway.Carts;
import com.android.graduate.daoway.CartsDao;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.User;
import com.android.graduate.daoway.UserDao;
import com.android.graduate.daoway.h_login_and_register.LoginActivity;
import com.android.graduate.daoway.z_db.DBUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/5.
 */
public class CartFragment extends Fragment {

    @BindView(R.id.cart_lv)
    ListView cartLv;
    private Context mContext;
    private SharedPreferences sp;
    List<String> keys=new ArrayList<>();
    Map<String,List<Carts>> mapDatas=new HashMap<>();
    private CartAdapter cartAdapter;

    public static CartFragment newInstance(Bundle bundle) {
        CartFragment fragment = new CartFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO: 2016/9/5
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        initData();//获取后台数据库信息
        initAdapter();

        initListener();

        return view;
    }

    private void initListener() {

    }

    public void refresh(){
        mapDatas.clear();
        keys.clear();
        initData();

       // cartAdapter.notifyDataSetChanged();
        CartAdapter  cartAdapter = new CartAdapter(mContext,keys,mapDatas);
        cartLv.setAdapter(cartAdapter);
    }

    private void initData() {
        sp=mContext.getSharedPreferences("isLogin",Context.MODE_PRIVATE);
        boolean login_key = sp.getBoolean("login_key", false);
        if(!login_key){
            return;
        }
        String phone=sp.getString("userName",null);
        //判断是已经登陆状态之后再操作数据库
        /*UserDao userDao = DBUtils.getUserDao(mContext);
        QueryBuilder<User> builder = userDao.queryBuilder();
        //设置用户查询条件
        builder.where(UserDao.Properties.Phone.eq(phone));
        List<User> list = builder.list();
        if(list==null){
            return;
        }
        User user = list.get(0);
        List<Carts> carts = user.getCarts();*/
        CartsDao cartsDao = DBUtils.getCartsDao(mContext);
        QueryBuilder<Carts> builder = cartsDao.queryBuilder();
        List<Carts> carts =builder .list();
        //整理购物车信息
        Log.i("east","购物车信息有多少条："+carts.size());

        for (int i = 0; i <carts.size() ; i++) {
            String shopName = carts.get(i).getShopName();

            if(!mapDatas.containsKey(shopName)) {
                //不存在的店铺名
                keys.add(shopName);
                CartsDao cartsDao2 = DBUtils.getCartsDao(mContext);
                QueryBuilder<Carts> builder2 = cartsDao2.queryBuilder();
                builder2.where(CartsDao.Properties.ShopName.eq(shopName));
                List<Carts> list = builder2.list();
                mapDatas.put(shopName, list);
            }
        }



              /*  ItemInfo itemInfo=new ItemInfo();
                itemInfo.setSkuNum(Integer.parseInt(carts.get(i).getSkuNum()));
                itemInfo.setPrice(Integer.parseInt(carts.get(i).getPrice()));
                itemInfo.setSkuName(carts.get(i).getSkuName());
                itemInfo.setImgUrl(carts.get(i).getImgUrl());
                itemList.add(itemInfo);
            else
            //已经存在的店铺名，增加list内容就行
                ItemInfo itemInfo=new ItemInfo();
                itemInfo.setSkuNum(Integer.parseInt(carts.get(i).getSkuNum()));
                itemInfo.setPrice(Integer.parseInt(carts.get(i).getPrice()));
                itemInfo.setSkuName(carts.get(i).getSkuName());
                itemInfo.setImgUrl(carts.get(i).getImgUrl());
                itemList.add(itemInfo);*/



    }

    private void initAdapter() {
        cartAdapter = new CartAdapter(mContext,keys,mapDatas);
        cartLv.setAdapter(cartAdapter);
    }


}
