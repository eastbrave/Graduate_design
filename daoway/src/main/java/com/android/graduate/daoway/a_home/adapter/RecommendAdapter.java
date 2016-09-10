package com.android.graduate.daoway.a_home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.bean.RecomBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6.
 * 首页第四栏 今日推荐的适配器
 */
public class RecommendAdapter extends BaseAdapter {
    private Context mContext;
    private List<RecomBean.DataBean.RecommendBean> rcDatas;

    public RecommendAdapter(Context mContext, List<RecomBean.DataBean.RecommendBean> rcDatas) {
        this.mContext = mContext;
        this.rcDatas = rcDatas;
    }

    @Override
    public int getCount() {
        return rcDatas==null ? 0 : rcDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return rcDatas.get(i);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_recommend, parent, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        switch (position){
            case 0:
                view.setBackgroundColor(mContext.getResources().getColor(R.color.color_recommend_one));
                viewHolder.tvName.setTextColor(mContext.getResources().getColor(R.color.color_recommend_title_one));
                break;
            case 1:
                view.setBackgroundColor(mContext.getResources().getColor(R.color.color_recommend_two));
                viewHolder.tvName.setTextColor(mContext.getResources().getColor(R.color.color_recommend_title_two));
                break;
            case 2:
                view.setBackgroundColor(mContext.getResources().getColor(R.color.color_recommend_three));
                viewHolder.tvName.setTextColor(mContext.getResources().getColor(R.color.color_recommend_title_three));
                break;
            case 3:
                view.setBackgroundColor(mContext.getResources().getColor(R.color.color_recommend_four));
                viewHolder.tvName.setTextColor(mContext.getResources().getColor(R.color.color_recommend_title_four));
                break;
        }
        viewHolder.tvName.setText(rcDatas.get(position).getTitle());
        viewHolder.tvSubject.setText(rcDatas.get(position).getSubject());
        Picasso.with(mContext).load(rcDatas.get(position).getImg()).into(viewHolder.imageIv);
        return view;
    }


    class ViewHolder {
        @BindView(R.id.item_home_recommend_name_tv)
        TextView tvName;
        @BindView(R.id.item_home_recommend_subject_tv)
        TextView tvSubject;
        @BindView(R.id.item_home_recommend_image)
        ImageView imageIv;

       public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
