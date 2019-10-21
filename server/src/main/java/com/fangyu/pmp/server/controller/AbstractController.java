package com.fangyu.pmp.server.controller;

import com.fangyu.pmp.model.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * 抽象基类
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/17 6:34 下午
 */
@Controller
public abstract class AbstractController {
    /** logger */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    // 获取当前登录用户的详情
    SysUserEntity getCurrLoginUser(){
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    // 获取当前登录用户的id
    protected Long getUserId() {
        return getCurrLoginUser().getUserId();
    }

    // 获取当前登录用户的名字
    protected String getUserName() {
        return getCurrLoginUser().getUsername();
    }

    // 获取

}
