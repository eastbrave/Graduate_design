package com.android.graduate.daoway.z_constant;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.y_bean.CategoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MBENBEN on 2016/9/6.
 */
public class GvAdapter extends BaseAdapter {
 private   Context mContect;
    private List<CategoryBean.DataBean.TagsBean> mlist;

    public GvAdapter(Context mContect, List<CategoryBean.DataBean.TagsBean> mlist) {
        this.mContect = mContect;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist==null?0:mlist.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
           view= LayoutInflater.from(mContect).inflate(R.layout.fragment_category_list2_gv,null);
            viewHolder=new ViewHolder(view);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
       viewHolder.text.setText(mlist.get(i).getName());
        boolean hasService = mlist.get(i).isHasService();
        boolean hot = mlist.get(i).isHot();
        if(hasService){
          viewHolder.text.setTextColor(Color.DKGRAY);
        }else {
            viewHolder.text.setTextColor(Color.LTGRAY);
        }
        if(hot){
            viewHolder.image.setVisibility(View.VISIBLE);
        }
        return view;
    }
  class ViewHolder{
      @BindView(R.id.category_list2_myGridView_text)
     TextView text;
      @BindView(R.id.category_list2_myGridView_image)
      ImageView image;
      public ViewHolder(View view) {
      view.setTag(this);
          ButterKnife.bind(this,view);
      }
  }
}
