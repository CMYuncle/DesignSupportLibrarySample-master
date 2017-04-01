package com.wuxiaolong.designsupportlibrarysample.UI;

import com.wuxiaolong.designsupportlibrarysample.datas.LongquanData;

import java.util.List;

/**
 * Created by IVERSON on 2017/3/13.
 */
public class AnnouncementConstract {
    interface BasePrenter{
        void start();
        void getAnnoucement();
        void getMoreAnnoucement(int page);
    };
    interface BaseView<T>{
        void setPresenter(T t);
        void showList(List<LongquanData> e);

        void loadMoreList(List<LongquanData> datas);
    }
}
