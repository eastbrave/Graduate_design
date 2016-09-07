package com.android.graduate.daoway.a_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.bean.BusinessBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6.
 * 首页最底部，推荐商家列表的适配器
 */
public class BusinessAdapter extends BaseAdapter {

    private Context mContext;
    private List<BusinessBean.DataBean> businessDatas;

    public BusinessAdapter(Context mContext, List<BusinessBean.DataBean> businessDatas) {
        this.mContext = mContext;
        this.businessDatas = businessDatas;
    }


    @Override
    public int getCount() {
        return businessDatas == null ? 0 : businessDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return businessDatas.get(i);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_business, parent, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvName.setText(businessDatas.get(position).getTitle());
        viewHolder.tvNum.setText("已接"+businessDatas.get(position).getCompleteOrderNum()+"单");
        double star=businessDatas.get(position).getStar();
        String rate=(int)(star/5*100)+"%";
        viewHolder.tvNum.setText("好评"+rate);
        Picasso.with(mContext).load(businessDatas.get(position).getImgUrl()).into(viewHolder.imageIv);

        return view;
    }

    class ViewHolder {
        @BindView(R.id.item_home_business_name_tv)
        TextView tvName;
        @BindView(R.id.item_home_business_num_tv)
        TextView tvNum;
        @BindView(R.id.item_home_business_rate_tv)
        TextView tvRate;
        @BindView(R.id.item_home_business_image)
        ImageView imageIv;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }


}
