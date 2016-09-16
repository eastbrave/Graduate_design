package com.android.graduate.daoway;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.android.graduate.daoway.Search;
import com.android.graduate.daoway.User;
import com.android.graduate.daoway.Carts;
import com.android.graduate.daoway.Orders;

import com.android.graduate.daoway.SearchDao;
import com.android.graduate.daoway.UserDao;
import com.android.graduate.daoway.CartsDao;
import com.android.graduate.daoway.OrdersDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig searchDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig cartsDaoConfig;
    private final DaoConfig ordersDaoConfig;

    private final SearchDao searchDao;
    private final UserDao userDao;
    private final CartsDao cartsDao;
    private final OrdersDao ordersDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        searchDaoConfig = daoConfigMap.get(SearchDao.class).clone();
        searchDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        cartsDaoConfig = daoConfigMap.get(CartsDao.class).clone();
        cartsDaoConfig.initIdentityScope(type);

        ordersDaoConfig = daoConfigMap.get(OrdersDao.class).clone();
        ordersDaoConfig.initIdentityScope(type);

        searchDao = new SearchDao(searchDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        cartsDao = new CartsDao(cartsDaoConfig, this);
        ordersDao = new OrdersDao(ordersDaoConfig, this);

        registerDao(Search.class, searchDao);
        registerDao(User.class, userDao);
        registerDao(Carts.class, cartsDao);
        registerDao(Orders.class, ordersDao);
    }
    
    public void clear() {
        searchDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        cartsDaoConfig.clearIdentityScope();
        ordersDaoConfig.clearIdentityScope();
    }

    public SearchDao getSearchDao() {
        return searchDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CartsDao getCartsDao() {
        return cartsDao;
    }

    public OrdersDao getOrdersDao() {
        return ordersDao;
    }

}
