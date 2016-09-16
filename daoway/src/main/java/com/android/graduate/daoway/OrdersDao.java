package com.android.graduate.daoway;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ORDERS".
*/
public class OrdersDao extends AbstractDao<Orders, Long> {

    public static final String TABLENAME = "ORDERS";

    /**
     * Properties of entity Orders.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ShopName = new Property(1, String.class, "shopName", false, "SHOP_NAME");
        public final static Property SkuName = new Property(2, String.class, "skuName", false, "SKU_NAME");
        public final static Property SkuNum = new Property(3, String.class, "skuNum", false, "SKU_NUM");
        public final static Property Price = new Property(4, String.class, "price", false, "PRICE");
        public final static Property ImgUrl = new Property(5, String.class, "imgUrl", false, "IMG_URL");
        public final static Property UserId2 = new Property(6, Long.class, "userId2", false, "USER_ID2");
    }

    private DaoSession daoSession;

    private Query<Orders> user_OrdersQuery;

    public OrdersDao(DaoConfig config) {
        super(config);
    }
    
    public OrdersDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORDERS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SHOP_NAME\" TEXT," + // 1: shopName
                "\"SKU_NAME\" TEXT," + // 2: skuName
                "\"SKU_NUM\" TEXT," + // 3: skuNum
                "\"PRICE\" TEXT," + // 4: price
                "\"IMG_URL\" TEXT," + // 5: imgUrl
                "\"USER_ID2\" INTEGER);"); // 6: userId2
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORDERS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Orders entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String shopName = entity.getShopName();
        if (shopName != null) {
            stmt.bindString(2, shopName);
        }
 
        String skuName = entity.getSkuName();
        if (skuName != null) {
            stmt.bindString(3, skuName);
        }
 
        String skuNum = entity.getSkuNum();
        if (skuNum != null) {
            stmt.bindString(4, skuNum);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(5, price);
        }
 
        String imgUrl = entity.getImgUrl();
        if (imgUrl != null) {
            stmt.bindString(6, imgUrl);
        }
 
        Long userId2 = entity.getUserId2();
        if (userId2 != null) {
            stmt.bindLong(7, userId2);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Orders entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String shopName = entity.getShopName();
        if (shopName != null) {
            stmt.bindString(2, shopName);
        }
 
        String skuName = entity.getSkuName();
        if (skuName != null) {
            stmt.bindString(3, skuName);
        }
 
        String skuNum = entity.getSkuNum();
        if (skuNum != null) {
            stmt.bindString(4, skuNum);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(5, price);
        }
 
        String imgUrl = entity.getImgUrl();
        if (imgUrl != null) {
            stmt.bindString(6, imgUrl);
        }
 
        Long userId2 = entity.getUserId2();
        if (userId2 != null) {
            stmt.bindLong(7, userId2);
        }
    }

    @Override
    protected final void attachEntity(Orders entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Orders readEntity(Cursor cursor, int offset) {
        Orders entity = new Orders( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // shopName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // skuName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // skuNum
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // price
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // imgUrl
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // userId2
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Orders entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setShopName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSkuName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSkuNum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPrice(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setImgUrl(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUserId2(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Orders entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Orders entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Orders entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "Orders" to-many relationship of User. */
    public List<Orders> _queryUser_Orders(Long userId2) {
        synchronized (this) {
            if (user_OrdersQuery == null) {
                QueryBuilder<Orders> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId2.eq(null));
                user_OrdersQuery = queryBuilder.build();
            }
        }
        Query<Orders> query = user_OrdersQuery.forCurrentThread();
        query.setParameter(0, userId2);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUserDao().getAllColumns());
            builder.append(" FROM ORDERS T");
            builder.append(" LEFT JOIN USER T0 ON T.\"USER_ID2\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Orders loadCurrentDeep(Cursor cursor, boolean lock) {
        Orders entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
        entity.setUser(user);

        return entity;    
    }

    public Orders loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Orders> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Orders> list = new ArrayList<Orders>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Orders> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Orders> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
