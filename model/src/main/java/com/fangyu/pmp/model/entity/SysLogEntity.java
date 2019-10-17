package com.fangyu.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * Created by fangyu on 2019/10/17.
 */
@Data
@TableName(value = "sys_log")
public class SysLogEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户操作
     */
    @TableField(value = "operation")
    private String operation;

    /**
     * 请求方法
     */
    @TableField(value = "method")
    private String method;

    /**
     * 请求参数
     */
    @TableField(value = "params")
    private String params;

    /**
     * 执行时长(毫秒)
     */
    @TableField(value = "time")
    private Long time;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 创建时间
     */
    @TableField(value = "create_date")
    private Date createDate;

    public static final String COL_ID = "id";

    public static final String COL_USERNAME = "username";

    public static final String COL_OPERATION = "operation";

    public static final String COL_METHOD = "method";

    public static final String COL_PARAMS = "params";

    public static final String COL_TIME = "time";

    public static final String COL_IP = "ip";

    public static final String COL_CREATE_DATE = "create_date";
}