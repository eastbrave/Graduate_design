package com.android.graduate.daoway.a_home.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class BannerBean {

    /**
     * status : ok
     * data : [{"imgUrl":"http://img.daoway.cn/img/banners/2016/09/06/b53135ce-254e-46a5-81d7-9d903f5a7009.jpg","target":"5c75bf5cf1044c6f9081c6c20cae2458","type":"service","platform":"all","areaInclude":"1","city":"北京 重庆 哈尔滨 天津 成都 太原 深圳 呼和浩特 青岛 苏州 南昌 杭州 郑州 长沙 乌鲁木齐 沈阳 广州 温州 江苏 宁波 上海 西安 长春 南京 武汉 石家庄 合肥 济南","sharePicUrl":"","shareTitile":"无","shareContent":"无","version":"","serviceId":"","isChaoshi":0,"h5Url":"","start":1473124044000,"end":1473436799000},{"imgUrl":"http://img.daoway.cn/img/banners/2016/07/21/5d13904c-0087-4e58-b44c-5a9bad15ba65.jpg","target":"app://me/openWallet","type":"webpage","platform":"all","areaInclude":"0","city":"北京 上海 广州 深圳 成都 天津 南京 杭州 ","sharePicUrl":"","shareTitile":"无","shareContent":"无","version":"","serviceId":"","isChaoshi":0,"h5Url":"","start":null,"end":null}]
     */

    private String status;
    /**
     * imgUrl : http://img.daoway.cn/img/banners/2016/09/06/b53135ce-254e-46a5-81d7-9d903f5a7009.jpg
     * target : 5c75bf5cf1044c6f9081c6c20cae2458
     * type : service
     * platform : all
     * areaInclude : 1
     * city : 北京 重庆 哈尔滨 天津 成都 太原 深圳 呼和浩特 青岛 苏州 南昌 杭州 郑州 长沙 乌鲁木齐 沈阳 广州 温州 江苏 宁波 上海 西安 长春 南京 武汉 石家庄 合肥 济南
     * sharePicUrl :
     * shareTitile : 无
     * shareContent : 无
     * version :
     * serviceId :
     * isChaoshi : 0
     * h5Url :
     * start : 1473124044000
     * end : 1473436799000
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
        private String imgUrl;
        private String target;
        private String type;
        private String platform;
        private String areaInclude;
        private String city;
        private String sharePicUrl;
        private String shareTitile;
        private String shareContent;
        private String version;
        private String serviceId;
        private int isChaoshi;
        private String h5Url;
        private long start;
        private long end;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getAreaInclude() {
            return areaInclude;
        }

        public void setAreaInclude(String areaInclude) {
            this.areaInclude = areaInclude;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSharePicUrl() {
            return sharePicUrl;
        }

        public void setSharePicUrl(String sharePicUrl) {
            this.sharePicUrl = sharePicUrl;
        }

        public String getShareTitile() {
            return shareTitile;
        }

        public void setShareTitile(String shareTitile) {
            this.shareTitile = shareTitile;
        }

        public String getShareContent() {
            return shareContent;
        }

        public void setShareContent(String shareContent) {
            this.shareContent = shareContent;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public int getIsChaoshi() {
            return isChaoshi;
        }

        public void setIsChaoshi(int isChaoshi) {
            this.isChaoshi = isChaoshi;
        }

        public String getH5Url() {
            return h5Url;
        }

        public void setH5Url(String h5Url) {
            this.h5Url = h5Url;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }
    }
}
