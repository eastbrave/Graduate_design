package com.android.graduate.daoway.a_home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.graduate.daoway.Carts;
import com.android.graduate.daoway.CartsDao;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.adapter.ShopListAdapter;
import com.android.graduate.daoway.a_home.bean.ShopBean;
import com.android.graduate.daoway.c_cart.CartActivity;
import com.android.graduate.daoway.d_order.OrderActivity;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.widget.MyListView;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.z_db.DBUtils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.ecloud.pulltozoomview.TransparentToolBar;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public static long total;
    public static double totalPrice;
    @BindView(R.id.shop_click_to_pay_btn)
    Button payBtn;
    @BindView(R.id.shop_cart_iv)
    ImageView shopCartIv;
    @BindView(R.id.ball_shape)
    ImageView ballShape;
    @BindView(R.id.shop_bottom)
    RelativeLayout shopBottom;
    private String id ;//= "05219ff82a41477e8a7c4539bad74a17";

    private String city = "武汉";
    private String lot, lat;
    // private ZoomViewHolder zoomViewHolder;
    private ViewHolder viewHolder;
    private int mScreenHeight;
    private int mScreenWidth;
    private String shopName;
    private PullToZoomScrollViewEx.InternalScrollView internalScrollView;
    private List<ShopBean.DataBean.ImgsBean> imgDatas = new ArrayList<>();
    private ConvenientBanner bannerView;
    private List<ShopBean.DataBean.PricesBean> mPriceDatas = new ArrayList<>();
    private ShopListAdapter shopListAdapter;
    private Map<String, List<ShopBean.DataBean.PricesBean>> mapDatas = new HashMap<>();
    private List<String> keys = new ArrayList<>();
    private SharedPreferences sp;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onStart() {
        super.onStart();
        sp = getSharedPreferences("isLogin", MODE_PRIVATE);
        boolean login_key = sp.getBoolean("login_key", false);
        if (!login_key) {
            return;
        }

        CartsDao cartsDao = DBUtils.getCartsDao(this);
        List<Carts> carts = cartsDao.queryBuilder().list();
        total = 0;
        totalPrice = 0;
        //计算购物车总数量和总金额
        for (int i = 0; i < carts.size(); i++) {
            String skuNum = carts.get(i).getSkuNum();
            double price = Double.parseDouble(carts.get(0).getPrice());
            long num = Long.parseLong(skuNum);
            total += num;
            totalPrice += price * num;
        }
        if (total == 0) {
            cartNumTv.setVisibility(View.GONE);
        } else {
            cartNumTv.setVisibility(View.VISIBLE);
            cartNumTv.setText("" + total);
        }

        totalPriceTv.setText(totalPrice + "");


    }

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

        initListener();
    }



    private void initListener() {
        shopCartIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        //返回键，结束当前页面
        shopBarBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setAdapter() {

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
        //= "05219ff82a41477e8a7c4539bad74a17";
        //= "80999f240bac4e309a28ebf03a7fd34b"
         Intent intent = getIntent();
        id = intent.getStringExtra("serviceId");
        id="80999f240bac4e309a28ebf03a7fd34b";
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
        Log.i("east", "formatResult: "+shopBean);
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


        shopName = data.getTitle();
        //刷新适配器
        shopListAdapter = new ShopListAdapter(this, mPriceDatas, cartNumTv, totalPriceTv, shopName, shopCartIv,ballShape);
        viewHolder.shopMenuListLv.setAdapter(shopListAdapter);
        // shopListAdapter.notifyDataSetChanged();
        //设置店铺信息
        shopBarTitleTv.setText(shopName);
        viewHolder.shopName.setText(shopName);

        viewHolder.shopTimeTv.setText(data.getStartTime() + "-" + data.getEndTime());
        // TODO: 2016/9/10 计算可预约时间
        long time = data.getNextAppointTime();
//        String apt=getTime(time);
        //      String appointTime=apt.substring(11);
        //      viewHolder.shopAppointTimeTv.setText(appointTime);

        viewHolder.shopOrderNumTv.setText(data.getOrderTakingCount() + "");
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

       // viewHolder.ratingBar.setStepSize(data.getStar());//几星
        viewHolder.shopCommentNumTv.setText("(" + data.getCommentCount() + "条)");//条数
        viewHolder.commentAreaItemFirstTv.setText(data.getLastComment().getComment());//内容
        //      viewHolder.commentAreaTimeTv.setText(getTime(data.getLastComment().getCreatetime()));//时间
        viewHolder.commentAreaUserTv.setText(data.getLastComment().getNick());//昵称
        //服务商信息
        viewHolder.shopIntroduceContentTv.setText(data.getDescription());

        //提交支付
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到订单支付页面
                Intent intent = new Intent(ShopActivity.this, OrderActivity.class);
                intent.putExtra("shopName", shopName);
                startActivity(intent);

            }
        });

    }

    private String getTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-mm-dd e HH:mm");
        String format = sdf.format(new Date(time * 1000));
        return format;
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

        shopScrollView.setZoomView(zoomView);
        shopScrollView.setZoomEnabled(true);

        bannerView = (ConvenientBanner) findViewById(R.id.shop_banner);
        shopScrollView.setScrollContentView(scrollView);


        viewHolder = new ViewHolder(scrollView);


        //  View fuckView=LayoutInflater.from(this).inflate(R.layout.activity_shop,null);


        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        mScreenHeight = localDisplayMetrics.heightPixels;
        mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, mScreenWidth);
        shopScrollView.setHeaderLayoutParams(localObject);


    }

    private void getIntentInfo() {

        sharedPreferences = getSharedPreferences("location", Context.MODE_PRIVATE);
        lot = sharedPreferences.getString("lot","114.40240478515625");
        lat = sharedPreferences.getString("lat","30.513362884521484");

        // id = intent.getStringExtra("target");
        // "lot":114.40240478515625,"lat":30.513362884521484,

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
        @BindView(R.id.shop_appoint_time_tv)
        TextView shopAppointTimeTv;
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
        MyListView shopMenuListLv;
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
