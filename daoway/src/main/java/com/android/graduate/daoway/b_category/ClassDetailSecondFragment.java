package com.android.graduate.daoway.b_category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.ClassDetailBean2;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassDetailSecondFragment extends Fragment {
    List<ClassDetailBean2.DataBean> data;
    String id;
    @BindView(R.id.gv_hot_show)
    GridView gvHotShow;
    private MyAdapter adapter;

    public static ClassDetailSecondFragment newInstance(Bundle args) {
        ClassDetailSecondFragment fragment = new ClassDetailSecondFragment();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_detail_second, container, false);
        ButterKnife.bind(this, view);
        initNum();
        setAdapter();
        initData();
        return view;
    }

    private void setAdapter() {
    adapter=new MyAdapter();
        gvHotShow.setAdapter(adapter);
    }

    private void initNum() {
        Bundle bundle = getArguments();
        id = (String) bundle.get("id");
    }

    private void initData() {
        HttpUtils.init().getDatas(id).enqueue(new Callback<ClassDetailBean2>() {
            @Override
            public void onResponse(Call<ClassDetailBean2> call, Response<ClassDetailBean2> response) {
                data = response.body().getData();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ClassDetailBean2> call, Throwable t) {

            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
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
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.gridview_hot_item, null);
                viewHolder=new ViewHolder(view);
            }else {
                viewHolder= (ViewHolder) view.getTag();
            }
            Picasso.with(getContext()).load(data.get(i).getImgUrl()).placeholder(R.drawable.img_pic_default).into(viewHolder.ivFragmentHotShow);
           viewHolder.tvFragmentHotName.setText(data.get(i).getTitle());
            viewHolder.tvFragmentHotPrice.setText("已接"+data.get(i).getCompleteOrderNum()+"单");
            viewHolder.tvFragmentHotLikeCount.setText("好评"+data.get(i).getPositiveCommentRate());
            return view;
        }

        class ViewHolder {
            @BindView(R.id.iv_fragment_hot_show)
            ImageView ivFragmentHotShow;
            @BindView(R.id.tv_fragment_hot_name)
            TextView tvFragmentHotName;
            @BindView(R.id.tv_fragment_hot_price)
            TextView tvFragmentHotPrice;
            @BindView(R.id.tv_fragment_hot_like_count)
            TextView tvFragmentHotLikeCount;

            ViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this, view);
            }
        }
    }
}
