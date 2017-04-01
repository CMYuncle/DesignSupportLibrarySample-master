package com.wuxiaolong.designsupportlibrarysample.UI;

import android.util.Log;

import com.wuxiaolong.designsupportlibrarysample.API.ApiClient;
import com.wuxiaolong.designsupportlibrarysample.API.ApiStories;
import com.wuxiaolong.designsupportlibrarysample.API.RetrofitCallback;
import com.wuxiaolong.designsupportlibrarysample.datas.LongquanData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by IVERSON on 2017/3/21.
 */
public class WenjiangAnnoucementPresenter implements AnnouncementConstract.BasePrenter {
    AnnouncementConstract.BaseView mView;
//    HashMap<Integer,String> pages = new HashMap<>();

    public WenjiangAnnoucementPresenter(AnnouncementConstract.BaseView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getAnnoucement() {
        Call<ResponseBody> call = ApiClient.getApiServer().getWenJiangApi().wenjiangAnnouncement();
        call.enqueue(new RetrofitCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody model) {
                try {
                    Log.v("aaa", model.toString());
                    Document document = Jsoup.parse(new String(model.bytes(), "UTF-8"), ApiStories.WENJIANGBASEURL);
                    Elements all = document.getElementsByClass("tit");
//                    pages.put(1,"");
                    ArrayList<LongquanData> list = new ArrayList<LongquanData>();
                    for (Element element : all) {
                        LongquanData item = new LongquanData();
                        item.setUrl(ApiStories.WENJIANGBASEURL + element.getElementsByTag("a").attr("href"));
                        item.setTime(element.getElementsByClass("date Gsgs").text());
                        item.setDes(element.getElementsByTag("a").attr("title"));
                        list.add(item);
                    }
                    mView.showList(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int code, String msg) {

            }

            @Override
            public void onThrowable(Throwable t) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void getMoreAnnoucement(final int page) {
        Call<ResponseBody> call = ApiClient.getApiServer().getWenJiangApi().wenjiangMoreAnnouncement("_"+page);
        call.enqueue(new RetrofitCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody model) {
                try {
                    Log.v("aaa", model.toString());
                    Document document = Jsoup.parse(new String(model.bytes(), "UTF-8"), ApiStories.WENJIANGBASEURL);
                    Elements all = document.getElementsByClass("tit");
                    ArrayList<LongquanData> list = new ArrayList<LongquanData>();
//                    if(!pages.containsKey(page))
                    for (Element element : all) {
                        LongquanData item = new LongquanData();
                        item.setUrl(ApiStories.WENJIANGBASEURL + element.getElementsByTag("a").attr("href"));
                        item.setTime(element.getElementsByClass("date Gsgs").text());
                        item.setDes(element.getElementsByTag("a").attr("title"));
                        list.add(item);
                    }
//                    pages.put(page,"");
                    mView.loadMoreList(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int code, String msg) {

            }

            @Override
            public void onThrowable(Throwable t) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
