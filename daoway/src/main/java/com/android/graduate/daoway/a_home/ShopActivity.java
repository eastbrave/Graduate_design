package com.android.graduate.daoway.a_home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.adapter.ShopListAdapter;
import com.android.graduate.daoway.a_home.bean.ShopBean;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.ecloud.pulltozoomview.TransparentToolBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/7.
 */
public class ShopActivity extends BaseActivity {
    @BindView(R.id.shop_cart_num_tv)
    TextView cartNumTv;
    @BindView(R.id.shop_cart_total_price_tv)
    TextView totalPriceTv;
    @BindView(R.id.shop_scroll_view)
    PullToZoomScrollViewEx shopScrollView;
    @BindView(R.id.shop_bar)
    TransparentToolBar shopBar;
    @BindView(R.id.shop_bar_back_iv)
    ImageView shopBarBackIv;
    @BindView(R.id.shop_bar_title_tv)
    TextView shopBarTitleTv;
    @BindView(R.id.shop_bar_menu_iv)
    ImageView shopBarMenuIv;

    private String id = "05219ff82a41477e8a7c4539bad74a17";
    private String city = "武汉";
    private double lot, lat;
    // private ZoomViewHolder zoomViewHolder;
    private ViewHolder viewHolder;
    private int mScreenHeight;
    private int mScreenWidth;
    private PullToZoomScrollViewEx.InternalScrollView internalScrollView;
    private List<ShopBean.DataBean.ImgsBean> imgDatas = new ArrayList<>();
    private ConvenientBanner bannerView;
    private List<ShopBean.DataBean.PricesBean> mPriceDatas = new ArrayList<>();
    private ShopListAdapter shopListAdapter;
    private Map<String, List<ShopBean.DataBean.PricesBean>> mapDatas = new HashMap<>();
    private List<String> keys = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        getIntentInfo();
        initView();
        setBar();
        setAdapter();
        initData();
    }

    private void setAdapter() {
        shopListAdapter = new ShopListAdapter(this, mPriceDatas, cartNumTv);
        viewHolder.shopMenuListLv.setAdapter(shopListAdapter);
    }

    private void setBar() {
        ScrollView pullRootView = shopScrollView.getPullRootView();
        internalScrollView = (PullToZoomScrollViewEx.InternalScrollView) pullRootView;

        shopBar.setNameTV(shopBarTitleTv);
        shopBar.setBackIB(shopBarBackIv);
        shopBar.setMenuIB(shopBarMenuIv);
        shopBar.setNameTVColor(Color.BLACK);
        //必须设置ToolBar颜色值，也就是0~1透明度变化的颜色值,
        //设置最终颜色，白色
        shopBar.setBgColor(Color.WHITE);
        //必须设置ToolBar最大偏移量，这会影响到0~1透明度变化的范围
        //图片高度减去bar的高度，图片完全收进去的时候，透明度变为100，bar为全白色
        shopBar.setOffset(mScreenWidth - getResources().getDimension(R.dimen.activity_bar));
        internalScrollView.setTitleBar(shopBar);
    }

    private void initData() {
        //id,city,lot,lat

        HttpUtils.init().queryShopBean(id, city, lot, lat).enqueue(new Callback<ShopBean>() {
            @Override
            public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {
                formatResult(response.body());
            }

            @Override
            public void onFailure(Call<ShopBean> call, Throwable t) {

            }
        });
    }

    private void formatResult(ShopBean shopBean) {
        ShopBean.DataBean data = shopBean.getData();
        //设置banner
        imgDatas.addAll(data.getImgs());
        bannerView.setPages(new CBViewHolderCreator<HeaderViewHolder>() {
            @Override
            public HeaderViewHolder createHolder() {
                return new HeaderViewHolder();
            }
        }, imgDatas);
        bannerView.setPageIndicator(new int[]{R.drawable.tag_off, R.drawable.tag_on})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        //设置list
        mPriceDatas.addAll(data.getPrices());
        shopListAdapter.notifyDataSetChanged();
        //设置店铺信息
        viewHolder.shopName.setText(data.getTitle());
        viewHolder.shopTimeTv.setText(data.getStartTime() + "-" + data.getEndTime());
        // TODO: 2016/9/10 计算可预约时间
        viewHolder.shopOrderNumTv.setText(data.getOrderTakingCount());
        viewHolder.orderSuccessRate.setText(data.getOrderTakingRate());
        viewHolder.orderSuccessRate.setText(data.getPositiveCommentRate());
        viewHolder.shopGuarantee1Tv.setText(data.getGuarantee().getItems().get(0).getLabel());
        viewHolder.shopGuarantee2Tv.setText(data.getGuarantee().getItems().get(1).getLabel());
        viewHolder.shopGuarantee3Tv.setText(data.getGuarantee().getItems().get(2).getLabel());
        Picasso.with(this).load(data.getGuarantee().getItems().get(0).getIconUrl())
                .into(viewHolder.shopGuarantee1Iv);
        Picasso.with(this).load(data.getGuarantee().getItems().get(1).getIconUrl())
                .into(viewHolder.shopGuarantee2Iv);
        Picasso.with(this).load(data.getGuarantee().getItems().get(2).getIconUrl())
                .into(viewHolder.shopGuarantee3Iv);
        //设置tab

        //数据分类

        keys.add("所有项目");
        mapDatas.put(keys.get(0), mPriceDatas);
        for (int i = 0; i < mPriceDatas.size(); i++) {
            String categoryName = mPriceDatas.get(i).getCategoryName();
            if (!mapDatas.containsKey(categoryName)) {
                keys.add(categoryName);
                List<ShopBean.DataBean.PricesBean> listDatas = new ArrayList<>();
                listDatas.add(mPriceDatas.get(i));
                mapDatas.put(categoryName, listDatas);
            } else {
                mapDatas.get(categoryName).add(mPriceDatas.get(i));
            }
        }
        for (int i = 0; i < keys.size(); i++) {
            viewHolder.shopMenuTab.addTab(viewHolder.shopMenuTab.newTab().setText(keys.get(i)));
        }

        viewHolder.shopMenuTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPriceDatas.clear();
                mPriceDatas.addAll(mapDatas.get(keys.get(tab.getPosition())));
                shopListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //评价专区

        viewHolder.ratingBar.setStepSize(data.getStar());//几星
        viewHolder.shopCommentNumTv.setText(data.getCommentCount());//条数
        viewHolder.commentAreaItemFirstTv.setText(data.getLastComment().getComment());//内容
        viewHolder.commentAreaTimeTv.setText(data.getLastComment().getCreatetime()+"");//时间
        viewHolder.commentAreaUserTv.setText(data.getLastComment().getNick());//昵称
        //服务商信息
        viewHolder.shopIntroduceContentTv.setText(data.getDescription());





    }

    class HeaderViewHolder implements Holder<ShopBean.DataBean.ImgsBean> {
        ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, ShopBean.DataBean.ImgsBean data) {
            Picasso.with(context).load(imgDatas.get(position).getUrl()).into(imageView);
        }
    }

    private void initView() {
        ButterKnife.bind(this);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.shop_banner_layout, null, false);
        //   zoomViewHolder = new ZoomViewHolder(zoomView);

        View scrollView = LayoutInflater.from(this).inflate(R.layout.shop_content_layout, null, false);
        viewHolder = new ViewHolder(scrollView);
        shopScrollView.setZoomView(zoomView);
        shopScrollView.setZoomEnabled(true);
      /*  shopScrollView.setOnPullZoomListener(new PullToZoomBase.OnPullZoomListener() {
            @Override
            public void onPullZooming(int newScrollValue) {

            }

            @Override
            public void onPullZoomEnd() {

            }
        });*/
        bannerView = (ConvenientBanner) findViewById(R.id.shop_banner);
        shopScrollView.setScrollContentView(scrollView);

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        mScreenHeight = localDisplayMetrics.heightPixels;
        mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, mScreenWidth);
        shopScrollView.setHeaderLayoutParams(localObject);

    }

    private void getIntentInfo() {
        Intent intent = getIntent();
        // id = intent.getStringExtra("target");
        // "lot":114.40240478515625,"lat":30.513362884521484,
        lot = 114.40240478515625;
        lat = 30.513362884521484;
    }

  /*  class ZoomViewHolder {
        @BindView(R.id.shop_banner)
        ConvenientBanner bannerView;

        ZoomViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }*/

    class ViewHolder {
        @BindView(R.id.shop_name)
        TextView shopName;
        @BindView(R.id.shop_content_mark_cb)
        CheckBox shopContentMarkCb;
        @BindView(R.id.shop_clock_icon)
        ImageView shopClockIcon;
        @BindView(R.id.shop_time_tv)
        TextView shopTimeTv;
        @BindView(R.id.shop_time_tag_tv)
        TextView shopTimeTagTv;
        @BindView(R.id.shop_order_day_tv)
        TextView shopOrderDayTv;
        @BindView(R.id.shop_order_time_tv)
        TextView shopOrderTimeTv;
        @BindView(R.id.shop_order_num_tv)
        TextView shopOrderNumTv;
        @BindView(R.id.shop_order_success_rate)
        TextView orderSuccessRate;
        @BindView(R.id.shop_feedback_rate_tv)
        TextView rateTv;
        @BindView(R.id.shop_click_to_comment_iv)
        ImageView clickToCommentIv;
        @BindView(R.id.shop_click_to_comment_rl)
        RelativeLayout clickToCommentRl;
        @BindView(R.id.item_shop_guarantee_1_iv)
        ImageView shopGuarantee1Iv;
        @BindView(R.id.item_shop_guarantee_1_tv)
        TextView shopGuarantee1Tv;
        @BindView(R.id.item_shop_guarantee_2_iv)
        ImageView shopGuarantee2Iv;
        @BindView(R.id.item_shop_guarantee_2_tv)
        TextView shopGuarantee2Tv;
        @BindView(R.id.item_shop_guarantee_3_iv)
        ImageView shopGuarantee3Iv;
        @BindView(R.id.item_shop_guarantee_3_tv)
        TextView shopGuarantee3Tv;
        @BindView(R.id.shop_menu_tab)
        TabLayout shopMenuTab;
        @BindView(R.id.shop_menu_list_lv)
        ListView shopMenuListLv;
        @BindView(R.id.shop_comment_num_tv)
        TextView shopCommentNumTv;
        @BindView(R.id.shop_comment_area_item_first_tv)
        TextView commentAreaItemFirstTv;
        @BindView(R.id.shop_comment_area_time_tv)
        TextView commentAreaTimeTv;
        @BindView(R.id.shop_comment_area_user_tv)
        TextView commentAreaUserTv;
        @BindView(R.id.shop_comment_area_rl)
        RelativeLayout commentAreaRl;
/*        @BindView(R.id.shop_introduce_title_tv)
        TextView shopIntroduceTitleTv;*/
        @BindView(R.id.shop_introduce_content_tv)
        TextView shopIntroduceContentTv;
        @BindView(R.id.shop_comment_rate_star_rb)
        RatingBar ratingBar;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}