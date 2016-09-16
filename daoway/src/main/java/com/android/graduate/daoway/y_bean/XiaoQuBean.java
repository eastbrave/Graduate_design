package com.android.graduate.daoway.y_bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MBENBEN on 2016/9/16.
 */
public class XiaoQuBean implements Serializable {

    /**
     * status : ok
     * data : {"communities":[{"id":306757,"city":"武汉","name":"拓创大厦","addr":"杨桥湖大道附近","lot":114.43604618483677,"lat":30.424651061321562,"serviceId":null,"area":"江夏区"},{"id":307642,"city":"武汉","name":"拓创科技产业园","addr":"江夏区杨桥湖大道15号","lot":114.43580364234907,"lat":30.42443307263885,"serviceId":null,"area":"江夏区"},{"id":241056,"city":"武汉","name":"C-park创智天地","addr":"武汉市杨桥湖大道13号C-park创智天地营销中心（湖北经济学院法商学院大门对面）","lot":114.43467712402344,"lat":30.426189422607422,"serviceId":null,"area":"江夏"},{"id":239261,"city":"武汉","name":"阳光时尚","addr":"江夏藏龙岛科技园区杨桥湖大道13号（湖北经济学校旁）","lot":114.4323501586914,"lat":30.42271614074707,"serviceId":null,"area":"江夏"}],"baiduCommunities":[{"name":"江夏宏大影院","addr":"武汉市江夏区藏龙岛开发区拓创公寓一楼","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43604618483677,"y":30.424651061321562},{"name":"7天","addr":"武汉市江夏区藏龙岛开发区杨桥湖大道15号拓创产业园业","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43604618483677,"y":30.424651061321562},{"name":"土灶锅台","addr":"藏龙岛开发区杨桥湖大道8号湖北经济学院对面","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43596533734086,"y":30.424542067041696},{"name":"中百超市(藏龙岛店)","addr":"江夏区藏龙岛开发区杨桥湖大道光谷世纪广场C区(经济学院北大门正对面)","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43718703283443,"y":30.425896701499244},{"name":"禾丽简爱概念主题酒店","addr":"藏龙岛经济开发区杨桥湖大道15号金鹰乐活城3楼(湖北经济学院正对面商业街)","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43718703283443,"y":30.42588891629802},{"name":"Helens西餐吧（武汉江夏湖北经院店）","addr":"经济学院杨桥湖大道8号乐活城3楼(湖北经济学院)","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43661211730804,"y":30.426340456931648},{"name":"楚天综合楼","addr":"湖北经济学院继续教育学院滨湖教学区附近","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43640550704076,"y":30.426963268134756},{"name":"江夏区藏龙岛经院社区卫生服务中心","addr":"湖北经济学院内","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43525567598799,"y":30.422658003623837}],"parent":{"id":289882,"city":"武汉","name":"江夏区","addr":"江夏区","lot":114.36708160048248,"lat":30.25248411213425,"serviceId":null,"area":"江夏区"}}
     */

    private String status;
    /**
     * communities : [{"id":306757,"city":"武汉","name":"拓创大厦","addr":"杨桥湖大道附近","lot":114.43604618483677,"lat":30.424651061321562,"serviceId":null,"area":"江夏区"},{"id":307642,"city":"武汉","name":"拓创科技产业园","addr":"江夏区杨桥湖大道15号","lot":114.43580364234907,"lat":30.42443307263885,"serviceId":null,"area":"江夏区"},{"id":241056,"city":"武汉","name":"C-park创智天地","addr":"武汉市杨桥湖大道13号C-park创智天地营销中心（湖北经济学院法商学院大门对面）","lot":114.43467712402344,"lat":30.426189422607422,"serviceId":null,"area":"江夏"},{"id":239261,"city":"武汉","name":"阳光时尚","addr":"江夏藏龙岛科技园区杨桥湖大道13号（湖北经济学校旁）","lot":114.4323501586914,"lat":30.42271614074707,"serviceId":null,"area":"江夏"}]
     * baiduCommunities : [{"name":"江夏宏大影院","addr":"武汉市江夏区藏龙岛开发区拓创公寓一楼","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43604618483677,"y":30.424651061321562},{"name":"7天","addr":"武汉市江夏区藏龙岛开发区杨桥湖大道15号拓创产业园业","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43604618483677,"y":30.424651061321562},{"name":"土灶锅台","addr":"藏龙岛开发区杨桥湖大道8号湖北经济学院对面","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43596533734086,"y":30.424542067041696},{"name":"中百超市(藏龙岛店)","addr":"江夏区藏龙岛开发区杨桥湖大道光谷世纪广场C区(经济学院北大门正对面)","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43718703283443,"y":30.425896701499244},{"name":"禾丽简爱概念主题酒店","addr":"藏龙岛经济开发区杨桥湖大道15号金鹰乐活城3楼(湖北经济学院正对面商业街)","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43718703283443,"y":30.42588891629802},{"name":"Helens西餐吧（武汉江夏湖北经院店）","addr":"经济学院杨桥湖大道8号乐活城3楼(湖北经济学院)","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43661211730804,"y":30.426340456931648},{"name":"楚天综合楼","addr":"湖北经济学院继续教育学院滨湖教学区附近","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43640550704076,"y":30.426963268134756},{"name":"江夏区藏龙岛经院社区卫生服务中心","addr":"湖北经济学院内","city":"武汉市","province":"湖北省","district":"江夏区","street":"杨桥湖大道","x":114.43525567598799,"y":30.422658003623837}]
     * parent : {"id":289882,"city":"武汉","name":"江夏区","addr":"江夏区","lot":114.36708160048248,"lat":30.25248411213425,"serviceId":null,"area":"江夏区"}
     */

    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 289882
         * city : 武汉
         * name : 江夏区
         * addr : 江夏区
         * lot : 114.36708160048248
         * lat : 30.25248411213425
         * serviceId : null
         * area : 江夏区
         */

        private ParentBean parent;
        /**
         * id : 306757
         * city : 武汉
         * name : 拓创大厦
         * addr : 杨桥湖大道附近
         * lot : 114.43604618483677
         * lat : 30.424651061321562
         * serviceId : null
         * area : 江夏区
         */

        private List<CommunitiesBean> communities;
        /**
         * name : 江夏宏大影院
         * addr : 武汉市江夏区藏龙岛开发区拓创公寓一楼
         * city : 武汉市
         * province : 湖北省
         * district : 江夏区
         * street : 杨桥湖大道
         * x : 114.43604618483677
         * y : 30.424651061321562
         */

        private List<BaiduCommunitiesBean> baiduCommunities;

        public ParentBean getParent() {
            return parent;
        }

        public void setParent(ParentBean parent) {
            this.parent = parent;
        }

        public List<CommunitiesBean> getCommunities() {
            return communities;
        }

        public void setCommunities(List<CommunitiesBean> communities) {
            this.communities = communities;
        }

        public List<BaiduCommunitiesBean> getBaiduCommunities() {
            return baiduCommunities;
        }

        public void setBaiduCommunities(List<BaiduCommunitiesBean> baiduCommunities) {
            this.baiduCommunities = baiduCommunities;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "parent=" + parent +
                    ", communities=" + communities +
                    ", baiduCommunities=" + baiduCommunities +
                    '}';
        }

        public static class ParentBean implements Serializable{
            private int id;

            @Override
            public String toString() {
                return "ParentBean{" +
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
        }

        public static class CommunitiesBean implements Serializable{
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
                return "CommunitiesBean{" +
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

        public static class BaiduCommunitiesBean implements Serializable{
            private String name;
            private String addr;
            private String city;
            private String province;
            private String district;
            private String street;
            private double x;
            private double y;

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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }

            @Override
            public String toString() {
                return "BaiduCommunitiesBean{" +
                        "name='" + name + '\'' +
                        ", addr='" + addr + '\'' +
                        ", city='" + city + '\'' +
                        ", province='" + province + '\'' +
                        ", district='" + district + '\'' +
                        ", street='" + street + '\'' +
                        ", x=" + x +
                        ", y=" + y +
                        '}';
            }
        }
    }
}
