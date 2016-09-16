package com.android.graduate.daoway.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.XiaoQuSearchBean;
import com.android.graduate.daoway.z_constant.PlotSearchAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlotSearchActivity extends AppCompatActivity {

    private String cityName;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText et;
    private ListView lv;
    private List<XiaoQuSearchBean.DataBean> data1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_search);
        Intent intent = getIntent();
        cityName = intent.getStringExtra("cityName");
        sharedPreferences = getSharedPreferences("location", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initView();
        setListener();
    }

    private void setListener() {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initData( et.getText().toString());
            }
        });

    }

    private void initData(String s) {
        HttpUtils.init().getDataXiao(cityName,s).enqueue(new Callback<XiaoQuSearchBean>() {
            @Override
            public void onResponse(Call<XiaoQuSearchBean> call, Response<XiaoQuSearchBean> response) {
                List<XiaoQuSearchBean.DataBean> data = response.body().getData();
                data1.addAll(data);
                setAdapter();

            }

            @Override
            public void onFailure(Call<XiaoQuSearchBean> call, Throwable t) {

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XiaoQuSearchBean.DataBean dataBean = data1.get(position);
                editor.putString("plot", dataBean.getName());
                Log.d("pidan", "onItemClick: dasdadadasssssssssssss"+dataBean.getName());
                editor.putString("cityName", cityName);
                editor.putString("lot", String.valueOf(dataBean.getLot()));
                editor.putString("lat",String.valueOf(dataBean.getLat()));
                editor.putString("id", String.valueOf(dataBean.getId()));
                editor.commit();
                setResult(1);
                finish();
            }
        });
    }

    private void setAdapter() {
        lv.setAdapter(new PlotSearchAdapter(this,R.layout.nearby_plot_lv_item,data1));
    }

    private void initView() {
        et = (EditText)findViewById(R.id.plot_search_et);
        lv = (ListView) findViewById(R.id.plot_search_lv);
    }
    public void onBack(View view) {
        finish();
    }
}
