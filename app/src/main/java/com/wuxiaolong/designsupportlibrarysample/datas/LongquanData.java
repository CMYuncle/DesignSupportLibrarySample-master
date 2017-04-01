package com.wuxiaolong.designsupportlibrarysample.datas;

/**
 * Created by IVERSON on 2017/3/20.
 */
public class LongquanData implements Comparable<LongquanData> {
    String time;
    String des;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String url;

    @Override
    public int compareTo(LongquanData longquanData) {
        return getTime().compareTo(longquanData.getTime()) < 0 ? 1 : -1;
    }
}
