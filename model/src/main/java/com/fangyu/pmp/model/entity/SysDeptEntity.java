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
@TableName(value = "sys_dept")
public class SysDeptEntity {
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    /**
     * 上级部门ID，一级部门为0
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 部门名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 排序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_NAME = "name";

    public static final String COL_ORDER_NUM = "order_num";

    public static final String COL_DEL_FLAG = "del_flag";
}