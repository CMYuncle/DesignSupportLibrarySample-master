package com.wuxiaolong.designsupportlibrarysample.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by IVERSON on 2017/3/13.
 */
public interface ApiStories {
    public final static String LONGQUANBASEURL = "http://www.longquanyi.gov.cn";
    public final static String PIXIANBASEURL = "http://www.pixian.gov.cn";
    public final static String WENJIANGBASEURL = "http://www.wenjiang.gov.cn";

    @GET("/index.php?cid=2874")
    Call<ResponseBody> loadLongQuanYiAnnouncement();

    @GET("/index.php?cid=340")
    Call<ResponseBody> loadPiXianAnnouncement();

    @GET("/index.php?cid=340")
    Call<ResponseBody> loadPiXianMoreAnnouncement(@Query("page") String page1);

    @GET("/wjzzw/gsgg/common_gsgg.shtml")
    Call<ResponseBody> wenjiangAnnouncement();

    @GET("/wjzzw/gsgg/common_gsgg{page}.shtml")
    Call<ResponseBody> wenjiangMoreAnnouncement(@Path("page") String page);
}
