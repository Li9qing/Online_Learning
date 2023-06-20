package edu.hubu.common.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * PageParams for
 *
 * @Author yruns
 * @Version 2023/6/20
 */
@Data
public class PageParams {

    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 页大小
     */
    private int pageSize;

    /**
     * 从Map中获取分页参数
     */
    public static PageParams parsePageParams(Map<String, Object> params) {
        int pageNum, pageSize;
        try {
            pageNum = Integer.parseInt(String.valueOf(params.get(Constant.PAGE)));
            pageSize = Integer.parseInt(String.valueOf(params.get(Constant.LIMIT)));
        } catch (Exception e) {
            pageNum = 1;
            pageSize = Constant.DEFAULT_SIZE;
        }

        PageParams pageParams = new PageParams();
        pageParams.setPageSize(pageSize);
        pageParams.setPageNum(pageNum);
        return pageParams;
    }
}
