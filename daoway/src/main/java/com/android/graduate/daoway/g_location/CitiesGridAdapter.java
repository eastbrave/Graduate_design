package com.android.graduate.daoway.g_location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.graduate.daoway.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/10.
 */
public class CitiesGridAdapter extends BaseAdapter {
    private Context mContext;
    List<String> mDatas;
    public CitiesGridAdapter(Context mContext, List<String> mDatas) {
        this.mContext=mContext;
        this.mDatas=mDatas;
    }

    @Override
    public int getCount() {
        return mDatas==null? 0:mDatas.size();
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
        ViewHolder viewHolder=null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_search_grid, parent, false);
            viewHolder=new ViewHolder(view);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tvName.setText(mDatas.get(position));
        return view;
    }



    class ViewHolder {

        @BindView(R.id.item_search_tv)
        TextView tvName;

        public  ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }

}
