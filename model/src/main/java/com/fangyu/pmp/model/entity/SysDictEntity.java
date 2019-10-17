package com.fangyu.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by fangyu on 2019/10/17.
 */
@Data
@TableName(value = "sys_dict")
public class SysDictEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 字典类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 字典码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 字典值
     */
    @TableField(value = "value")
    private String value;

    /**
     * 排序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 删除标记  -1：已删除  0：正常
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_TYPE = "type";

    public static final String COL_CODE = "code";

    public static final String COL_VALUE = "value";

    public static final String COL_ORDER_NUM = "order_num";

    public static final String COL_REMARK = "remark";

    public static final String COL_DEL_FLAG = "del_flag";
}