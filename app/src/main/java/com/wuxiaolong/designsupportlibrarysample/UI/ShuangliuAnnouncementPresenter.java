package com.wuxiaolong.designsupportlibrarysample.UI;

/**
 * Created by IVERSON on 2017/3/22.
 */
public class ShuangliuAnnouncementPresenter implements AnnouncementConstract.BasePrenter {
    AnnouncementConstract.BaseView mView;

    public ShuangliuAnnouncementPresenter(AnnouncementConstract.BaseView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getAnnoucement() {
        
    }

    @Override
    public void getMoreAnnoucement(int page) {

    }
}
