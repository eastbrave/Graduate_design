package com.android.graduate.daoway.d_order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.android.graduate.daoway.z_db.Carts;
import com.android.graduate.daoway.z_db.CartsDao;
import com.android.graduate.daoway.z_db.Orders;
import com.android.graduate.daoway.z_db.OrdersDao;
import com.android.graduate.daoway.R;
import com.android.graduate.daoway.pay.PayResult;
import com.android.graduate.daoway.pay.SignUtils;
import com.android.graduate.daoway.z_db.DBUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {
    public static final String params = "partner=\"2088101568358171\"&seller_id=\"xxx@alipay.com\"&out_trade_no=\"0819145412-6177\"&subject=\"测试\"&body=\"测试测试\"&total_fee=\"0.01\"&notify_url=\"http://notify.msp.hk/notify.htm\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&sign=\"lBBK%2F0w5LOajrMrji7DUgEqNjIhQbidR13GovA5r3TgIbNqv231yC1NksLdw%2Ba3JnfHXoXuet6XNNHtn7VE%2BeCoRO1O%2BR1KugLrQEZMtG5jmJIe2pbjm%2F3kb%2FuGkpG%2BwYQYI51%2BhA3YBbvZHVQBYveBqK%2Bh8mUyb7GM1HxWs9k4%3D\"&sign_type=\"RSA\"\n";
    private static final String TAG = "androidxx";

    // 商户PID
    public static final String PARTNER = "2088111278561763";
    // 商户收款账号
    public static final String SELLER = "gaoyandingzhi@126.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANEClM9ja39OuhbiFcPYG8nUt19TIGvnBjC2CGMV3BKY2pTolVuicMfM0yyxvwtewe7Wkk+06Zl8fjgIWZS8SsfOeznQZbJq236CbcFYIhDsorDllDwQ0Uk409WSjaOCDJamOjGeQjYqy3D7v+z+Z48ZvCOPleX2h415mHQeHWVdAgMBAAECgYB6FrHqOr7uTIRzHXltPu1shi7fJeWIYhjBl3NqvbghvNvho8KrFkYez8yDDQj1kVJjOz+YA6t4lrn77RS2xw4+fRJgBy/LD9ILectaThysuFt84yKooSuFAv1AQKMeVXkpnFuzzBFtxyuRPtPUYXftSvEm/9BapFHGEVCuT7RvAQJBAP9yq18VFhPQAfngld9n0NwmCO33kdbFYqVIWBNKZdvVZIqwIvnmTqsgQacrvWutsWauukKT7VzySkht/uE63j0CQQDRdjgqx4H7SfMjkaZK5nJ6ptuFgR19HkakOJZSIM78Ot3PzfHcnfYuCRjs8lIEWmhYqj2FE+BcZ9cejphGuTWhAkB0XimBXBq9ldGAonXD2whDcbQ5q8EtJKgmgUlWKFs0hQaTQ1/7lZYa0Mv3uq5EwlCBZXGGaNsFr351dl5Y/jdFAkA6D2DmSsL22rqwo1DK9jHJWbMDwJRh+CBwqNbSERIOzGprjZR7KLXycMcd9tVRK5Y87YN7/dR1CLuSVshS4kfBAkAW6ls9/RlBA6gOpDuq+Qn4CZUng3h7OJsDgzCY95RtuMISJNuVFcGC/XVKB+urkyfhR/H7I8HIPXQtNJenH9f2";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "";
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(OrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(OrderActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    //``````````````````````````````````````````````````
    @BindView(R.id.order_lv)
    ListView orderLv;
    @BindView(R.id.pay_back)
    ImageView payBack;
    @BindView(R.id.pay_total_price_tv)
    TextView payTotalPriceTv;
    @BindView(R.id.pay_commit_btn)
    Button payCommitBtn;
    private String shopName;
    private List<Carts> cartsList = new ArrayList<>();
    private HeadHolder headHolder;
    private FootHolder footHolder;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        getKey();
        initAdapter();
        initListener();
    }

    private void initListener() {
        payCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //提交订单
                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String orderTime = formatter.format(curDate);

                CartsDao cartsDao = DBUtils.getCartsDao(OrderActivity.this);
                QueryBuilder<Carts> builder = cartsDao.queryBuilder();
                builder.where(CartsDao.Properties.ShopName.eq(shopName));
                List<Carts> list = builder.list();
                OrdersDao ordersDao = DBUtils.getOrdersDao(OrderActivity.this);
                for (int i = 0; i < list.size(); i++) {
                    String skuName = list.get(i).getSkuName();
                    String price = list.get(i).getPrice();
                    String skuNum = list.get(i).getSkuNum();
                    String imgUrl = list.get(i).getImgUrl();
                    Orders orders=new Orders();
                    orders.setSkuName(skuName);
                    orders.setSkuNum(skuNum);
                    orders.setPrice(price);
                    orders.setShopName(shopName);
                    orders.setOrderTime(orderTime);
                    orders.setImgUrl(imgUrl);
                    ordersDao.insert(orders);
                }

                //提交之后移除购物车数据

                cartsDao.deleteInTx(cartsDao.queryBuilder().where(CartsDao.Properties.ShopName.eq(shopName)).list());




               //// TODO: 2016/9/17 跳转到支付页面
                /////-----------------------------------需要放在后台服务器 begin-----------------------------------
                String orderInfo = getOrderInfo(shopName, "连吃三颗免单", ""+total);

                /**
                 * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
                 */
                String sign = sign(orderInfo);
                try {
                    /**
                     * 仅需对sign 做URL编码
                     */
                    sign = URLEncoder.encode(sign, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                /**
                 * 完整的符合支付宝参数规范的订单信息
                 */
                final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
                //-----------------------------------------后台服务器 end--------------------------------------

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //1、生成订单数据
                        //2、支付
                        PayTask payTask = new PayTask(OrderActivity.this);
                /*
                参数1：订单信息
                参数2：表示在支付钱包显示之前，true会显示一个dialog提示用户表示正在唤起支付宝钱包
                返回值：
                就是同步返回的支付结果（在实际开发过程中，不应该以此同步结果作为支付成功的依据。以异步结果作为成功支付的依据）
                 */
                        String result = payTask.pay(payInfo, true);
                        Message message = mHandler.obtainMessage();
                        message.obj = result;
                        mHandler.sendMessage(message);
                    }
                }).start();

            }
            /**
             * create the order info. 创建订单信息
             *
             */
            private String getOrderInfo(String subject, String body, String price) {

                // 签约合作者身份ID
                String orderInfo = "partner=" + "\"" + PARTNER + "\"";

                // 签约卖家支付宝账号
                orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

                // 商户网站唯一订单号：商户自己的订单号，此订单号需要时唯一的
                orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

                // 商品名称
                orderInfo += "&subject=" + "\"" + subject + "\"";

                // 商品详情
                orderInfo += "&body=" + "\"" + body + "\"";

                // 商品金额
                orderInfo += "&total_fee=" + "\"" + price + "\"";

                // 服务器异步通知页面路径：支付宝将支付结果通过此链接返回给商户的后台服务器。
                //实际开发中：需要将此值修改成公司的后台服务器的一个接口地址-----用来接收支付结果
                orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

                // 服务接口名称， 固定值
                orderInfo += "&service=\"mobile.securitypay.pay\"";

                // 支付类型， 固定值
                orderInfo += "&payment_type=\"1\"";

                // 参数编码， 固定值
                orderInfo += "&_input_charset=\"utf-8\"";

                // 设置未付款交易的超时时间
                // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
                // 取值范围：1m～15d。
                // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
                // 该参数数值不接受小数点，如1.5h，可转换为90m。
                orderInfo += "&it_b_pay=\"30m\"";

                // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
                // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

                // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
                orderInfo += "&return_url=\"m.alipay.com\"";

                return orderInfo;
            }

            /**
             * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
             *
             */
            private String getOutTradeNo() {
                SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
                Date date = new Date();
                String key = format.format(date);

                Random r = new Random();
                key = key + r.nextInt();
                key = key.substring(0, 15);
                return key;
            }

            /**
             * sign the order info. 对订单信息进行签名
             *
             * @param content
             *            待签名订单信息
             */
            private String sign(String content) {
                return SignUtils.sign(content, RSA_PRIVATE);
            }

            /**
             * get the sign type we use. 获取签名方式
             *
             */
            private String getSignType() {
                return "sign_type=\"RSA\"";
            }

        });
    }

    private void initAdapter() {
        PayAdapter payAdapter = new PayAdapter(this, cartsList);
        orderLv.setAdapter(payAdapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.pay_head, null, false);
        headHolder = new HeadHolder(headView);
        View footView = LayoutInflater.from(this).inflate(R.layout.pay_foot, null, false);
        footHolder = new FootHolder(footView);
        orderLv.addHeaderView(headView);
        orderLv.addFooterView(footView);
    }

    private void getKey() {
        Intent intent = getIntent();
        shopName = intent.getStringExtra("shopName");
        CartsDao cartsDao = DBUtils.getCartsDao(this);
        QueryBuilder<Carts> builder = cartsDao.queryBuilder();
        builder.where(CartsDao.Properties.ShopName.eq(shopName));
        List<Carts> list = builder.list();
        cartsList.clear();
        cartsList.addAll(list);
        total=0;
        for (int i = 0; i < cartsList.size(); i++) {
            //设置总金额
                long skuNum = Long.parseLong(cartsList.get(i).getSkuNum());
                double price = Double.parseDouble( cartsList.get(i).getPrice());
                total+=skuNum*price;
        }
        // TODO: 2016/9/17 总金额，保留小数点后两位来显示
        payTotalPriceTv.setText(total+"");

    }


    class HeadHolder {

        @BindView(R.id.pay_city_address)
        TextView payCityAddress;
        @BindView(R.id.pay_street_address_et)
        EditText payStreetAddressEt;
        @BindView(R.id.pay_user_nickname_et)
        EditText payUserNicknameEt;
        @BindView(R.id.pay_user_phone_et)
        EditText payUserPhoneEt;
        @BindView(R.id.pay_appoint_time_tv)
        TextView payAppointTimeTv;

        HeadHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }

    class FootHolder {

        FootHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
