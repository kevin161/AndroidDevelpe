package com.gyz.androiddevelope.net.retrofit.api;

import com.gyz.androiddevelope.engine.AppContants;
import com.gyz.androiddevelope.net.volley.bean.HotSearchBean;
import com.gyz.androiddevelope.net.volley.bean.HtfLoginBean;
import com.gyz.androiddevelope.request_bean.ReqHealthInfoList;
import com.gyz.androiddevelope.request_bean.ReqUserInfoBean;
import com.gyz.androiddevelope.request_bean.ReqWeatherBean;
import com.gyz.androiddevelope.response_bean.AlbumDetailListBean;
import com.gyz.androiddevelope.response_bean.Axiba;
import com.gyz.androiddevelope.response_bean.BeforeNewsBean;
import com.gyz.androiddevelope.response_bean.BoardListInfoBean;
import com.gyz.androiddevelope.response_bean.FollowBoardOperateBean;
import com.gyz.androiddevelope.response_bean.FollowUserOperateBean;
import com.gyz.androiddevelope.response_bean.GalleryRespBean;
import com.gyz.androiddevelope.response_bean.GalleryTypeRespBean;
import com.gyz.androiddevelope.response_bean.HealthInfoList;
import com.gyz.androiddevelope.response_bean.InfoList;
import com.gyz.androiddevelope.response_bean.LatestNewsBean;
import com.gyz.androiddevelope.response_bean.LikePinsOperateBean;
import com.gyz.androiddevelope.response_bean.ListPinsBean;
import com.gyz.androiddevelope.response_bean.LoadImageBean;
import com.gyz.androiddevelope.response_bean.NewsDetailBean;
import com.gyz.androiddevelope.response_bean.PinsDetailBean;
import com.gyz.androiddevelope.response_bean.PinsMainEntity;
import com.gyz.androiddevelope.response_bean.SearchHintBean;
import com.gyz.androiddevelope.response_bean.StoryExtraBean;
import com.gyz.androiddevelope.response_bean.Tngou;
import com.gyz.androiddevelope.response_bean.TokenBean;
import com.gyz.androiddevelope.response_bean.UserInfo;
import com.gyz.androiddevelope.response_bean.UserMeAndOtherBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author: guoyazhou
 * @date: 2016-02-19 10:55
 */
public interface ApiManagerService {

    //============================================知乎日报api=======================================================
    @GET(AppContants.START_IMAG)
    Observable<LoadImageBean> getLoadImg();

    //最新消息
    @GET(AppContants.LATEST_NEWS)
    Observable<LatestNewsBean> getLatestNews();

    // 历史消息
    @GET(AppContants.BEFORE_NEWS)
    Observable<BeforeNewsBean> getBeforeNews(@Path("date") String date);

    //获取新闻详细消息
    @GET(AppContants.DETAIL_NEWS)
    Observable<NewsDetailBean> getNewsDetail(@Path("id") int id);

    //获取故事评论数、点赞数
    @GET(AppContants.EXTRA_NEWS)
    Observable<StoryExtraBean> getNewsExtra(@Path("id") int id);

    //==========天狗云=========================================================================================

    //热点分类接口
    @GET(AppContants.GALLERY_CLASS)
    Observable<GalleryTypeRespBean> getGalleryTypeList();

    @FormUrlEncoded
    @POST(AppContants.GALLERY_BEAN_LIST)
    Observable<GalleryRespBean> getGalleryBeanList(@Field("id") int id, @Field("page") int page, @Field("rows") int rows);

    @FormUrlEncoded
    @POST(AppContants.ALBUM_DETAIL_LIST)
    Observable<AlbumDetailListBean> getAlbumDetailList(@Field("id") int id);

    //关键词搜索
    @FormUrlEncoded
    @POST(AppContants.SEARCH_KEY_LIST)
    Observable<HotSearchBean> getSearchList(@Field("keyword") String keyword, @Field("name") String name, @Field("page") int page, @Field("rows") int rows);
    //=============================================================================================================
    @FormUrlEncoded
    @POST("/data/sk/101010100.html")
    Observable<Axiba> getWeather(@Field("cityId") String cityId, @Field("cityName") String cityName);

    @POST("/data/sk/101010100.html")
    Observable<Axiba> getWeather2(@Body ReqWeatherBean reqWeatherBean);

    @POST("api/user")
    Observable<UserInfo> getUserInfo(@Body ReqUserInfoBean bean);

//    @FormUrlEncoded
//    @POST("api/user")
//    Observable<UserInfo> getUserInfo(@Field("access_token") String access_token);

    @GET("api/user")
    Observable<UserInfo> getUserInfo(@Query("access_token") String access_token);

    @POST("api/info/classify")
    Observable<Tngou> getInfoList();


    @GET("api/info/list")
    Observable<InfoList> getHealthInfoList(@Query("id") int id, @Query("rows") int rows, @Query("page") int page);

    @POST("api/info/list")
    Observable<InfoList> getHealthInfoList(@Body ReqHealthInfoList list);

    @POST("api/info/list")
    Observable<HealthInfoList> getHealthNewsInfoList(@Body ReqHealthInfoList list);

    //=========================花瓣==========================
//https://api.huaban.com/favorite/food_drink?limit=20
// 模板类型
    @GET("favorite/{type}")
    Observable<ListPinsBean> httpsTypeLimitRx(@Header("Authorization") String authorization, @Path("type") String type, @Query("limit") int limit);

    //https://api.huaban.com/favorite/food_drink?max=5445324325&limit=20
    //模板类型 的后续联网 max
    @GET("favorite/{type}")
    Observable<ListPinsBean> httpsTypeMaxLimitRx(@Header("Authorization") String authorization, @Path("type") String type, @Query("max") String max, @Query("limit") int limit);


    //https 用户登录  的第一步
    // Authorization 报头一个固定的值 内容 grant_type=password&password=密码&username=账号
    //传入用户名和密码
    @FormUrlEncoded
    @POST("https://huaban.com/oauth/access_token/")
    Observable<TokenBean> httpsTokenRx(@Header("Authorization") String authorization, @Field("grant_type") String grant,
                                       @Field("username") String username, @Field("password") String password);

    //登录第二步 用上一步结果联网
    @GET("users/me")
    Observable<UserMeAndOtherBean> httpsUserRx(@Header("Authorization") String authorization);


    //获取我的画板集合信息 不需要显示需要保存
    //https://api.huaban.com/last_boards/?extra=recommend_tags
    @GET("last_boards/")
    Observable<BoardListInfoBean> httpsBoardListInfo(@Header("Authorization") String authorization, @Query("extra") String extra);

    //https://api.huaban.com/users/15246080
    //获取个人信息
    @GET("users/{userId}")
    Observable<UserMeAndOtherBean> httpsUserInfoRx(@Header("Authorization") String authorization, @Path("userId") String pinsId);

    //关注某个用户
    //https://api.huaban.com/users/17037199/follow  或者unfollow POST方法 统一成一个接口
    @POST("users/{userId}/{operate}")
    Observable<FollowUserOperateBean> httpsFollowUserOperate(@Header("Authorization") String authorization, @Path("userId") String userId, @Path("operate") String operate);

    //https://api.huaban.com/users/188174/likes?limit=40
    //用户的喜欢
    @GET("users/{userId}/likes")
    Observable<ListPinsBean> httpsUserLikePinsRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/743988/likes?limit=40&max=4338219
    //用户的喜欢 更多page+
    @GET("users/{userId}/likes")
    Observable<ListPinsBean> httpsUserLikePinsMaxRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("max") String max, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/pins?limit=40
    //用户的采集
    @GET("users/{userId}/pins")
    Observable<ListPinsBean> httpsUserPinsRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("limit") int limit);

    //https://api.huaban.com/users/188174/pins?limit=40&max=19532400
    //采集后续滑动联网
    @GET("users/{userId}/pins")
    Observable<ListPinsBean> httpsUserPinsMaxRx(@Header("Authorization") String authorization, @Path("userId") String pinsId, @Query("max") String max, @Query("limit") int limit);
    //https://api.huaban.com/pins/663478425
    //根据图片id获取详情
    @GET("pins/{pinsId}")
    Observable<PinsDetailBean> httpsPinsDetailRx(@Header("Authorization") String authorization, @Path("pinsId") String pinsId);

    //https//api.huaban.com/pins/654197326/recommend/?page=1&limit=40
    //获取某个图片的推荐图片列表
    @GET("pins/{pinsId}/recommend/")
    Observable<List<PinsMainEntity>> httpPinsRecommendRx(@Header("Authorization") String authorization, @Path("pinsId") String pinsId, @Query("page") int page, @Query("limit") int limit);

    //https//api.huaban.com/boards/19196160/pins?limit=40
    //获取画板的图片集合
    @GET("boards/{boardId}/pins")
    Observable<ListPinsBean> httpsBoardPinsRx(@Header("Authorization") String authorization, @Path("boardId") String boardId, @Query("limit") int limit);

    //https//api.huaban.com/boards/19196160/pins?limit=40&max=508414882
    //获取画板的图片集合 根据上一个图片的id继续加载
    @GET("boards/{boardId}/pins")
    Observable<ListPinsBean> httpsBoardPinsMaxRx(@Header("Authorization") String authorization, @Path("boardId") String boardId, @Query("max") long max, @Query("limit") int limit);
    //https://api.huaban.com/pins/687738004/like
    //https://api.huaban.com/pins/687738004/unlike POST方法 这两个统一成一个接口
    //对图片的进行like操作
    @POST("pins/{pinId}/{operate}")
    Observable<LikePinsOperateBean> httpsLikeOperate(@Header("Authorization") String authorization,
                                                     @Path("pinId") String pinsId, @Path("operate") String operate);


    //https://api.huaban.com/boards/967118/follow
    //https://api.huaban.com/boards/967118/unfollow POST方法 统一成一个接口
    //对画板进行关注操作
    @POST("boards/{boardId}/{operate}")
    Observable<FollowBoardOperateBean> httpsFollowBoardOperate(@Header("Authorization") String authorization,
                                                               @Path("boardId") String boardId, @Path("operate") String operate);

    /**
     * 下载图片接口
     * @param pinId
     * @return
     */
    @GET("http://img.hb.aicdn.com/{pinId}")
    Observable<ResponseBody> httpDownImage(@Path("pinId") String pinId);

    //https//api.huaban.com/search/hint?q=%E4%BA%BA
    //搜索关键字 提示
    @GET("search/hint")
    Observable<SearchHintBean> httpsSearHintBean(@Header("Authorization") String authorization, @Query("q") String key);

    //====================汇添富====================
    @FormUrlEncoded
    @POST("services/account/login")
    Observable<HtfLoginBean> htfLogin(@Field("certNum")String certNum,@Field("password") String password,
                                      @Field("certType") String certType,@Field("deviceId") String deviceId,
                                      @Field("device") String device ,@Field("os") String os,
                                      @Field("version") String version,@Field("versionCode") String versionCode,
                                      @Field("access") String access,@Field("carrier") String carrier,
                                      @Field("imei") String imei,@Field("macAddress") String macAddress,
                                      @Field("androidId") String androidId,@Field("uniqueId") String uniqueId);

}
