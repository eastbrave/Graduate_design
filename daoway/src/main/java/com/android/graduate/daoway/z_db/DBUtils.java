package com.android.graduate.daoway.z_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.graduate.daoway.DaoMaster;
import com.android.graduate.daoway.DaoSession;
import com.android.graduate.daoway.SearchDao;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DBUtils {
    private static SearchDao searchDao;
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
}
