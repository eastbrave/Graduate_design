package com.android.graduate.daoway.y_bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MBENBEN on 2016/9/16.
 */
public class XiaoQuSearchBean implements Serializable {

    /**
     * status : ok
     * data : [{"id":240297,"city":"武汉","name":"清能清江锦城","addr":"洪山张家湾街烽胜路55号（烽胜路和白沙四路交汇处）","lot":114.2981185913086,"lat":30.48110580444336,"serviceId":null,"area":"洪山"},{"id":239700,"city":"武汉","name":"清江花园","addr":"武昌中南三路58号","lot":114.34442138671875,"lat":30.54552459716797,"serviceId":null,"area":"武昌区"},{"id":239470,"city":"武汉","name":"清江泓景","addr":"江夏文化大道32号（政协文史中心旁）","lot":114.32867431640625,"lat":30.39727210998535,"serviceId":null,"area":"江夏"},{"id":295446,"city":"武汉","name":"清江山水-2.1期","addr":"湖北省武汉市洪山区","lot":114.407600402832,"lat":30.47619247436523,"serviceId":null,"area":"洪山区"},{"id":239480,"city":"武汉","name":"清江山水","addr":"洪山东湖开发区光谷软件园中路10号（南湖","lot":114.40312957763672,"lat":30.484149932861328,"serviceId":null,"area":"洪山区"},{"id":240219,"city":"武汉","name":"南方小区","addr":"武昌中南三路47-51号（清江花园对面）","lot":114.34518432617188,"lat":30.545438766479492,"serviceId":null,"area":"武昌区"}]
     */

    private String status;
    /**
     * id : 240297
     * city : 武汉
     * name : 清能清江锦城
     * addr : 洪山张家湾街烽胜路55号（烽胜路和白沙四路交汇处）
     * lot : 114.2981185913086
     * lat : 30.48110580444336
     * serviceId : null
     * area : 洪山
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

    public static class DataBean implements Serializable{
        private int id;
        private String city;
        private String name;
        private String addr;
        private double lot;
        private double lat;
        private Object serviceId;
        private String area;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public double getLot() {
            return lot;
        }

        public void setLot(double lot) {
            this.lot = lot;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public Object getServiceId() {
            return serviceId;
        }

        public void setServiceId(Object serviceId) {
            this.serviceId = serviceId;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", city='" + city + '\'' +
                    ", name='" + name + '\'' +
                    ", addr='" + addr + '\'' +
                    ", lot=" + lot +
                    ", lat=" + lat +
                    ", serviceId=" + serviceId +
                    ", area='" + area + '\'' +
                    '}';
        }
    }
}
