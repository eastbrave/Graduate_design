package com.android.graduate.daoway.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.Carts;
import com.android.graduate.daoway.CartsDao;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.a_home.ShopActivity;
import com.android.graduate.daoway.b_category.Blank1Fragment;
import com.android.graduate.daoway.b_category.Blank2Fragment;
import com.android.graduate.daoway.b_category.Blank3Fragment;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.z_db.DBUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ClassDetailitemActivity extends BaseActivity  {

    @BindView(R.id.tab_classDetail_item)
    TabLayout tabClassDetailItem;
    @BindView(R.id.vp_classDetail_item)
    ViewPager vpClassDetailItem;
    List<String> tabs = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.fenxiang_image)
    ImageView fenxiangImage;
    @BindView(R.id.tv_one)
    TextView shopTv;
    @BindView(R.id.tv_two)
    TextView telTv;
    @BindView(R.id.tv_three)
    TextView carTv;
    @BindView(R.id.tv_four)
    Button payBtn;
    @BindView(R.id.shop_cart_num_tv)
    TextView cartNumTv;
    private VpAdapter vpAdapter;
    String id;
    private String serviceId;
    public static long  totalNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detailitem);
        ButterKnife.bind(this);
        ShareSDK.initSDK(this);
        initID();
        initTab();
        initVP();
        setVp();
        tabClassDetailItem.setupWithViewPager(vpClassDetailItem);
        initListener();
    }

    public TextView getCarNumTv(){
        return  cartNumTv;
    }

    public TextView getCarTv(){
        return carTv;
    }

    private void initListener() {
        shopTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassDetailitemActivity.this, ShopActivity.class);
                intent.putExtra("id",serviceId);
                startActivity(intent);
            }
        });




    }

  public void Back(View view){
      finish();
  }
    @Override
    protected void onStart() {
        super.onStart();
        totalNum=0;
        CartsDao cartsDao = DBUtils.getCartsDao(this);
        List<Carts> carts = cartsDao.queryBuilder().list();

        //计算购物车总数量
        for (int i = 0; i < carts.size(); i++) {
            String skuNum = carts.get(i).getSkuNum();

            long num = Long.parseLong(skuNum);
            totalNum += num;

        }
        if (totalNum == 0) {
            cartNumTv.setVisibility(View.GONE);
        } else {
            cartNumTv.setVisibility(View.VISIBLE);
            cartNumTv.setText("" + totalNum);
        }
        cartNumTv.setText(totalNum+"");
    }

    private void initID() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        serviceId = intent.getStringExtra("serviceId");
    }

    private void setVp() {
        vpAdapter = new VpAdapter(getSupportFragmentManager());
        vpClassDetailItem.setAdapter(vpAdapter);
    }

    private void initVP() {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);

        Bundle bundle1 = new Bundle();
        bundle1.putString("serviceId", serviceId);
        Blank1Fragment blank1Fragment = Blank1Fragment.newInstance(bundle);
        Blank2Fragment blank2Fragment = Blank2Fragment.newInstance(bundle);
        Blank3Fragment blank3Fragment = Blank3Fragment.newInstance(bundle1);
        fragments.add(blank1Fragment);
        fragments.add(blank2Fragment);
        fragments.add(blank3Fragment);
    }

    private void initTab() {
        tabs.add("服务");
        tabs.add("详情");
        tabs.add("评价");
    }

    @OnClick(R.id.fenxiang_image)
    public void onClick() {
        //Toast.makeText(ClassDetailitemActivity.this, "点击了分享", Toast.LENGTH_SHORT).show();
        showShare();

    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("https://hao.360.cn/?wd_xp1");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我在｛到位｝发现一个很有意思的服务，服务真到位，强烈推荐！");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    class VpAdapter extends FragmentStatePagerAdapter {

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }

}
