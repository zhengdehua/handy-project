package com.edward.io.base.domain;

/**
 * Created by edwardcheng on 2017/9/10.
 */
public class PageParam {

    private int offset;
    private int limit;

    public PageParam(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
