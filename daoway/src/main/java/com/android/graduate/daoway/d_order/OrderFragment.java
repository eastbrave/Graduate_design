package com.android.graduate.daoway.d_order;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.graduate.daoway.z_db.Orders;
import com.android.graduate.daoway.z_db.OrdersDao;
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
public class OrderFragment extends Fragment {

    @BindView(R.id.order_lv)
    ListView orderLv;
    private Context mContext;
    private SharedPreferences sp;
    List<String> keys = new ArrayList<>();
    Map<String, List<Orders>> mapDatas = new HashMap<>();
    private OrderAdapter orderAdapter;


    public static OrderFragment newInstance(Bundle bundle) {
        OrderFragment fragment = new OrderFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        orderAdapter = new OrderAdapter(mContext,keys,mapDatas);
        orderLv.setAdapter(orderAdapter);
    }


    private void initData() {
        sp = mContext.getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        boolean login_key = sp.getBoolean("login_key", false);
        if (!login_key) {
            return;
        }


        OrdersDao ordersDao = DBUtils.getOrdersDao(mContext);
        QueryBuilder<Orders> builder = ordersDao.queryBuilder();
        List<Orders> ordersList = builder.list();
        //整理购物车信息

        for (int i = 0; i < ordersList.size(); i++) {
            String orderTime = ordersList.get(i).getOrderTime();
            if (!mapDatas.containsKey(orderTime)) {
                //不存在的店铺名
                keys.add(orderTime);
                QueryBuilder<Orders> builder2 = ordersDao.queryBuilder();
                List<Orders> list = builder2.where(OrdersDao.Properties.OrderTime.eq(orderTime)).list();
                mapDatas.put(orderTime, list);
            }
        }

    }




}
