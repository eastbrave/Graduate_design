package com.android.graduate.daoway.d_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.Orders;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.c_cart.ItemInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6.
 * mList里面嵌套的GridView的适配器
 */
public class ItemOrderAdapter extends BaseAdapter {

    private Context mContext;
    private List<Orders> itemInfos;

    public ItemOrderAdapter(Context mContext, List<Orders> itemInfos) {
        this.mContext = mContext;
        this.itemInfos = itemInfos;
    }

    @Override
    public int getCount() {
        return itemInfos == null ? 0 : itemInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return itemInfos.get(i);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_cart_content, parent, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.with(mContext).load(itemInfos.get(position).getImgUrl()).into(viewHolder.icon);
        viewHolder.nameTv.setText(itemInfos.get(position).getSkuName()+"");
        viewHolder.priceTv.setText(itemInfos.get(position).getPrice()+"");
        viewHolder.selectNumTv.setText(itemInfos.get(position).getSkuNum()+"");

        // TODO: 2016/9/14  加减数量监听


        return view;
    }


    class ViewHolder {
        @BindView(R.id.cart_item_content_shop_check)
        CheckBox itemCheck;
        @BindView(R.id.cart_item_content_shop_icon)
        ImageView icon;
        @BindView(R.id.cart_item_content_shop_name_tv)
        TextView nameTv;
        @BindView(R.id.cart_item_content_shop_price_tv)
        TextView priceTv;
        @BindView(R.id.cart_item_content_shop_list_reduce_iv)
        ImageView reduceIv;
        @BindView(R.id.cart_item_content_shop_list_select_num_tv)
        TextView selectNumTv;
        @BindView(R.id.cart_item_content_shop_list_add_iv)
        ImageView addIv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
