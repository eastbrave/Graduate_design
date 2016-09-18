package com.android.graduate.daoway.b_category;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.graduate.daoway.Carts;
import com.android.graduate.daoway.CartsDao;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.start.ClassDetailitemActivity;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.ServiceIsBean;
import com.android.graduate.daoway.z_db.DBUtils;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Blank1Fragment extends Fragment {
    /**
     * 定义动画执行的起始坐标和结束坐标
     */
    private float startX,startY,endX,endY;
    ImageView ball;

    String id;
    @BindView(R.id.shop_scroll_view1)
    PullToZoomScrollViewEx shopScrollView1;


    private int mScreenHeight;
    private int mScreenWidth;
    ServiceIsBean.DataBean data;
    private View zoomView;
    private View inflate;
    private View scrollView;
    private AnimatorSet animatorSet;
    private ViewHolder2 viewHolder2;
    private long num=0;
    private Context mContext;
    private CartsDao cartsDao;
    private double price;
    private String shopName;
    private String picUrl;
    private String skuName;
    private TextView cartNumTv;//购物车对象
    private TextView carTv;

    public static Blank1Fragment newInstance(Bundle args) {
        Blank1Fragment fragment = new Blank1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        //获取购物车对象 和购物车数量对象
        ClassDetailitemActivity classDetailitemActivity = (ClassDetailitemActivity) mContext;
        cartNumTv = classDetailitemActivity.getCarNumTv();
        carTv = classDetailitemActivity.getCarTv();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank1, container, false);
        ButterKnife.bind(this, view);
        initID();
        initView();
        setupAnimator();
        initData();


        return view;
    }
    private void setupAnimator() {
        animatorSet = new AnimatorSet();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ball.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    private void initListener() {
        //先置空
        num=0;
        viewHolder2.productAddIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getRawX();
                        startY = motionEvent.getRawY() ;
                        break;
                }
                return false;
            }
        });
        viewHolder2.productAddIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                   endX = carTv.getX();
//                   endY = carTv.getY();
//                ball.setVisibility(View.VISIBLE);
//                //创建动画
//                ObjectAnimator xAnimator = ObjectAnimator.ofFloat(ball, "x", startX,   endX);
//                xAnimator.setDuration(500);
//                ObjectAnimator yAnimator = ObjectAnimator.ofFloat(ball, "y", startY, endY);
//                yAnimator.setDuration(500);
//                yAnimator.setInterpolator(new AccelerateInterpolator());
//                animatorSet.play(xAnimator).with(yAnimator);
//                animatorSet.start();


                viewHolder2. productReduceIv.setVisibility(View.VISIBLE);
                viewHolder2.productNumTv.setVisibility(View.VISIBLE);
                cartNumTv.setVisibility(View.VISIBLE);
                num++;
                ClassDetailitemActivity.totalNum++;
                cartNumTv.setText( ClassDetailitemActivity.totalNum+"");
                viewHolder2.productNumTv.setText(num+"");
                //修改数据库
               cartsDao = DBUtils.getCartsDao(mContext);
                QueryBuilder<Carts> builder = cartsDao.queryBuilder();
                builder.where(CartsDao.Properties.SkuName.eq(skuName));
                List<Carts> list =builder.list();
                if(list.size()==0){
                    //还没添加
                    Carts carts=new Carts();
                    carts.setShopName(shopName);
                    carts.setImgUrl(picUrl);
                    carts.setSkuNum(""+ 1);
                    carts.setSkuName(skuName);
                    carts.setPrice(price+"");
                    cartsDao.insert(carts);
                }else {
                    Carts carts = list.get(0);
                    long number = Long.parseLong(carts.getSkuNum())+1;
                    carts.setSkuNum(""+ number);
                    cartsDao.update(carts);
                }
            }
        });

        viewHolder2.productReduceIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num--;
                ClassDetailitemActivity.totalNum--;
                cartNumTv.setText( ClassDetailitemActivity.totalNum+"");
                viewHolder2.productNumTv.setText(num+"");
                //修改数据库
                QueryBuilder<Carts> builder = cartsDao.queryBuilder();
                builder.where(CartsDao.Properties.SkuName.eq(skuName));
                List<Carts> list =builder.list();
                Carts carts = list.get(0);
                long number = Long.parseLong(carts.getSkuNum())-1;
                carts.setSkuNum(""+ number);
                cartsDao.update(carts);
                if(num==0){
                    viewHolder2.productReduceIv.setVisibility(View.GONE);
                    viewHolder2.productNumTv.setVisibility(View.GONE);
                }
            }
        });


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
                shopName = data.getService().getTitle();
                skuName=data.getSericePrice().getName();
                price = data.getSericePrice().getPrice();
                picUrl = data.getSericePrice().getPicUrl();
                ViewHolder viewHolder = new ViewHolder(zoomView);
                Picasso.with(getContext()).load(data.getSericePrice().getPicUrl()).placeholder(R.drawable.img_pic_default).into(viewHolder.blank1HeadImage);
                ViewHolder1 viewHolder1 = new ViewHolder1(inflate);
                viewHolder1.blank1HeadRlTv1.setText(data.getSericePrice().getName());
                viewHolder1.blank1HeadRlTv2.setText("已售" + data.getSericePrice().getSalesNum());
                viewHolder2 = new ViewHolder2(scrollView);
                viewHolder2.tvServiceShow1.setText(data.getSericePrice().getPrice() + data.getSericePrice().getPriceUnit());
                viewHolder2.tvServiceShow2.setText("原价" + data.getSericePrice().getOriginalPrice() + "元");
                viewHolder2.tvServiceShow3.setText(data.getService().getStartTime() + "-" + data.getService().getEndTime());
                long nextAppointTime = data.getService().getNextAppointTime();
                String time = returnDate(nextAppointTime);

                viewHolder2.tvServiceShow4.setText("明天" + time);
                viewHolder2.tvServiceShow5.setText(data.getSericePrice().getDescription());
                viewHolder2.tvServiceShow6.setText(data.getService().getTitle());
                viewHolder2.tvServiceShow7.setText("接单率" + data.getSericePrice().getOrderTakingRate());
                viewHolder2.tvServiceShow8.setText("好评率" + data.getSericePrice().getPositiveCommentRate());
                viewHolder2.tvServiceShow9.setText(data.getService().getDescription());
                initListener();
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

    private void initView() {
        zoomView = LayoutInflater.from(getContext()).inflate(R.layout.blank1_head, null);
        inflate = LayoutInflater.from(getContext()).inflate(R.layout.blank1_head_bottom, null);
        scrollView = LayoutInflater.from(getContext()).inflate(R.layout.blank1_content, null);


        ball = (ImageView) scrollView.findViewById(R.id.shopping_cart_ball_iv);
        ball.setVisibility(View.GONE);







        shopScrollView1.setZoomView(zoomView);
        shopScrollView1.setZoomEnabled(true);
        shopScrollView1.setHeaderView(this.inflate);
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

        @BindView(R.id.product_reduce_iv)
        ImageView productReduceIv;
        @BindView(R.id.product_reduce_num_tv)
        TextView productNumTv;
        @BindView(R.id.product_add_iv)
        ImageView productAddIv;
        ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
