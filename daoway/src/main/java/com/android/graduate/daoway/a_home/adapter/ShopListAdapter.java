package com.android.graduate.daoway.a_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.bean.ShopBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/9.
 */
public class ShopListAdapter extends BaseAdapter {

    private Context mContext;
    private List<ShopBean.DataBean.PricesBean> mPriceDatas;
    private TextView cartNumTv;

    public ShopListAdapter(Context mContext, List<ShopBean.DataBean.PricesBean> mPriceDatas,TextView cartNumTv) {
        this.mContext = mContext;
        this.mPriceDatas = mPriceDatas;
        this.cartNumTv=cartNumTv;
    }

    @Override
    public int getCount() {
        return mPriceDatas == null ? 0 : mPriceDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_menu_list, parent, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Picasso.with(mContext).load(mPriceDatas.get(position).getPic_url()).into(viewHolder.imageIv);
        viewHolder.tvName.setText(mPriceDatas.get(position).getName());
        viewHolder.itemShopListDescTv.setText(mPriceDatas.get(position).getDescription());
        viewHolder.priceTv.setText(mPriceDatas.get(position).getPrice()+"");
        viewHolder.priceUnitTv.setText(mPriceDatas.get(position).getPrice_unit());
        viewHolder.priceOriginalTv.setText(mPriceDatas.get(position).getOriginalPrice()+"元");
        viewHolder.saleNumTv.setText("已售"+mPriceDatas.get(position).getSalesNum());
        // TODO: 2016/9/9   对减号和已选数量设置存入数据库
        final ViewHolder mViewHolder=viewHolder;
        //减号按钮和选中数量  初始的时候是消失状态

        mViewHolder.minBuyNum=Integer.parseInt(mPriceDatas.get(position).getMinBuyNum());
        mViewHolder.selectNumTv.setVisibility(View.GONE);
        mViewHolder.reduceIv.setVisibility(View.GONE);
        mViewHolder.addIv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewHolder.num+=mViewHolder.minBuyNum;
                mViewHolder.selectNumTv.setVisibility(View.VISIBLE);
                mViewHolder.reduceIv.setVisibility(View.VISIBLE);
                mViewHolder.selectNumTv.setText(mViewHolder.num+"");
                //修改数据库数据，并刷新设置

            }
        });

        mViewHolder.reduceIv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewHolder.num-=mViewHolder.minBuyNum;
                mViewHolder.selectNumTv.setText(mViewHolder.num+"");
                //修改数据库数据并刷新设置
                if(mViewHolder.num==0){
                    mViewHolder.selectNumTv.setVisibility(View.GONE);
                    mViewHolder.reduceIv.setVisibility(View.GONE);

                }
            }
        });


        return view;
    }


    class ViewHolder {
        @BindView(R.id.item_shop_list_icon_iv)
        ImageView imageIv;
        @BindView(R.id.item_shop_list_name_tv)
        TextView tvName;
        @BindView(R.id.item_shop_list_reduce_iv)
        ImageView reduceIv;
        @BindView(R.id.item_shop_list_select_num_tv)
        TextView selectNumTv;
        @BindView(R.id.item_shop_list_add_iv)
        ImageView addIv;
        @BindView(R.id.item_shop_list_price_tv)
        TextView priceTv;
        @BindView(R.id.item_shop_list_price_unit_tv)
        TextView priceUnitTv;
        @BindView(R.id.item_shop_list_price_original_tv)
        TextView priceOriginalTv;
        @BindView(R.id.item_shop_list_sale_num_tv)
        TextView saleNumTv;
        @BindView(R.id.item_shop_list_desc_tv)
        TextView itemShopListDescTv;
        int num;
        int minBuyNum;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
