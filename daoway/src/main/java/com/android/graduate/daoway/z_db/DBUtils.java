package com.android.graduate.daoway.z_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DBUtils {
    private static SearchDao searchDao;
    private static UserDao userDao;
    private static CartsDao cartsDao;
    private static OrdersDao ordersDao;
    public static SearchDao getSearchDao(Context context){
        if(searchDao==null){
            DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(context,"daoway");
            SQLiteDatabase sqLiteDatabase=devOpenHelper.getReadableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession daoSession= daoMaster.newSession();
            searchDao=daoSession.getSearchDao();
        }
        return searchDao;
    }

    public static UserDao getUserDao(Context context){
        if(userDao==null){
            DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(context,"daoway");
            SQLiteDatabase sqLiteDatabase=devOpenHelper.getReadableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession daoSession= daoMaster.newSession();
            userDao=daoSession.getUserDao();
        }
        return userDao;
    }

    /*
    * 购物车
    * */
    public static CartsDao getCartsDao(Context context){
        if(cartsDao==null){
            DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(context,"daoway");
            SQLiteDatabase sqLiteDatabase=devOpenHelper.getReadableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession daoSession= daoMaster.newSession();
            cartsDao=daoSession.getCartsDao();
        }
        return cartsDao;
    }


    /*
   * 订单
   * */
    public static OrdersDao getOrdersDao(Context context){
        if(ordersDao==null){
            DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(context,"daoway");
            SQLiteDatabase sqLiteDatabase=devOpenHelper.getReadableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession daoSession= daoMaster.newSession();
            ordersDao=daoSession.getOrdersDao();
        }
        return ordersDao;
    }




}
