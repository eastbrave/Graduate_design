package com.android.graduate.daoway.d_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.graduate.daoway.z_db.Orders;
import com.android.graduate.daoway.z_db.OrdersDao;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.widget.MyListView;
import com.android.graduate.daoway.z_db.DBUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6.
 * mList里面嵌套的GridView的适配器
 */
public class OrderAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> keys = new ArrayList<>();
    Map<String, List<Orders>> mapDatas = new HashMap<>();

    public OrderAdapter(Context mContext, List<String> keys, Map<String, List<Orders>> mapDatas) {
        this.mContext = mContext;
        this.mapDatas = mapDatas;
        this.keys = keys;
    }

    @Override
    public int getCount() {
        return keys == null ? 0 : keys.size();
    }

    @Override
    public Object getItem(int i) {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //设置店铺名
        final int cur = position;
        final String orderTime = keys.get(position);
        //// TODO: 2016/9/17 设置店铺名
        OrdersDao ordersDao = DBUtils.getOrdersDao(mContext);
        List<Orders> list = ordersDao.queryBuilder().where(OrdersDao.Properties.OrderTime.eq(orderTime)).list();
        if (list.size() != 0) {
            Orders orders = list.get(0);
            String shopName = orders.getShopName();
            viewHolder.shopNameTv.setText(shopName);
        }

        //订单日期
        viewHolder.orderTimeTv.setText(orderTime);


        //设置内部list
        List<Orders> itemInfos = mapDatas.get(keys.get(position));
        ItemOrderAdapter itemCartAdapter = new ItemOrderAdapter(mContext, itemInfos);
        viewHolder.itemCartLv.setAdapter(itemCartAdapter);


        //设置总金额和商品总数
        double total = 0;
        long totalNum = 0;
        for (int i = 0; i < itemInfos.size(); i++) {
            long skuNum = Long.parseLong(itemInfos.get(i).getSkuNum());
            totalNum += skuNum;
            double price = Double.parseDouble(itemInfos.get(i).getPrice());
            total += skuNum * price;
        }
        viewHolder.shopTotalTv.setText(total + "");
        viewHolder.orderNumTv.setText("共"+totalNum+"件商品");
        viewHolder.deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrdersDao ordersDao = DBUtils.getOrdersDao(mContext);
                ordersDao.deleteInTx(ordersDao.queryBuilder().where(OrdersDao.Properties.OrderTime.eq(orderTime)).list());
                mapDatas.remove(keys.get(cur));
                keys.remove(cur);
                OrderAdapter.this.notifyDataSetChanged();
            }
        });


        return view;
    }




     class ViewHolder {
        @BindView(R.id.cart_item_top_shop_check)
        CheckBox shopCheck;
        @BindView(R.id.cart_item_top_shop_name_tv)
        TextView shopNameTv;
        @BindView(R.id.cart_item_top_shop_delete_tv)
        TextView deleteTv;
        @BindView(R.id.item_cart_lv)
        MyListView itemCartLv;
        @BindView(R.id.cart_item_bottom_shop_total_tv)
        TextView shopTotalTv;
        @BindView(R.id.cart_item_bottom_pay_btn)
        Button payBtn;
        @BindView(R.id.order_time_tv)
        TextView orderTimeTv;
         @BindView(R.id.order_num_tv)
         TextView orderNumTv;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);

        }
    }



}
