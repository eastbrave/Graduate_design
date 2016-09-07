package com.android.graduate.daoway.a_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.bean.HomeBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6.
 * mList里面嵌套的GridView的适配器
 */
public class ItemGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<HomeBean.DataBean.ItemsBean> itemsDatas;

    public ItemGridAdapter(Context mContext, List<HomeBean.DataBean.ItemsBean> itemsDatas) {
        this.mContext = mContext;
        this.itemsDatas = itemsDatas;
    }

    @Override
    public int getCount() {
        return itemsDatas == null ? 0 : itemsDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return itemsDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder=null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_grid, parent, false);
            viewHolder=new ViewHolder(view);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.tvName.setText(itemsDatas.get(position).getName());
        viewHolder.tvPrice.setText(itemsDatas.get(position).getPrice() + "");
        viewHolder.tvPriceType.setText(itemsDatas.get(position).getPrice_unit());
        viewHolder.tvBusiness.setText(itemsDatas.get(position).getServiceTitle());
        Picasso.with(mContext).load(itemsDatas.get(position).getPic_url()).into( viewHolder.imageIv);

        return view;
    }


     class ViewHolder {
         @BindView(R.id.item_home_gridView_image)
         ImageView imageIv;
         @BindView(R.id.item_home_gridView_name_tv)
         TextView tvName;
         @BindView(R.id.item_home_gridView_price_tv)
         TextView tvPrice;
         @BindView(R.id.item_home_gridView_price_type_tv)
         TextView tvPriceType;
         @BindView(R.id.item_home_gridView_business_tv)
         TextView tvBusiness;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
