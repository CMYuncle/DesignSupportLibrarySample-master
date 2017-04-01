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
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by IVERSON on 2017/3/20.
 */
public class PiXianAnnouncementPresenter implements AnnouncementConstract.BasePrenter {
    AnnouncementConstract.BaseView mView;
//    HashMap<Integer,String> pages = new HashMap<>();

    public PiXianAnnouncementPresenter(AnnouncementConstract.BaseView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void getAnnoucement() {
        Call<ResponseBody> call = ApiClient.getApiServer().getPiXIanApi().loadPiXianAnnouncement();
        call.enqueue(new RetrofitCallback<ResponseBody>() {

            @Override
            public void onSuccess(ResponseBody model) {
                try {
                    Log.v("aaa", model.toString());
                    Document document = Jsoup.parse(new String(model.bytes(), "UTF-8"), ApiStories.PIXIANBASEURL);
                    List<Element> itemList = new ArrayList<Element>();
                    Elements all = document.getElementsByClass("newlist");
                    for (Element e : all) {
                        itemList.addAll(e.getElementsByTag("li"));
                        Log.e("e", e.toString());
                    }
//                    pages.put(1,"");
                    ArrayList<LongquanData> list = new ArrayList<LongquanData>();
                    for (Element element : itemList) {
                        LongquanData item = new LongquanData();
                        item.setUrl(ApiStories.PIXIANBASEURL+element.getElementsByTag("a").attr("href"));
                        item.setTime(element.getElementsByTag("span").text());
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
                Log.e("e", code + msg);
            }

            @Override
            public void onThrowable(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onFinish() {

            }
        });

    }

    @Override
    public void getMoreAnnoucement(final int page) {
        Call<ResponseBody> call = ApiClient.getApiServer().getPiXIanApi().loadPiXianMoreAnnouncement(""+page);
        call.enqueue(new RetrofitCallback<ResponseBody>() {

            @Override
            public void onSuccess(ResponseBody model) {
                try {
                    Log.v("aaa", model.toString());
                    Document document = Jsoup.parse(new String(model.bytes(), "UTF-8"), ApiStories.PIXIANBASEURL);
                    List<Element> itemList = new ArrayList<Element>();
                    Elements all = document.getElementsByClass("newlist");
                    for (Element e : all) {
                        itemList.addAll(e.getElementsByTag("li"));
                        Log.e("e", e.toString());
                    }
                    ArrayList<LongquanData> list = new ArrayList<LongquanData>();
//                    if(!pages.containsKey(page))
                    for (Element element : itemList) {
                        LongquanData item = new LongquanData();
                        item.setUrl(ApiStories.PIXIANBASEURL+element.getElementsByTag("a").attr("href"));
                        item.setTime(element.getElementsByTag("span").text());
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
                Log.e("e", code + msg);
            }

            @Override
            public void onThrowable(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onFinish() {

            }
        });

    }
}
