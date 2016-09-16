package com.android.graduate.daoway.f_search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.Search;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/10.
 */
public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Search> searchList;
    public ListAdapter(Context mContext, List<Search> searchList) {
        this.mContext=mContext;
        this.searchList=searchList;
    }

    @Override
    public int getCount() {
        return searchList==null? 0:searchList.size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_search_history_grid, parent, false);
            viewHolder=new ViewHolder(view);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tvName.setText(searchList.get(searchList.size()-1-position).getContent());
        return view;
    }



    class ViewHolder {

        @BindView(R.id.item_search_history_tv)
        TextView tvName;

        public  ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
