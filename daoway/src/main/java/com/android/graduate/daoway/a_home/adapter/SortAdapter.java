package com.android.graduate.daoway.a_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.bean.SortBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/9/6.
 * 首页第二栏，分类列表的适配器
 */
public class SortAdapter extends BaseAdapter {
    private Context mContext;
    private List<SortBean.DataBean> mSortDatas;
    public static final int SIZE=10;
    public SortAdapter(Context mContext, List<SortBean.DataBean> mSortDatas) {
        this.mContext = mContext;
        this.mSortDatas = mSortDatas;
    }

    @Override
    public int getCount() {
        return mSortDatas==null ? 0:mSortDatas.size()-1;
    }

    @Override
    public Object getItem(int i) {
        return mSortDatas.get(i);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_sort, parent, false);
            viewHolder=new ViewHolder(view);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        try{
            Picasso.with(mContext).load(mSortDatas.get(position).getIconUrl2()).into(viewHolder.imageIV);
            viewHolder.tvName.setText(mSortDatas.get(position).getName());

        }catch (Exception e){
            e.printStackTrace();
        }



        return view;
    }

    class ViewHolder {

        @BindView(R.id.item_home_sort_icon)
        ImageView imageIV;
        @BindView(R.id.item_home_sort_txt)
        TextView tvName;

       public  ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
