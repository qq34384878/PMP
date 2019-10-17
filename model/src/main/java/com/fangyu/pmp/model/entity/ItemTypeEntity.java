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
@TableName(value = "item_type")
public class ItemTypeEntity {
    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;

    /**
     * 类型编码
     */
    @TableField(value = "type_code")
    private String typeCode;

    /**
     * 类型名称
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 排序编号
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 状态（1=启用；0=禁用）
     */
    @TableField(value = "status")
    private Byte status;

    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_TYPE_ID = "type_id";

    public static final String COL_TYPE_CODE = "type_code";

    public static final String COL_TYPE_NAME = "type_name";

    public static final String COL_ORDER_NUM = "order_num";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}