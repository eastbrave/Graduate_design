package com.android.graduate.daoway.b_category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.ServiceIsBean;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Blank1Fragment extends Fragment {

    String id;
    @BindView(R.id.shop_scroll_view1)
    PullToZoomScrollViewEx shopScrollView1;

    private int mScreenHeight;
    private int mScreenWidth;
    ServiceIsBean.DataBean data;
    private View zoomView;
    private View inflate;
    private View scrollView;

    public static Blank1Fragment newInstance(Bundle args) {
        Blank1Fragment fragment = new Blank1Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank1, container, false);
        ButterKnife.bind(this, view);
        initID();
        initView();
        initData();
        return view;
    }

    private void initID() {
        Bundle arguments = getArguments();
        id = (String) arguments.get("id");

    }

    private void initData() {
        HttpUtils.init().getDatass(id).enqueue(new Callback<ServiceIsBean>() {
            @Override
            public void onResponse(Call<ServiceIsBean> call, Response<ServiceIsBean> response) {
                data = response.body().getData();
                ViewHolder viewHolder = new ViewHolder(zoomView);
                Picasso.with(getContext()).load(data.getSericePrice().getPicUrl()).placeholder(R.drawable.img_pic_default).into(viewHolder.blank1HeadImage);
                ViewHolder1 viewHolder1 = new ViewHolder1(inflate);
                viewHolder1.blank1HeadRlTv1.setText(data.getSericePrice().getName());
                viewHolder1.blank1HeadRlTv2.setText("已售" + data.getSericePrice().getSalesNum());
                ViewHolder2 viewHolder2 = new ViewHolder2(scrollView);
                viewHolder2.tvServiceShow1.setText(data.getSericePrice().getPrice()+data.getSericePrice().getPriceUnit());
                viewHolder2.tvServiceShow2.setText("原价"+data.getSericePrice().getOriginalPrice()+"元");
                viewHolder2.tvServiceShow3.setText(data.getService().getStartTime()+"-"+data.getService().getEndTime());
                long nextAppointTime = data.getService().getNextAppointTime();
                String time = returnDate(nextAppointTime);

                viewHolder2.tvServiceShow4.setText("明天"+time);
                viewHolder2.tvServiceShow5.setText(data.getSericePrice().getDescription());
                viewHolder2.tvServiceShow6.setText(data.getService().getTitle());
                viewHolder2.tvServiceShow7.setText("接单率"+data.getSericePrice().getOrderTakingRate());
                viewHolder2.tvServiceShow8.setText("好评率"+data.getSericePrice().getPositiveCommentRate());
                viewHolder2.tvServiceShow9.setText(data.getService().getDescription());
            }

            @Override
            public void onFailure(Call<ServiceIsBean> call, Throwable t) {

            }
        });
    }
    public String returnDate(long time)  {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String now = sdf.format(new Date(time));
        return now;
    }
    private void initView() {
        zoomView = LayoutInflater.from(getContext()).inflate(R.layout.blank1_head, null);

        inflate = LayoutInflater.from(getContext()).inflate(R.layout.blank1_head_bottom, null);
        scrollView = LayoutInflater.from(getContext()).inflate(R.layout.blank1_content, null);
        shopScrollView1.setZoomView(zoomView);
        shopScrollView1.setZoomEnabled(true);
        shopScrollView1.setHeaderView(inflate);
        shopScrollView1.setScrollContentView(scrollView);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        mScreenHeight = localDisplayMetrics.heightPixels;
        mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, mScreenWidth);
        shopScrollView1.setHeaderLayoutParams(localObject);
    }


    static class ViewHolder {
        @BindView(R.id.blank1_head_image)
        ImageView blank1HeadImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder1 {
        @BindView(R.id.blank1_head_rl_tv1)
        TextView blank1HeadRlTv1;
        @BindView(R.id.blank1_head_rl_tv2)
        TextView blank1HeadRlTv2;

        ViewHolder1(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder2 {
        @BindView(R.id.tv_Service_show1)
        TextView tvServiceShow1;
        @BindView(R.id.tv_Service_show2)
        TextView tvServiceShow2;
        @BindView(R.id.tv_Service_show3)
        TextView tvServiceShow3;
        @BindView(R.id.tv_Service_show4)
        TextView tvServiceShow4;
        @BindView(R.id.tv_Service_show5)
        TextView tvServiceShow5;
        @BindView(R.id.tv_Service_show6)
        TextView tvServiceShow6;
        @BindView(R.id.tv_Service_show7)
        TextView tvServiceShow7;
        @BindView(R.id.tv_Service_show8)
        TextView tvServiceShow8;
        @BindView(R.id.tv_Service_show9)
        TextView tvServiceShow9;

        ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
