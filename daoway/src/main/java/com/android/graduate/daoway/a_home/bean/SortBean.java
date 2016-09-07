package com.android.graduate.daoway.a_home.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class SortBean {

    /**
     * status : ok
     * data : [{"id":"20","name":"家政","count":0,"iconUrl":"http://img.daoway.cn/img/icon/jiazheng3.png","iconUrl2":"http://img.daoway.cn/img/icon/home/jiazhen.png","order":20,"tags":["小时工","深度保洁","擦玻璃","家电清洗","家居养护","杀虫/除甲醛","保姆/护工","月嫂/育儿嫂"],"description":"保洁 保姆 月嫂"},{"id":"36","name":"维修","count":0,"iconUrl":"http://img.daoway.cn/img/icon/weixiu3.png","iconUrl2":"http://img.daoway.cn/img/icon/home/weixiu.png","order":22,"tags":["手机维修","家电维修","电脑维修","家庭维修","管道疏通","开锁换锁","家电拆装"],"description":"手机 家电 家修"},{"id":"21","name":"家电清洗","count":0,"iconUrl":"http://img.daoway.cn/img/icon/jiadian.png","iconUrl2":"http://img.daoway.cn/img/icon/home/jiadian.png","order":23,"tags":["空调清洗","油烟机清洗","燃气灶清洗","冰箱清洗","洗衣机清洗","热水器清洗","微波炉清洗","饮水机清洗"],"description":"全方位深度清洁"},{"id":"26","name":"洗衣洗鞋","count":0,"iconUrl":"http://img.daoway.cn/img/icon/yixi3.png","iconUrl2":"http://img.daoway.cn/img/icon/home/xiyi.png","order":55,"tags":["洗衣","洗鞋","家居家纺","洗包","定制改衣","奢品养护"],"description":"专业洗涤上门取送"},{"id":"35","name":"搬家","count":0,"iconUrl":"http://img.daoway.cn/img/icon/paotui3.png","iconUrl2":"http://img.daoway.cn/img/icon/home/peisong.png","order":75,"tags":["搬家套餐","家具拆装","大件搬运","仓储收纳"],"description":"货车搬家一站服务"},{"id":"110","name":"美容美体","count":0,"iconUrl":"http://img.daoway.cn/img/icon/liren.png","iconUrl2":"http://img.daoway.cn/img/icon/home/meirong.png","order":77,"tags":["美体塑形","化妆","美容","美眉/美睫","美甲"],"description":"家就是最好的美容"},{"id":"34","name":"更多","count":0,"iconUrl":"http://img.daoway.cn/img/icon/qita4.png","iconUrl2":"http://img.daoway.cn/img/icon/home/qita4.png","order":97,"tags":["鲜花速递","上门回收","家教/陪练","医护陪诊","上门美宠","装修换新"],"description":"各种服务都能上门"},{"id":"22","name":"上门按摩","count":0,"iconUrl":"http://img.daoway.cn/img/icon/anmo.png","iconUrl2":"http://img.daoway.cn/img/icon/noservice/anmo.png","order":21,"tags":["中医推拿","油压/SPA","足疗套餐","养生护理","小儿推拿","开奶催乳"],"description":"推拿 足疗 SPA"},{"id":"60","name":"上门养车","count":0,"iconUrl":"http://img.daoway.cn/img/icon/yangche.png","iconUrl2":"http://img.daoway.cn/img/icon/noservice/yangche2.png","order":80,"tags":["保养套餐","维修检测","清洁养护","上门洗车","保养工时"],"description":"洗车 保养 维修"},{"id":"25","name":"外卖","count":0,"iconUrl":"http://img.daoway.cn/img/icon/waimai3.png","iconUrl2":"http://img.daoway.cn/img/icon/noservice/waimai.png","order":81,"tags":["热送美食","蛋糕","送水","半成净菜","水果生鲜"],"description":"生鲜美食随叫随到"},{"id":"chaoshi","name":"闪送超市","count":0,"iconUrl":"http://img.daoway.cn/img/icon/jiameng.png","iconUrl2":"http://img.daoway.cn/img/icon/jiameng.png","order":0,"tags":null,"description":null}]
     */

    private String status;
    /**
     * id : 20
     * name : 家政
     * count : 0
     * iconUrl : http://img.daoway.cn/img/icon/jiazheng3.png
     * iconUrl2 : http://img.daoway.cn/img/icon/home/jiazhen.png
     * order : 20
     * tags : ["小时工","深度保洁","擦玻璃","家电清洗","家居养护","杀虫/除甲醛","保姆/护工","月嫂/育儿嫂"]
     * description : 保洁 保姆 月嫂
     */

    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String name;
        private int count;
        private String iconUrl;
        private String iconUrl2;
        private int order;
        private String description;
        private List<String> tags;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getIconUrl2() {
            return iconUrl2;
        }

        public void setIconUrl2(String iconUrl2) {
            this.iconUrl2 = iconUrl2;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
}
