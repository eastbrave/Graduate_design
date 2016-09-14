package com.android.graduate.daoway.f_search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.z_db.Search;
import com.android.graduate.daoway.z_db.SearchDao;
import com.android.graduate.daoway.f_search.SearchBean.SearchBean;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.widget.MyGridView;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.z_db.DBUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/6.
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_click_to_search_iv)
    ImageView searchIv;
    @BindView(R.id.search_lv_history)
    ListView mList;
    private MyGridView gridView;
    private List<String> mGridDatas=new ArrayList<>();

    private List<Search> searchList=new ArrayList<>();
    private GridAdapter gridAdapter;
    private ListAdapter listAdapter;
    private double lot=114.42472076416016;
    private double lat=30.4676456451416;
    private TextView clearTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        intView();
        initListener();
        initData();
    }

    private void initListener() {
        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = searchEdit.getText().toString();
                SearchDao searchDao = DBUtils.getSearchDao(SearchActivity.this);
                if(!TextUtils.isEmpty(searchText)&&!isExist(searchText,searchDao)){
                    Search search=new Search();
                    search.setContent(searchText);
                    searchDao.insert(search);
                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String searchStr = mGridDatas.get(position);

                SearchDao searchDao = DBUtils.getSearchDao(SearchActivity.this);
                if(!isExist(searchStr,searchDao)){
                    Search search=new Search();
                    search.setContent(searchStr);
                    searchDao.insert(search);
                }
                Intent intent=new Intent(SearchActivity.this,SearchResultActivity.class);
                startActivity(intent);
            }
        });

        clearTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUtils.getSearchDao(SearchActivity.this).deleteAll();
                refreshDb();
            }
        });

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String content = searchList.get(position-1).getContent();

            }
        });

    }

    private boolean isExist(String searchText, SearchDao searchDao) {

        QueryBuilder<Search> builder = searchDao.queryBuilder();
        builder.where(SearchDao.Properties.Content.eq(searchText));
        List<Search> searches = builder.list();
        if (searches.size()!=0){
            return true;
        }
       return false;
    }


    private void initData() {
        //刷新数据库数据
        refreshDb();

        //网络请求
        //&lot=114.42472076416016&lat=30.4676456451416
        HttpUtils.init().querySearchBean(lot,lat).enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                formatResult(response.body());
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {

            }
        });


    }
    /*
    * 刷新搜索记录数据库
    * */
    private void refreshDb() {
        searchList.clear();
        List<Search> searches = DBUtils.getSearchDao(this).loadAll();
        searchList.addAll(searches);
        listAdapter.notifyDataSetChanged();
    }

    private void formatResult(SearchBean searchBean) {
        List<String> data = searchBean.getData();
        mGridDatas.addAll(data);
        gridAdapter.notifyDataSetChanged();


    }

    private void intView() {
        View view =LayoutInflater.from(this).inflate(R.layout.top_search_grid, null, false);
        gridView= (MyGridView) view.findViewById(R.id.top_search_gridView);
        gridAdapter = new GridAdapter(this,mGridDatas);
        gridView.setAdapter(gridAdapter);
        //设置头部
        mList.addHeaderView(view);
        listAdapter = new ListAdapter(this,searchList);
        mList.setAdapter(listAdapter);
        clearTv=new TextView(this);
   /*     Display display=getWindowManager().getDefaultDisplay();
        int width=display.getWidth();
        int height=40;
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(width,height);
          clearTv.setLayoutParams(params);*/

        clearTv.setText("清除历史记录");

        clearTv.setGravity(Gravity.CENTER);
        mList.addFooterView(clearTv);
    }
}
