package com.android.graduate.daoway.z_constant;

import android.content.Context;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.y_bean.XiaoQuBean;

import java.util.List;

public class PlotListAdapter extends CommonAdapter<XiaoQuBean.DataBean.CommunitiesBean> {
	private Context mcontext;
	public PlotListAdapter(Context context, int layoutId, List<XiaoQuBean.DataBean.CommunitiesBean> list) {
		super(context, layoutId, list);
	   this.mcontext = context;
	}

	@Override
	public void convert(ViewHolderM holderM, XiaoQuBean.DataBean.CommunitiesBean bean) {
		TextView name = (TextView) holderM.getView(R.id.nearby_plot_lv_item_name);
		TextView price =(TextView)  holderM.getView(R.id.nearby_plot_lv_item_location);
		name.setText(bean.getName());
		price.setText(bean.getAddr());
	}

}
