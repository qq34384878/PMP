package com.fangyu.pmp.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fangyu.pmp.model.entity.SysUserEntity;

/**
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/21 6:32 下午
 */
public interface SysUserService extends IService<SysUserEntity> {

    // 修改密码
    boolean updatePassword(Long userId, String oldPassword, String newPassword);
}
