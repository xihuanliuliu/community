package com.imooc.ecommerce.util;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.imooc.ecommerce.dto.PageDto;
import com.imooc.ecommerce.vo.PageVO;

/**
 * 一对一分页
 */
public class PageUtil {
    public static <T> PageVO<T> doPage(PageDto pageDto, ISelect select) {
        PageSerializable<T> simplePageInfo = PageHelper.startPage(pageDto).doSelectPageInfo(select);

        PageVO<T> pageVO = new PageVO<>();
        pageVO.setList(simplePageInfo.getList());
        pageVO.setTotal(simplePageInfo.getTotal());
        pageVO.setPages(getPages(simplePageInfo.getTotal(), pageDto.getPageSize()));
        return pageVO;
    }

    public static Integer getPages(long total, Integer pageSize) {

        if (total == -1) {
            return 1;
        }
        if (pageSize > 0) {
            return (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        }
        return 0;
    }
}
