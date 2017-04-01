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
 * Created by IVERSON on 2017/3/13.
 */
public class LongquanAnnoucementPresenter implements AnnouncementConstract.BasePrenter {

    AnnouncementConstract.BaseView mView;

    public LongquanAnnoucementPresenter(AnnouncementConstract.BaseView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getAnnoucement() {
        Call<ResponseBody> call = ApiClient.getApiServer().getLongquanRetrofitApi().loadLongQuanYiAnnouncement();
        call.enqueue(new RetrofitCallback<ResponseBody>() {

            @Override
            public void onSuccess(ResponseBody model) {
                try {
                    Document document = Jsoup.parse(new String(model.bytes(), "UTF-8"), ApiStories.LONGQUANBASEURL);
                    List<Element> itemList = new ArrayList<Element>();
                    Elements all = document.getElementsByClass("innerList");
                    for (Element e : all) {
                        itemList.addAll(e.getElementsByTag("li"));
                        Log.e("e", e.toString());
                    }
                    ArrayList<LongquanData> list = new ArrayList<LongquanData>();
                    for (Element element : itemList) {
                        LongquanData item = new LongquanData();
                        item.setUrl(ApiStories.LONGQUANBASEURL + element.getElementsByTag("a").attr("href"));
                        item.setTime(element.getElementsByClass("fr").text());
                        item.setDes(element.getElementsByTag("a").text());
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
    public void getMoreAnnoucement(int page) {

    }
}
