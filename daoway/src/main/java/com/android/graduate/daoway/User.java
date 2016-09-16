package com.android.graduate.daoway;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import com.android.graduate.daoway.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "USER".
 */
@Entity(active = true)
public class User {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String phone;
    private String password;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient UserDao myDao;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "userId")
    })
    private List<Carts> Carts;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "userId2")
    })
    private List<Orders> Orders;

    @Generated
    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    @Generated
    public User(Long id, String phone, String password) {
        this.id = id;
        this.phone = phone;
        this.password = password;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getPhone() {
        return phone;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPhone(@NotNull String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Carts> getCarts() {
        if (Carts == null) {
            __throwIfDetached();
            CartsDao targetDao = daoSession.getCartsDao();
            List<Carts> CartsNew = targetDao._queryUser_Carts(id);
            synchronized (this) {
                if(Carts == null) {
                    Carts = CartsNew;
                }
            }
        }
        return Carts;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetCarts() {
        Carts = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Orders> getOrders() {
        if (Orders == null) {
            __throwIfDetached();
            OrdersDao targetDao = daoSession.getOrdersDao();
            List<Orders> OrdersNew = targetDao._queryUser_Orders(id);
            synchronized (this) {
                if(Orders == null) {
                    Orders = OrdersNew;
                }
            }
        }
        return Orders;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetOrders() {
        Orders = null;
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

}
