package com.imooc.ecommerce.util;

import com.imooc.ecommerce.dto.PageDto;

public class PageAdapter {

    private int begin;
    private int size;

    public PageAdapter(PageDto pageDto) {
        this.begin = getStart(pageDto.getPageNum() - 1, pageDto.getPageSize());
        this.size = pageDto.getPageSize();
    }

    public static int getStart(int pageNo, int pageSize) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        if (pageNo < 1) {
            pageNo = 0;
        }
        return pageNo * pageSize;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
