package com.fangyu.pmp.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fangyu.pmp.common.filter.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 查询统一封装的工具类
 *
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/21 9:36 下午
 */
public class QueryUtil<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        // 分页参数
        long curPage = 1;
        long limit = 10;

        if (params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constant.LIMIT));
        }

        // 分页对象
        Page<T> page = new Page<>(curPage, limit);
        // 分页参数
        params.put(Constant.PAGE, page);

        // 排序字段
        // 防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject((String) params.get(Constant.ORDER_FILED));
        String order = (String) params.get(Constant.ORDER);

        // 前端字段排序
        if (StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)) {
            if (Constant.ASC.equalsIgnoreCase(order)) {
                return page.setAsc(orderField);
            } else {
                return page.setDesc(orderField);
            }
        }
        // 默认排序
        if (isAsc) {
            page.setAsc(defaultOrderField);
        } else {
            page.setDesc(defaultOrderField);
        }
        return page;
    }

    // 重载的查询
    public IPage<T> getQueryPage(Map<String, Object> params) {
        // 当前第几页、每页显示的条目
        long curPage = 1;
        long limit = 10;

        if (params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong(params.get(Constant.PAGE).toString());
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong(params.get(Constant.LIMIT).toString());
        }

        // 分页对象
        Page<T> page = new Page<>(curPage, limit);

        // 设置前端请求的字段排序 asc or desc
        if (params.get(Constant.ORDER) != null && params.get(Constant.ORDER_FILED) != null) {
            SQLFilter.sqlInject((String) params.get(Constant.ORDER_FILED));

            if (Constant.ASC.equalsIgnoreCase(params.get(Constant.ORDER).toString())) {
                return page.setAsc(params.get(Constant.ORDER_FILED).toString());
            } else {
                return page.setDesc(params.get(Constant.ORDER_FILED).toString());
            }
        }
        return page;
    }
}
