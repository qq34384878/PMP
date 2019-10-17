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
@TableName(value = "sys_menu")
public class SysMenuEntity {
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 菜单URL
     */
    @TableField(value = "url")
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 排序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    public static final String COL_MENU_ID = "menu_id";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_NAME = "name";

    public static final String COL_URL = "url";

    public static final String COL_PERMS = "perms";

    public static final String COL_TYPE = "type";

    public static final String COL_ICON = "icon";

    public static final String COL_ORDER_NUM = "order_num";
}