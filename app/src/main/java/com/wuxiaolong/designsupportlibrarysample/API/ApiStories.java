package com.wuxiaolong.designsupportlibrarysample.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by IVERSON on 2017/3/13.
 */
public interface ApiStories {
    public final static String LONGQUANBASEURL = "http://www.longquanyi.gov.cn";

    @GET("/index.php?cid=2874")
    Call<ResponseBody> loadLongQuanYiAnnouncement();
}
