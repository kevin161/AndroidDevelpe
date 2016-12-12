package com.gyz.androiddevelope.response_bean;

import java.util.List;

public class SearchHintBean {


    /**
     * total : 277
     * result : ["人物","人设","人物海报","人像","人物插画","人物素材","人物摄影","人生","人群","人物介绍"]
     */

    private int total;
    private List<String> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
