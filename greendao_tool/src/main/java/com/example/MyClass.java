package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {
    public static void main(String[] args){
       curdDemo();
    }

    private static void curdDemo() {
        Schema schema = new Schema(1,"com.android.graduate.daoway");
        //添加表
        Entity entity = schema.addEntity("Search");
        entity.addIdProperty().autoincrement();
        entity.addStringProperty("content").notNull();

        //自动生成
        try {
            new DaoGenerator().generateAll(schema,"../Graduate_design/daoway/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
