package com.fangyu.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fangyu.pmp.model.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 * Created by fangyu on 2019/10/17.
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    // 查询用户的所有权限
    List<String> queryAllPerms(Long userId);

    // 查询用户的所有权限
    List<Long> queryAllMenuId(Long userId);
}