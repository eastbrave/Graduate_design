package com.android.graduate.daoway.a_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.bean.HomeBean;
import com.android.graduate.daoway.widget.MyGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6.
 * mList的适配器
 */
public class HomeAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeBean.DataBean> homeDatas;

    public HomeAdapter(Context mContext, List<HomeBean.DataBean> homeDatas) {
        this.mContext = mContext;
        this.homeDatas = homeDatas;
    }

    @Override
    public int getCount() {
        return homeDatas==null ? 0:homeDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return homeDatas.get(i);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvName.setText(homeDatas.get(position).getCategoryName());
        if(position==0){
            viewHolder.labelView.setBackgroundColor(mContext.getResources().getColor(R.color.color_home_label_first));
        }else{
            viewHolder.labelView.setBackgroundColor(mContext.getResources().getColor(R.color.color_home_label_other));
        }

        ItemGridAdapter itemGridAdapter = new ItemGridAdapter(mContext,homeDatas.get(position).getItems());
        viewHolder.gridView.setAdapter(itemGridAdapter);

        return view;
    }





    class ViewHolder {
        @BindView(R.id.item_home_label_view)
        View labelView;
        @BindView(R.id.item_home_name_tv)
        TextView tvName;
        @BindView(R.id.item_home_gridView)
        MyGridView gridView;

     public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
