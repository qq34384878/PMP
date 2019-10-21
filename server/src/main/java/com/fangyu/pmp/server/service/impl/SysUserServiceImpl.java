package com.fangyu.pmp.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fangyu.pmp.model.entity.SysUserEntity;
import com.fangyu.pmp.model.mapper.SysUserDao;
import com.fangyu.pmp.server.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/21 6:34 下午
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    // 更新密码 ~ 借助于mybatis-plus
    @Override
    public boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        SysUserEntity entity = new SysUserEntity();
        entity.setPassword(newPassword);
        return this.update(entity, new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", oldPassword));
    }
}
