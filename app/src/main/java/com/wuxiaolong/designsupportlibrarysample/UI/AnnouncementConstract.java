package com.wuxiaolong.designsupportlibrarysample.UI;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by IVERSON on 2017/3/13.
 */
public class AnnouncementConstract {
    interface BasePrenter{
        void start();
        void getAnnoucement();
    };
    interface BaseView<T>{
        void setPresenter(T t);
        void showList(List<Element> e);
    }
}
