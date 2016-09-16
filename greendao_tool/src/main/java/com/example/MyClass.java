package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

public class MyClass {
    public static void main(String[] args){
       curdDemo();
    }

    private static void curdDemo() {
        Schema schema = new Schema(1, "com.android.graduate.daoway");
     //   schema.setDefaultJavaPackageDao("com.android.graduate.daoway");
        //添加表
        Entity search = schema.addEntity("Search");
        search.addIdProperty().autoincrement();
        search.addStringProperty("content").notNull();

        Entity user = schema.addEntity("User");
        user.addIdProperty().autoincrement();
        user.addStringProperty("phone").notNull();
        user.addStringProperty("password");

        Entity carts = schema.addEntity("Carts");
        carts.implementsSerializable();
        carts.addIdProperty().autoincrement();
        carts.addStringProperty("shopName");
        carts.addStringProperty("skuName");
        carts.addStringProperty("skuNum");
        carts.addStringProperty("price");
        carts.addStringProperty("imgUrl");

        Property userId = carts.addLongProperty("userId").getProperty();
        carts.addToOne(user,userId);
        ToMany toMany = user.addToMany(carts, userId);
        toMany.setName("Carts");

        Entity orders = schema.addEntity("Orders");
        orders.implementsSerializable();
        orders.addIdProperty().autoincrement();
        orders.addStringProperty("shopName");
        orders.addStringProperty("skuName");
        orders.addStringProperty("skuNum");
        orders.addStringProperty("price");
        orders.addStringProperty("imgUrl");
        Property userId2 = orders.addLongProperty("userId2").getProperty();
        orders.addToOne(user,userId2);
        ToMany toMany2 = user.addToMany(orders, userId2);
        toMany2.setName("Orders");
        /*
        *


        //信息表进行建立
        Entity infoBean = schema.addEntity("infos");
        infoBean.implementsSerializable();
        infoBean.addIdProperty();
        infoBean.addStringProperty("infoTitle");
        infoBean.addStringProperty("infoAuthor");
        infoBean.addStringProperty("infoContent");
        //这里我们为信息表，添加一个typeId外键，它就是infoType表的id
        Property typeId = infoBean.addLongProperty("typeId").getProperty();

        //这里是重点，我们为这两个表建立1:n的关系，并设置关联字段。
        infoBean.addToOne(infoTypeBean, typeId);
        ToMany addToMany = infoTypeBean.addToMany(infoBean,typeId);
        addToMany.setName("infoes");
        * */







        //自动生成
        try {
            new DaoGenerator().generateAll(schema,"../Graduate_design/daoway/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }




    }






}
