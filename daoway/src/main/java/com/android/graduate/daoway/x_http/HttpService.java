package com.android.graduate.daoway.x_http;

import com.android.graduate.daoway.y_bean.CategoryBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.android.graduate.daoway.a_home.bean.BannerBean;
import com.android.graduate.daoway.a_home.bean.BusinessBean;
import com.android.graduate.daoway.a_home.bean.HomeBean;
import com.android.graduate.daoway.a_home.bean.RecomBean;
import com.android.graduate.daoway.a_home.bean.ShopBean;
import com.android.graduate.daoway.a_home.bean.SortBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/5.
 */
public interface HttpService {
    //分类页面
     @GET("/daoway/rest/category/withtags?&communityId=242212&hasChaoshi=true")
    Call<CategoryBean> getData(@Query("manualCity") String city);
    //首页banner 接口
    @GET("/daoway/rest/config/banners?platform=android&community_id=242212")
    Call<BannerBean> queryBanner(@Query("city") String city);

    //首页第二栏 分类接口
    @GET("/daoway/rest/category/for_filter?&weidian=false&recommendOnly=true&includeChaoshi=true&hasChaoshi=false")
    Call<SortBean> querySort(@Query("manualCity") String city);

    //首页今日推荐及其头部接口
    @GET("/daoway/rest/indexEvent/all?&market=false&version=v2&serviceMinLimit=4&type=all&marketMinLimit=2")
    Call<RecomBean> queryRecommend(@Query("city") String city);

    //首页List接口
    @GET("/daoway/rest/service_items/recommend_top?start=0&size=10&imei=990006202677968&includeNotInScope=true")
    Call<HomeBean> queryHomeBean(@Query("manualCity") String city,@Query("lot") double lot,@Query("lat") double lat);


     //首页business List接口
    @GET("/daoway/rest/services?start=0&size=10&imei=990006202677968&includeNotInScope=true")
    Call<BusinessBean> queryBusinessBean(@Query("manualCity") String city, @Query("lot") double lot, @Query("lat") double lat);

    //店铺首页
    @GET("/daoway/rest/service/{id}?userId=9a863a39ef0444beb506780a0a6bcfa1&imei=990006202677968")
    Call<ShopBean> queryShopBean(@Path("id") String id,@Query("manualCity")String ctiy,@Query("lot") double lot, @Query("lat") double lat);

/*   // @GET("http://api.daoway.cn/daoway/rest/service/05219ff82a41477e8a7c4539bad74a17?userId=9a863a39ef0444beb506780a0a6bcfa1&manualCity=武汉&lot=114.42472076416016&lat=30.4676456451416&imei=990006202677968")
    @GET("http://api.daoway.cn/daoway/rest/service/{id}?userId=9a863a39ef0444beb506780a0a6bcfa1&manualCity=武汉&lot=114.42472076416016&lat=30.4676456451416&imei=990006202677968")
    Call<ShopBean> queryShopBean(@Path("id") String id);*/
}
