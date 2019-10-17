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
@TableName(value = "sys_post")
public class SysPostEntity {
    /**
     * 岗位ID
     */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

    /**
     * 岗位编码
     */
    @TableField(value = "post_code")
    private String postCode;

    /**
     * 岗位名称
     */
    @TableField(value = "post_name")
    private String postName;

    /**
     * 排序号
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 状态（1正常 0停用）
     */
    @TableField(value = "status")
    private Byte status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    public static final String COL_POST_ID = "post_id";

    public static final String COL_POST_CODE = "post_code";

    public static final String COL_POST_NAME = "post_name";

    public static final String COL_ORDER_NUM = "order_num";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_REMARK = "remark";
}