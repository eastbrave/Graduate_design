package com.android.graduate.daoway.f_search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.b_category.activity.ClassDetailitemActivity;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.ClassDetailBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/12.
 */
public class SearchResultActivity extends BaseActivity {
    String name;
    List<ClassDetailBean.DataBean.ItemsBean> items;
    private MyAdapter adapter;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_click_to_search_iv)
    ImageView searchClickToSearchIv;
    @BindView(R.id.search_lv_history)
    ListView searchLvHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        ButterKnife.bind(this);
        initName();
        setAdapter();
        initData();
        setListen();
        View viewById = findViewById(R.id.enmty_list);
        searchLvHistory.setEmptyView(viewById);
    }

    private void setListen() {
        searchLvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(SearchResultActivity.this, ClassDetailitemActivity.class);
                intent.putExtra("serviceId",items.get(i).getServiceId());
                intent.putExtra("id",items.get(i).getId());
                startActivity(intent);
            }
        });
    }

    private void initName() {
        Intent intent = getIntent();
        name = (String) intent.getStringExtra("name");
    }
    private void setAdapter() {
        adapter=new MyAdapter();
        searchLvHistory.setAdapter(adapter);
    }

    private void initData() {
        HttpUtils.init().getItems(name).enqueue(new Callback<ClassDetailBean>() {
            @Override
            public void onResponse(Call<ClassDetailBean> call, Response<ClassDetailBean> response) {
                items = response.body().getData().getItems();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ClassDetailBean> call, Throwable t) {

            }
        });
    }
    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
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
                view = LayoutInflater.from(SearchResultActivity.this).inflate(R.layout.fragment_classdetail_vp_list, null);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder= (ViewHolder) view.getTag();
            }
            Picasso.with(SearchResultActivity.this).load(items.get(i).getPic_url()).placeholder(R.drawable.img_pic_default).into(viewHolder.vpFragmentListImageShow);
            viewHolder.vpFragmentListNameShow.setText(items.get(i).getName());
            viewHolder.vpFragmentListShangmenShow.setText(items.get(i).getAheadHours()+"小时");
            viewHolder.vpFragmentListContentShow.setText(items.get(i).getDescription());
            viewHolder.vpFragmentListTextShow.setText(""+items.get(i).getPrice());
            viewHolder.vpFragmentListTextShow1.setText(items.get(i).getPrice_unit());
            viewHolder.vpFragmentListZhenShow.setText("￥"+items.get(i).getOriginalPrice());
            viewHolder.vpFragmentListTitleShow.setText(items.get(i).getServiceTitle());
            viewHolder.vpFragmentListNumShow.setText("已售"+items.get(i).getSalesNum());
            viewHolder.vpFragmentListCommentRateShow.setText("好评"+items.get(i).getPositiveCommentRate());
            return view;
        }

        class ViewHolder {
            @BindView(R.id.vpFragment_list_image_show)
            ImageView vpFragmentListImageShow;
            @BindView(R.id.vpFragment_list_name_show)
            TextView vpFragmentListNameShow;
            @BindView(R.id.vpFragment_list_shangmen_show)
            TextView vpFragmentListShangmenShow;
            @BindView(R.id.vpFragment_list_content_show)
            TextView vpFragmentListContentShow;
            @BindView(R.id.vpFragment_list_text_show)
            TextView vpFragmentListTextShow;
            @BindView(R.id.vpFragment_list_text_show1)
            TextView vpFragmentListTextShow1;
            @BindView(R.id.vpFragment_list_zhen_show)
            TextView vpFragmentListZhenShow;
            @BindView(R.id.vpFragment_list_title_show)
            TextView vpFragmentListTitleShow;
            @BindView(R.id.vpFragment_list_num_show)
            TextView vpFragmentListNumShow;
            @BindView(R.id.vpFragment_list_CommentRate_show)
            TextView vpFragmentListCommentRateShow;

            ViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this, view);
            }
        }
    }



}
