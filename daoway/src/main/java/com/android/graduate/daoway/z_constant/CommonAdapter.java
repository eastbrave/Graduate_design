package com.android.graduate.daoway.z_constant;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {
	private Context context;
	private int layoutId;
	private List<T> list;
	
	
	

	public CommonAdapter(Context context, int layoutId, List<T> list) {
		this.context = context;
		this.layoutId = layoutId;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public T getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolderM holderM=ViewHolderM.get(context, position, layoutId, convertView);		
		convert(holderM,getItem(position));
		return holderM.getmConvertView();
	}
	
	public abstract void convert(ViewHolderM holderM, T bean);
	


}
