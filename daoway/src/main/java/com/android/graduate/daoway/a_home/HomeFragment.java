package com.android.graduate.daoway.a_home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.adapter.BusinessAdapter;
import com.android.graduate.daoway.a_home.adapter.HomeAdapter;
import com.android.graduate.daoway.a_home.adapter.RecommendAdapter;
import com.android.graduate.daoway.a_home.adapter.SortAdapter;
import com.android.graduate.daoway.a_home.bean.BannerBean;
import com.android.graduate.daoway.a_home.bean.BusinessBean;
import com.android.graduate.daoway.a_home.bean.HomeBean;
import com.android.graduate.daoway.a_home.bean.RecomBean;
import com.android.graduate.daoway.a_home.bean.SortBean;
import com.android.graduate.daoway.b_category.activity.ClassDetailActivity;
import com.android.graduate.daoway.widget.MyGridView;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.CategoryBean;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/5.
 */
public class HomeFragment extends Fragment {

    private String city = "武汉";
    private double lat = 30.4676456451416;
    private double lot = 114.42472076416016;
    @BindView(R.id.order_lv)
    ListView mList;
    private Context mContext;
    private List<BannerBean.DataBean> mBannerDatas = new ArrayList<>();
    private List<SortBean.DataBean> mSortDatas = new ArrayList<>();
    private List<RecomBean.DataBean.RecommendBean> rcDatas = new ArrayList<>();
    private List<RecomBean.DataBean.ActivityBean> acDatas = new ArrayList<>();
    private List<HomeBean.DataBean> homeDatas = new ArrayList<>();
    private List<BusinessBean.DataBean> businessDatas = new ArrayList<>();
    private SortAdapter sortAdapter;
    private RecommendAdapter rcAdapter;
    private HomeAdapter homeAdapter;
    private BusinessAdapter businessAdapter;
    private ViewHolder viewHolder;
    private MyGridView businessGV;


    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        setListTopAndBottom();
        initData();
        initListener();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        viewHolder.banner.startTurning(3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewHolder.banner.stopTurning();
    }


    private void initListener() {
        viewHolder.sortGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(mContext, ClassDetailActivity.class);

            }
        });
    }

    private void initData() {
        /*
        * banner数据加载
        * */
        HttpUtils.init().queryBanner(city).enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                formatBannerResult(response.body());
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });

        /*
        * 首页第二类，分类图标数据加载
        * */
        HttpUtils.init().querySort(city).enqueue(new Callback<SortBean>() {
            @Override
            public void onResponse(Call<SortBean> call, Response<SortBean> response) {
                formatSortResult(response.body());
            }

            @Override
            public void onFailure(Call<SortBean> call, Throwable t) {

            }
        });

        /*
        * 今日推荐及其头部数据加载
        * */
        HttpUtils.init().queryRecommend(city).enqueue(new Callback<RecomBean>() {
            @Override
            public void onResponse(Call<RecomBean> call, Response<RecomBean> response) {
                formatRecommendResult(response.body());
            }

            @Override
            public void onFailure(Call<RecomBean> call, Throwable t) {

            }
        });

        /*
        * mList数据加载
        * */
        HttpUtils.init().queryHomeBean(city, lot, lat).enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                formatHomeResult(response.body());
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {

            }
        });


        /*
        * 商家推荐数据加载
        * */
        HttpUtils.init().queryBusinessBean(city, lot, lat).enqueue(new Callback<BusinessBean>() {
            @Override
            public void onResponse(Call<BusinessBean> call, Response<BusinessBean> response) {
                formatBusinessResult(response.body());
            }

            @Override
            public void onFailure(Call<BusinessBean> call, Throwable t) {

            }
        });


        //dantent
        HttpUtils.init().getData("武汉").enqueue(new Callback<CategoryBean>() {
            @Override
            public void onResponse(Call<CategoryBean> call, Response<CategoryBean> response) {
                final List<CategoryBean.DataBean> data = response.body().getData();
                viewHolder.sortGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent intent=new Intent(mContext,ClassDetailActivity.class);
                        CategoryBean.DataBean dataBean = data.get( 1);
                        List<CategoryBean.DataBean.TagsBean> tags = dataBean.getTags();
                        CategoryBean.DataBean.TagsBean tagsBean = tags.get( 1);
                        intent.putExtra("tags",tagsBean);
                        intent.putExtra("bean",dataBean);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<CategoryBean> call, Throwable t) {

            }
        });



    }

    private void formatBusinessResult(BusinessBean businessBean) {
        businessDatas.addAll(businessBean.getData());
        businessAdapter.notifyDataSetChanged();
    }

    private void formatHomeResult(HomeBean homeBean) {
        homeDatas.addAll(homeBean.getData());
        homeAdapter.notifyDataSetChanged();
    }

    private void formatRecommendResult(RecomBean recomBean) {
        rcDatas.addAll(recomBean.getData().getRecommend());
        acDatas.addAll(recomBean.getData().getActivity());
        rcAdapter.notifyDataSetChanged();
        // 设置今日推荐头部数据

        Picasso.with(mContext).load(acDatas.get(0).getImg()).into(viewHolder.itemImage1);
        Picasso.with(mContext).load(acDatas.get(1).getImg()).into(viewHolder.itemImage2);
        Picasso.with(mContext).load(acDatas.get(2).getImg()).into(viewHolder.itemImage3);
        viewHolder.itemName1.setText(acDatas.get(0).getTitle());

        viewHolder.itemName2.setText(acDatas.get(1).getTitle());
        viewHolder.itemName3.setText(acDatas.get(2).getTitle());
        viewHolder.itemLabel1.setText(acDatas.get(0).getSubject());
        viewHolder.itemLabel2.setText(acDatas.get(1).getSubject());
        viewHolder.itemLabel3.setText(acDatas.get(2).getSubject());
        viewHolder.itemName1.setTextColor(getResources().getColor(R.color.color_recommend_text_one));
        viewHolder.itemName2.setTextColor(getResources().getColor(R.color.color_recommend_text_two));
        viewHolder.itemName3.setTextColor(getResources().getColor(R.color.color_recommend_text_three));




    }

    private void formatSortResult(SortBean sortBean) {
        mSortDatas.addAll(sortBean.getData());
        sortAdapter.notifyDataSetChanged();
    }


    /*
    * 格式化banner返回数据
    * */
    private void formatBannerResult(BannerBean bannerBean) {
        mBannerDatas.addAll(bannerBean.getData());
        viewHolder.banner.setPages(new CBViewHolderCreator<HeadBannerViewHolder>() {
            @Override
            public HeadBannerViewHolder createHolder() {
                return new HeadBannerViewHolder();
            }
        }, mBannerDatas);
        viewHolder.banner.setPageIndicator(new int[]{R.drawable.tag_off, R.drawable.tag_on})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    }


    /*
    * 设置头部和底部
    * */
    private void setListTopAndBottom() {
        //初始化头部控件
        View topView = LayoutInflater.from(mContext).inflate(R.layout.top_home, null, false);
        viewHolder=new ViewHolder(topView);
        initTop();//头部控件处理，绑定适配器
        //初始化底部控件
        View bottomView = LayoutInflater.from(mContext).inflate(R.layout.bottom_home, null, false);
        businessGV= (MyGridView) bottomView.findViewById(R.id.recommend_business_gridView);
        initBottom();//尾部控件处理，绑定适配器
        //添加头部和尾部
        mList.addHeaderView(topView);
        mList.addFooterView(bottomView);
        homeAdapter = new HomeAdapter(mContext, homeDatas);
        mList.setAdapter(homeAdapter);
    }


    private void initBottom() {
        //推荐商家适配器创建和绑定
        businessAdapter = new BusinessAdapter(mContext, businessDatas);
        businessGV.setAdapter(businessAdapter);
    }

    private void initTop() {
        //首页第二栏分类适配器创建和绑定
        sortAdapter = new SortAdapter(mContext, mSortDatas);
        viewHolder.sortGV.setAdapter(sortAdapter);
        //今日推荐适配器创建和绑定
        rcAdapter = new RecommendAdapter(mContext, rcDatas);
        viewHolder.rcGV.setAdapter(rcAdapter);

    }



    class HeadBannerViewHolder implements Holder<BannerBean.DataBean> {
        ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerBean.DataBean data) {
            Picasso.with(mContext).load(mBannerDatas.get(position).getImgUrl()).into(imageView);
        }
    }


     class ViewHolder {
         @BindView(R.id.top_recommend_item_name_1)
         TextView itemName1;
         @BindView(R.id.top_recommend_item_label_1)
         TextView itemLabel1;
         @BindView(R.id.top_recommend_item_image_1)
         ImageView itemImage1;
         @BindView(R.id.top_recommend_item_name_2)
         TextView itemName2;
         @BindView(R.id.top_recommend_item_label_2)
         TextView itemLabel2;
         @BindView(R.id.top_recommend_item_image_2)
         ImageView itemImage2;
         @BindView(R.id.top_recommend_item_name_3)
         TextView itemName3;
         @BindView(R.id.top_recommend_item_label_3)
         TextView itemLabel3;
         @BindView(R.id.top_recommend_item_image_3)
         ImageView itemImage3;
         @BindView(R.id.top_recommend_item1_rl)
         RelativeLayout item1Rl;
         @BindView(R.id.top_recommend_item2_rl)
         RelativeLayout item2Rl;
         @BindView(R.id.top_recommend_item3_rl)
         RelativeLayout item3Rl;
         @BindView(R.id.home_banner)
         ConvenientBanner banner;
         @BindView(R.id.home_sort_gridView)
         MyGridView sortGV;

         @BindView(R.id.top_home_recommend_grid)
         MyGridView rcGV;


         ViewHolder(View view) {
             ButterKnife.bind(this, view);
         }
     }
}
