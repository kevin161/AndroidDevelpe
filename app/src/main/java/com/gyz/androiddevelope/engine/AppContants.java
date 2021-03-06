package com.gyz.androiddevelope.engine;

import android.os.Environment;

import java.io.File;

/**
 * @author: guoyazhou
 * @date: 2016-01-22 15:21
 */
public class AppContants {

    public static final boolean isDebug = true;

    public static final int ZHIHU_HTTP = 1001;
    public static final int TNGOU_HTTP = 1002;
    public static final int HUABAN_HTTP = 1003;
    public static final int HTF_HTTP = 1004;

    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 101;

    public static final int LIMIT = 20;

    //首次打开应用
    public static final String NOT_FIRST_LOAD = "not_first_load";

    public static final String SP_LOAD_PIC_BY_MOBILE_NET = "load_pic_with_mobile_network";


    public static final String BUGLY_APP_ID = "900021343";
    public static final String WEIXIN_APP_ID = "wx7c330bddec9f3191";

    public static final String BASE_URL_ZHIHU = "https://news-at.zhihu.com/api/4/";
        public static final String BASE_URL_TNGOU = "http://www.tngou.net/";
    public static final String BASE_URL_HUABAN = "https://api.huaban.com/";
    public static final String BASE_URL_HTF = "http://10.50.115.99:9089/mobile3/";


    public static final String FILE_NAME = "gyz";
     public  static  final String FILE_PATH = new File(Environment.getExternalStorageDirectory()+"/"+FILE_NAME).getAbsolutePath();
    //,
    public static final String SEPARATECOMMA=",";

    //    仅为标题
    public static final int TITLE_TYPE = 101;

    public static final String READ_ID = "read_id";
    public static final int LATEST_COLUMN = Integer.MAX_VALUE;
    public static final int DATABASE_VERSION = 11;


    /**
     * 缓存文件路径
     */
    public static final String CACHE_PATH = Environment.getExternalStorageDirectory().getPath() + "/AndroidDevelop/data/";

    public static final String NEED_CALLBACK = "need_callback";

    public static final String TG_IMAGE_HEAD = "http://tnfs.tngou.net/image";
//===========知乎==begin===================================================================

    // loading 图片
    public static final String START_IMAG = "start-image/720*1184";
    //最新消息
    public static final String LATEST_NEWS = "news/latest";
    //历史消息
    public static final String BEFORE_NEWS = "news/before/{date}";
    //消息内容详情
    public static final String DETAIL_NEWS = "news/{id}";
    //新闻额外内容
    public static final String EXTRA_NEWS = "story-extra/{id}";

//===========天狗云==begin=====================================================================

    //热点分类接口
    public static final String GALLERY_CLASS = "tnfs/api/classify";

    //图片列表接口
    public static final String GALLERY_BEAN_LIST = "tnfs/api/list";

    //相册详情列表接口
    public static final String ALBUM_DETAIL_LIST = "tnfs/api/show";
    //关键词搜索
    public static final String SEARCH_KEY_LIST = "api/search";

    //==========花瓣=========================================================================

    public static final int HUABAN_ERR_NUM = 403;  //token过期
    public static final String HuaBanLoginAuth="Basic MWQ5MTJjYWU0NzE0NGZhMDlkODg6Zjk0ZmNjMDliNTliNDM0OWExNDhhMjAzYWIyZjIwYzc=";

    //用户喜欢操作的操作字段
    public static final String OPERATELIKE = "like";
    public static final String OPERATEUNLIKE = "unlike";

    //用户对画板的关注操作字段
    public static final String OPERATEFOLLOW = "follow";
    public static final String OPERATEUNFOLLOW = "unfollow";

    //获得用户画板列表详情的操作符
    public static final String OPERATEBOARDEXTRA="recommend_tags";
    public static final boolean OPERATECHECK=true;

    //删除画板的操作符
    public static final String OPERATEDELETEBOARD="DELETE";


    //user information
    public static final String ISLOGIN = "isLogin";
    public static final String LOGINTIME = "loginTime";
    public static final String USERACCOUNT = "userAccount";
    public static final String USERPASSWORD = "userPassword";
    //token information
    public static final String TOKENACCESS = "TokenAccess";
    public static final String TOKENREFRESH = "TokenRefresh";
    public static final String TOKENTYPE = "TokenType";
    public static final String TOKENEXPIRESIN = "TokeExpiresIn";

    public static final String USERNAME = "userName";
    public static final String USERID = "userID";
    public static final String USERHEADKEY = "userHeadKey";
    public static final String USEREMAIL = "userEmail";
    //board info
    public static final String BOARDTILTARRAY="boardTitleArray";
    public static final String BOARDIDARRAY="boardIdArray";

    public static final String DEX_DIR = "odex";

}
