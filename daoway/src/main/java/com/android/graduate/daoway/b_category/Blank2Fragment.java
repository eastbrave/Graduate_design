package com.android.graduate.daoway.b_category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.ServiceIsBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Blank2Fragment extends Fragment {

    ServiceIsBean.DataBean data;
    @BindView(R.id.xiangqing_tv1)
    TextView xiangqingTv1;
    @BindView(R.id.xiangqing_tv2)
    TextView xiangqingTv2;
    @BindView(R.id.xiangqing_tv3)
    TextView xiangqingTv3;
    @BindView(R.id.xiangqing_tv4)
    TextView xiangqingTv4;
    private String id;

    public static Blank2Fragment newInstance(Bundle args) {
        Blank2Fragment fragment = new Blank2Fragment();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank2, container, false);
        initID();
        initData();
        ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {
        HttpUtils.init().getDatass(id).enqueue(new Callback<ServiceIsBean>() {
            @Override
            public void onResponse(Call<ServiceIsBean> call, Response<ServiceIsBean> response) {
                data = response.body().getData();
                xiangqingTv1.setText(data.getSericePrice().getDescription());
                xiangqingTv2.setText(data.getService().getCoverArea());
                String startTime = data.getService().getStartTime();
                String endTime = data.getService().getEndTime();
                long nextAppointTime = data.getService().getNextAppointTime();
                String time = returnDate(nextAppointTime);
                xiangqingTv3.setText(startTime+"-"+endTime+",最近服务时间：明天 "+time);
                xiangqingTv4.setText(data.getService().getDescription());
            }

            @Override
            public void onFailure(Call<ServiceIsBean> call, Throwable t) {

            }
        });
    }

    public String returnDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String now = sdf.format(new Date(time));
        return now;
    }

    private void initID() {
        Bundle arguments = getArguments();
        id = (String) arguments.get("id");
    }

}
