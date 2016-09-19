package com.android.graduate.daoway.c_cart;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.graduate.daoway.z_db.Carts;
import com.android.graduate.daoway.z_db.CartsDao;
import com.android.graduate.daoway.R;
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




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();//获取后台数据库信息
        initAdapter();
    }

/*    public void refresh(){
        mapDatas.clear();
        keys.clear();
        initData();
        CartAdapter  cartAdapter = new CartAdapter(mContext,keys,mapDatas);
        cartLv.setAdapter(cartAdapter);
    }*/

    private void initData() {
        sp=mContext.getSharedPreferences("isLogin",Context.MODE_PRIVATE);
        boolean login_key = sp.getBoolean("login_key", false);
        if(!login_key){
            return;
        }
        mapDatas.clear();
        keys.clear();
        CartsDao cartsDao = DBUtils.getCartsDao(mContext);
        QueryBuilder<Carts> builder = cartsDao.queryBuilder();
        List<Carts> carts =builder .list();
        //整理购物车信息


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






    }

    private void initAdapter() {
        cartAdapter = new CartAdapter(mContext,keys,mapDatas);
        cartLv.setAdapter(cartAdapter);
    }


}
