package com.gyz.androiddevelope.net.volley.bean;

import com.android.volley.Request;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.net.volley.bean.HotSearchBean.java
 * @author: ZhaoHao
 * @date: 2016-11-23 16:21
 */
public class HotSearchBean {
    private static final String TAG = "HotSearchBean";

    public static class Input extends BaseInput<Object> {

        private String keyword;
        private String name;
        private int page;
        private int rows;

        private Input(){
            super("http://www.tngou.net/api/search", Request.Method.GET,HotSearchBean.class);
        }

       public static BaseInput<Object> buildInput(String keyword, String name, int page, int rows){
           Input input = new Input();
           input.keyword = keyword;
           input.name = name;
           input.page = page;
           input.rows = rows;
           return input;
       }
    }

    @SerializedName(value = "status")
    public boolean status;

    @SerializedName(value = "total")
    public int total;

    @SerializedName(value = "tngou")
    public List<TianGouBean> tianGouList;

    public class TianGouBean{
        public String title;//资讯标题
        public int topclass;//一级分类
        public String img;//图片
        public String description;//描述
        public String keywords;//关键字
        public String message;//资讯内容
        public int count ;//访问次数
        public int fcount;//收藏数
        public int rcount;//评论读数
        public String fromname;
        public String fromurl;
        public long time;
    }

}
