package com.fangyu.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * Created by fangyu on 2019/10/17.
 */
@Data
@TableName(value = "attend_record")
public class AttendRecordEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 部门id
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 打开开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 打卡结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 工时/小时
     */
    @TableField(value = "total")
    private BigDecimal total;

    /**
     * 状态（1=已打卡;0=未打卡）
     */
    @TableField(value = "status")
    private Byte status;

    /**
     * 日期
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_START_TIME = "start_time";

    public static final String COL_END_TIME = "end_time";

    public static final String COL_TOTAL = "total";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}