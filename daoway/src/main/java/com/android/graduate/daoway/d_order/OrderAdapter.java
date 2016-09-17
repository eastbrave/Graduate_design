package com.android.graduate.daoway.d_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.graduate.daoway.Orders;
import com.android.graduate.daoway.OrdersDao;
import com.android.graduate.daoway.Orders;
import com.android.graduate.daoway.OrdersDao;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.c_cart.ItemCartAdapter;
import com.android.graduate.daoway.c_cart.ItemInfo;
import com.android.graduate.daoway.widget.MyListView;
import com.android.graduate.daoway.z_db.DBUtils;

import org.greenrobot.greendao.query.QueryBuilder;

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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_cart, parent, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //设置店铺名
        final int cur=position;
        final String orderTime=keys.get(position);
        String shopName = mapDatas.get(keys.get(0)).get(0).getShopName();
        viewHolder.shopNameTv.setText(shopName);
        //设置内部list
        List<Orders> itemInfos = mapDatas.get(keys.get(position));
        ItemOrderAdapter itemCartAdapter = new ItemOrderAdapter(mContext,itemInfos);
        viewHolder.itemCartLv.setAdapter(itemCartAdapter);


        //设置总金额
        double total=0;
        for (int i = 0; i < itemInfos.size(); i++) {
            long skuNum =Long.parseLong(itemInfos.get(i).getSkuNum()) ;
            double price = Double.parseDouble(itemInfos.get(i).getPrice());
            total+=skuNum*price;
        }
        viewHolder.shopTotalTv.setText(total+"");




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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);

        }
    }
}
