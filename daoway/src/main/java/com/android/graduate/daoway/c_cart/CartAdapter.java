package com.android.graduate.daoway.c_cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.graduate.daoway.Carts;
import com.android.graduate.daoway.CartsDao;
import com.android.graduate.daoway.Orders;
import com.android.graduate.daoway.OrdersDao;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.adapter.ItemGridAdapter;
import com.android.graduate.daoway.widget.MyListView;
import com.android.graduate.daoway.z_db.DBUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6.
 * mList里面嵌套的GridView的适配器
 */
public class CartAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> keys = new ArrayList<>();
    Map<String, List<Carts>> mapDatas = new HashMap<>();

    public CartAdapter(Context mContext, List<String> keys, Map<String, List<Carts>> mapDatas) {
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
        final String key=keys.get(position);
        viewHolder.shopNameTv.setText(key);
        //设置内部list
        List<Carts> itemInfos = mapDatas.get(keys.get(position));
        ItemCartAdapter itemCartAdapter = new ItemCartAdapter(mContext,itemInfos);
        viewHolder.itemCartLv.setAdapter(itemCartAdapter);


        //设置总金额
        double total=0;
        for (int i = 0; i < itemInfos.size(); i++) {
            long skuNum = Long.parseLong(itemInfos.get(i).getSkuNum());
            double price = Double.parseDouble( itemInfos.get(i).getPrice());
            total+=skuNum*price;
        }
        viewHolder.shopTotalTv.setText(total+"");

        //点击提交
        viewHolder.payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到表单页面
                // TODO: 2016/9/14  跳转到表单页面
                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String orderTime = formatter.format(curDate);

                CartsDao cartsDao = DBUtils.getCartsDao(mContext);
                QueryBuilder<Carts> builder = cartsDao.queryBuilder();
                builder.where(CartsDao.Properties.ShopName.eq(key));
                List<Carts> list = builder.list();
                OrdersDao ordersDao = DBUtils.getOrdersDao(mContext);
                for (int i = 0; i < list.size(); i++) {
                    String skuName = list.get(i).getSkuName();
                    String price = list.get(i).getPrice();
                    String skuNum = list.get(i).getSkuNum();
                    String imgUrl = list.get(i).getImgUrl();
                    Orders orders=new Orders();
                    orders.setSkuName(skuName);
                    orders.setSkuNum(skuNum);
                    orders.setPrice(price);
                    orders.setShopName(key);
                    orders.setOrderTime(orderTime);
                    orders.setImgUrl(imgUrl);

                    ordersDao.insert(orders);
                }
            }
        });
        //删除

        viewHolder.deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //移除view，并清除数据库中该条数据

                CartsDao cartsDao = DBUtils.getCartsDao(mContext);
               cartsDao.deleteInTx(cartsDao.queryBuilder().where(CartsDao.Properties.ShopName.eq(key)).list());
                mapDatas.remove(keys.get(cur));
                keys.remove(cur);
                CartAdapter.this.notifyDataSetChanged();


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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);

        }
    }
}
