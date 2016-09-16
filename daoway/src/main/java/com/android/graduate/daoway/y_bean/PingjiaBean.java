package com.android.graduate.daoway.y_bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MBENBEN on 2016/9/12.
 */
public class PingjiaBean implements Serializable {
    /**
     * status : ok
     * data : {"goodCount":3145,"totalCount":3644,"averageCount":181,"badCount":318,"hasImgCount":6,"comments":[{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLAJCdm2Zqr4pOUDrUUkXeKEIaUiaVCDWvHhnouukKgreJibmP9nIyNpLZRibhGMEHiaU9UCy0mVhgrFag/0","nick":"Anna丶ლ(●ↀωↀ●)ლm","star":5,"comment":"可以","createtime":1473656825000,"hasOrder":true,"id":"b5363d5407024e64bd32aeca2935b6d0","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLAJCdm2Zqr4pOUDrUUkXeKEIaUiaVCDWvHhnouukKgreJibmP9nIyNpLZRibhGMEHiaU9UCy0mVhgrFag/0","nick":"Anna丶ლ(●ↀωↀ●)ლm","star":5,"comment":"可以","createtime":1473656817000,"hasOrder":true,"id":"ab1702448803473bbe2a3d82e935a224","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://q.qlogo.cn/qqapp/1103839060/76C38E732AFBB7EE431B1EBFC576A461/100","nick":"蝎子印印","star":5,"comment":"很好，很勤快，打扫的也很干净！谢谢！","createtime":1473654857000,"hasOrder":true,"id":"899dedfa18b34ec9bbf220b3e9023d09","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":null,"nick":"范兜兜","star":5,"comment":"还可以，有待提高","createtime":1473653476000,"hasOrder":true,"id":"66b6c4c700d649caac2521176cbb58e9","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/UsichrXlnR9K8vtdFTRMYuxoRvwkYUKZXibYh3cxhfFlicN3JGAPBhKPkw3SCZotcPaK4M3oESribVOIsUELtCcUkA/0","nick":"木可习习(ʘ̆ʚʘ̆)","star":5,"comment":"认真负责 把厨房的陈年油污打扫得很干净","createtime":1473645248000,"hasOrder":true,"id":"00f1469e39294160b7499972e394c487","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM4w3mjmktcpLfQK22plggguricArR9tJYYmdH9oz8QXxFuhwKeAwlL9aHue92JoUTiaorkNlZrQrToT3Pa9xZvTicCWBdCgibQiaf8g/0","nick":"随风飘散1","star":4,"comment":"干的不错，就是速度太慢了，自己干十分钟，小时工要干至少半小时，当然这不是一家的问题，不知如何规范行业标准。","createtime":1473635164000,"hasOrder":true,"id":"c787f90d419b41ec85566dc923b3214b","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://q.qlogo.cn/qqapp/1103839060/AE157C6A64B516E02EE9679D8055EE10/100","nick":"天空7","star":5,"comment":"来的两位阿姨都非常好，尤其是帮我打扫屋外地面的阿姨，做事不辞辛劳，认真负责！以后还请云家政的阿姨！","createtime":1473621951000,"hasOrder":true,"id":"5419aaa4bd46401598f7c4a8d59e32a6","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://q.qlogo.cn/qqapp/1103839060/078CAD1DEF95FB79AA275611DDBA6DCA/100","nick":"你说你傻不傻^(oo)^","star":5,"comment":"非常赞，阿姨人很好，也很辛苦","createtime":1473611419000,"hasOrder":true,"id":"81d59302867744168181fed7f7e11517","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":5,"comment":"好","createtime":1473609385000,"hasOrder":true,"id":"3887a201b78246d1b84c33ab76753365","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":4,"comment":"haokeyi","createtime":1473609369000,"hasOrder":true,"id":"cc456fd86bf04b06850f39222aa4c77b","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":4,"comment":"还可以","createtime":1473609360000,"hasOrder":true,"id":"81c6465f5bf64cd4bca228d0b5e57345","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":5,"comment":"好","createtime":1473609347000,"hasOrder":true,"id":"18930f6a51f74ff096ce9496787099de","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":5,"comment":"好","createtime":1473609339000,"hasOrder":true,"id":"07ed8c42d4854180850cf34bf5176241","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":null,"nick":"澜馨","star":3,"comment":"有些拖延","createtime":1473594126000,"hasOrder":true,"id":"2ebb14ae7ea0419292770fbead8de3d6","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":null,"nick":"小狮子的美妈咪","star":5,"comment":"很好","createtime":1473591766000,"hasOrder":true,"id":"382c14a21833464797925dff79e4a9ab","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/oqjmvaMlmyfibgPboLbbdcIDmddvcHVJicIfFsibcHb15J3VSic5gaNF6D8EviacEfo9Q4xmcCO4HAUicrPMIib898xU38eatjgxuo0/0","nick":"️️♎️","star":5,"comment":"干活利落，非常好。有机会还用这个阿姨。辛苦了","createtime":1473591502000,"hasOrder":true,"id":"c4e445d289f84edebc2be65357a472a7","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/Q5xPxqRgZicXHJFFA0cvdyWqyV0RxExCtyFIVafzP8PJeFYg7woWMdtEp92t3KNmyqMe4XgePNmiaU27BE3N8YibVhGnIA5y3vG/0","nick":"Y、W🌻","star":5,"comment":"挺好","createtime":1473588665000,"hasOrder":true,"id":"193da4a200804724b23b83002f37ca26","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/PiajxSqBRaEJxVoFXDsucKDsHh48WTHeHqxuF3IjpdzwKh2X0sCicd6Pe0xMJnGokic6WWB9Bxz0zcuzScHqOzVDw/0","nick":"Yaojm","star":5,"comment":"阿姨很好，特别利索，敬业！","createtime":1473588646000,"hasOrder":true,"id":"b3a3ec244321458c8659bde4b2979550","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":null,"nick":"suxtong","star":5,"comment":"不错","createtime":1473587804000,"hasOrder":true,"id":"78676d9d51fb4da3a33cc54fa6cf4b38","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM67qNDNvibbcic40GniaV1skSAVWv9y5Ahz7CzQkAPGgDiaa2iauLbfkhbeTyB2fSZt1vc0jM9eMnjDZGA/0","nick":"南南♑️","star":5,"comment":"好","createtime":1473586574000,"hasOrder":true,"id":"2a88064893fc4221824a8f8fab3cefd2","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false}]}
     */

    private String status;
    /**
     * goodCount : 3145
     * totalCount : 3644
     * averageCount : 181
     * badCount : 318
     * hasImgCount : 6
     * comments : [{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLAJCdm2Zqr4pOUDrUUkXeKEIaUiaVCDWvHhnouukKgreJibmP9nIyNpLZRibhGMEHiaU9UCy0mVhgrFag/0","nick":"Anna丶ლ(●ↀωↀ●)ლm","star":5,"comment":"可以","createtime":1473656825000,"hasOrder":true,"id":"b5363d5407024e64bd32aeca2935b6d0","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLAJCdm2Zqr4pOUDrUUkXeKEIaUiaVCDWvHhnouukKgreJibmP9nIyNpLZRibhGMEHiaU9UCy0mVhgrFag/0","nick":"Anna丶ლ(●ↀωↀ●)ლm","star":5,"comment":"可以","createtime":1473656817000,"hasOrder":true,"id":"ab1702448803473bbe2a3d82e935a224","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://q.qlogo.cn/qqapp/1103839060/76C38E732AFBB7EE431B1EBFC576A461/100","nick":"蝎子印印","star":5,"comment":"很好，很勤快，打扫的也很干净！谢谢！","createtime":1473654857000,"hasOrder":true,"id":"899dedfa18b34ec9bbf220b3e9023d09","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":null,"nick":"范兜兜","star":5,"comment":"还可以，有待提高","createtime":1473653476000,"hasOrder":true,"id":"66b6c4c700d649caac2521176cbb58e9","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/UsichrXlnR9K8vtdFTRMYuxoRvwkYUKZXibYh3cxhfFlicN3JGAPBhKPkw3SCZotcPaK4M3oESribVOIsUELtCcUkA/0","nick":"木可习习(ʘ̆ʚʘ̆)","star":5,"comment":"认真负责 把厨房的陈年油污打扫得很干净","createtime":1473645248000,"hasOrder":true,"id":"00f1469e39294160b7499972e394c487","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM4w3mjmktcpLfQK22plggguricArR9tJYYmdH9oz8QXxFuhwKeAwlL9aHue92JoUTiaorkNlZrQrToT3Pa9xZvTicCWBdCgibQiaf8g/0","nick":"随风飘散1","star":4,"comment":"干的不错，就是速度太慢了，自己干十分钟，小时工要干至少半小时，当然这不是一家的问题，不知如何规范行业标准。","createtime":1473635164000,"hasOrder":true,"id":"c787f90d419b41ec85566dc923b3214b","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://q.qlogo.cn/qqapp/1103839060/AE157C6A64B516E02EE9679D8055EE10/100","nick":"天空7","star":5,"comment":"来的两位阿姨都非常好，尤其是帮我打扫屋外地面的阿姨，做事不辞辛劳，认真负责！以后还请云家政的阿姨！","createtime":1473621951000,"hasOrder":true,"id":"5419aaa4bd46401598f7c4a8d59e32a6","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://q.qlogo.cn/qqapp/1103839060/078CAD1DEF95FB79AA275611DDBA6DCA/100","nick":"你说你傻不傻^(oo)^","star":5,"comment":"非常赞，阿姨人很好，也很辛苦","createtime":1473611419000,"hasOrder":true,"id":"81d59302867744168181fed7f7e11517","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":5,"comment":"好","createtime":1473609385000,"hasOrder":true,"id":"3887a201b78246d1b84c33ab76753365","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":4,"comment":"haokeyi","createtime":1473609369000,"hasOrder":true,"id":"cc456fd86bf04b06850f39222aa4c77b","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":4,"comment":"还可以","createtime":1473609360000,"hasOrder":true,"id":"81c6465f5bf64cd4bca228d0b5e57345","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":5,"comment":"好","createtime":1473609347000,"hasOrder":true,"id":"18930f6a51f74ff096ce9496787099de","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLD3eSXdtFXibgsj5ZVm1oIxaibkzrCibbiaRMnXgqwb1auEpEaPbXeYZ8WqbkubAsrxunBQqfE2TgGuoQ/0","nick":"Mr南瓜！","star":5,"comment":"好","createtime":1473609339000,"hasOrder":true,"id":"07ed8c42d4854180850cf34bf5176241","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":null,"nick":"澜馨","star":3,"comment":"有些拖延","createtime":1473594126000,"hasOrder":true,"id":"2ebb14ae7ea0419292770fbead8de3d6","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":null,"nick":"小狮子的美妈咪","star":5,"comment":"很好","createtime":1473591766000,"hasOrder":true,"id":"382c14a21833464797925dff79e4a9ab","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/oqjmvaMlmyfibgPboLbbdcIDmddvcHVJicIfFsibcHb15J3VSic5gaNF6D8EviacEfo9Q4xmcCO4HAUicrPMIib898xU38eatjgxuo0/0","nick":"️️♎️","star":5,"comment":"干活利落，非常好。有机会还用这个阿姨。辛苦了","createtime":1473591502000,"hasOrder":true,"id":"c4e445d289f84edebc2be65357a472a7","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/Q5xPxqRgZicXHJFFA0cvdyWqyV0RxExCtyFIVafzP8PJeFYg7woWMdtEp92t3KNmyqMe4XgePNmiaU27BE3N8YibVhGnIA5y3vG/0","nick":"Y、W🌻","star":5,"comment":"挺好","createtime":1473588665000,"hasOrder":true,"id":"193da4a200804724b23b83002f37ca26","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/PiajxSqBRaEJxVoFXDsucKDsHh48WTHeHqxuF3IjpdzwKh2X0sCicd6Pe0xMJnGokic6WWB9Bxz0zcuzScHqOzVDw/0","nick":"Yaojm","star":5,"comment":"阿姨很好，特别利索，敬业！","createtime":1473588646000,"hasOrder":true,"id":"b3a3ec244321458c8659bde4b2979550","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":null,"nick":"suxtong","star":5,"comment":"不错","createtime":1473587804000,"hasOrder":true,"id":"78676d9d51fb4da3a33cc54fa6cf4b38","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false},{"iconUrl":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM67qNDNvibbcic40GniaV1skSAVWv9y5Ahz7CzQkAPGgDiaa2iauLbfkhbeTyB2fSZt1vc0jM9eMnjDZGA/0","nick":"南南♑️","star":5,"comment":"好","createtime":1473586574000,"hasOrder":true,"id":"2a88064893fc4221824a8f8fab3cefd2","replyComment":null,"replyTime":null,"imgPath":null,"myComment":false}]
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

    public static class DataBean implements Serializable{
        private int goodCount;
        private int totalCount;
        private int averageCount;
        private int badCount;
        private int hasImgCount;
        /**
         * iconUrl : http://wx.qlogo.cn/mmopen/ajNVdqHZLLAJCdm2Zqr4pOUDrUUkXeKEIaUiaVCDWvHhnouukKgreJibmP9nIyNpLZRibhGMEHiaU9UCy0mVhgrFag/0
         * nick : Anna丶ლ(●ↀωↀ●)ლm
         * star : 5
         * comment : 可以
         * createtime : 1473656825000
         * hasOrder : true
         * id : b5363d5407024e64bd32aeca2935b6d0
         * replyComment : null
         * replyTime : null
         * imgPath : null
         * myComment : false
         */

        private List<CommentsBean> comments;

        public int getGoodCount() {
            return goodCount;
        }

        public void setGoodCount(int goodCount) {
            this.goodCount = goodCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getAverageCount() {
            return averageCount;
        }

        public void setAverageCount(int averageCount) {
            this.averageCount = averageCount;
        }

        public int getBadCount() {
            return badCount;
        }

        public void setBadCount(int badCount) {
            this.badCount = badCount;
        }

        public int getHasImgCount() {
            return hasImgCount;
        }

        public void setHasImgCount(int hasImgCount) {
            this.hasImgCount = hasImgCount;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "goodCount=" + goodCount +
                    ", totalCount=" + totalCount +
                    ", averageCount=" + averageCount +
                    ", badCount=" + badCount +
                    ", hasImgCount=" + hasImgCount +
                    ", comments=" + comments +
                    '}';
        }

        public static class CommentsBean implements Serializable{
            private String iconUrl;
            private String nick;
            private int star;
            private String comment;
            private long createtime;
            private boolean hasOrder;
            private String id;
            private Object replyComment;
            private Object replyTime;
            private Object imgPath;
            private boolean myComment;

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public boolean isHasOrder() {
                return hasOrder;
            }

            public void setHasOrder(boolean hasOrder) {
                this.hasOrder = hasOrder;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getReplyComment() {
                return replyComment;
            }

            public void setReplyComment(Object replyComment) {
                this.replyComment = replyComment;
            }

            public Object getReplyTime() {
                return replyTime;
            }

            public void setReplyTime(Object replyTime) {
                this.replyTime = replyTime;
            }

            public Object getImgPath() {
                return imgPath;
            }

            public void setImgPath(Object imgPath) {
                this.imgPath = imgPath;
            }

            public boolean isMyComment() {
                return myComment;
            }

            public void setMyComment(boolean myComment) {
                this.myComment = myComment;
            }

            @Override
            public String toString() {
                return "CommentsBean{" +
                        "iconUrl='" + iconUrl + '\'' +
                        ", nick='" + nick + '\'' +
                        ", star=" + star +
                        ", comment='" + comment + '\'' +
                        ", createtime=" + createtime +
                        ", hasOrder=" + hasOrder +
                        ", id='" + id + '\'' +
                        ", replyComment=" + replyComment +
                        ", replyTime=" + replyTime +
                        ", imgPath=" + imgPath +
                        ", myComment=" + myComment +
                        '}';
            }
        }
    }
}
