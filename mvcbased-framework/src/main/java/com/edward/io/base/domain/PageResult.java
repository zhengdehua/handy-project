package com.edward.io.base.domain;

import java.util.List;

/**
 * Created by edwardcheng on 2017/9/10.
 */
public class PageResult<T extends BaseEntity> {

    private int total;
    private PageParam pageParam;
    private List<T> list;

    public PageResult(int total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
