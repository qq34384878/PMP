package com.fangyu.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fangyu.pmp.model.entity.SysRoleDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色与部门关联关系
 * Created by fangyu on 2019/10/17.
 */
@Mapper
public interface SysRoleDeptDao extends BaseMapper<SysRoleDeptEntity> {

    // 根据角色ID，获取部门id列表
    List<Long> queryDeptIdList(Long[] roleIds);

    // 根据角色ID，批量删除
    int deleteBatch(@Param("roleIds")String roleIds);
}