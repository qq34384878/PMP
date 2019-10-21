package com.fangyu.pmp.common.filter;

import com.fangyu.pmp.common.exception.CommonException;
import org.apache.commons.lang.StringUtils;

/**
 * SQL过滤器
 *
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/21 10:10 下午
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     * @return String
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        // 去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        // 转换成小写
        str = str.toLowerCase();

        // 非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        // 判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.contains(keyword)){
                throw new CommonException("(╯﹏╰)有SQL注入风险：包含非法字符 ==> "+keyword);
            }
        }
        return str;
    }
}
