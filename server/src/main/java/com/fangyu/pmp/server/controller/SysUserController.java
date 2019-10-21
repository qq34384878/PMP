package com.fangyu.pmp.server.controller;

import com.fangyu.pmp.common.response.BaseResponse;
import com.fangyu.pmp.common.response.StatusCode;
import com.fangyu.pmp.model.entity.SysUserEntity;
import com.fangyu.pmp.server.service.SysUserService;
import com.fangyu.pmp.server.shiro.ShiroUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户管理Controller
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/21 4:16 下午
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController{

    @Autowired
    private SysUserService userService;

    /**
     * 获取当前用户信息
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public BaseResponse currInfo(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String, Object> resMap = Maps.newHashMap();
        try {
            // 获取当前用户的登录详情
            log.info("entity ==> {}", getCurrLoginUser());
            resMap.put("user", getCurrLoginUser());
        } catch (Exception e){
            return new BaseResponse(StatusCode.Fail);
        }
        response.setData(resMap);
        return response;
    }

    /**
     * 获取当前用户
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public BaseResponse updatePassword(String password, String newPassword){
        if (StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)){
            return new BaseResponse(StatusCode.PasswordCanNotBlank);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            // 真正的校验处理逻辑，先校验旧密码是否正确，再更新新的密码
            SysUserEntity entity = getCurrLoginUser();
            final String salt = entity.getSalt();

            // 网络请求传输的旧密码进行加盐加密处理转换
            String oldPassword = ShiroUtil.sha256(password, salt);
            if (!entity.getPassword().equals(oldPassword)){
                return new BaseResponse(StatusCode.OldPasswordNotMatch);
            }
            newPassword = ShiroUtil.sha256(newPassword, salt);
            // 执行更新密码的逻辑
            log.info("O(∩_∩)O哈哈~旧密码正确，开始更新新的密码");
            userService.updatePassword(entity.getUserId(), oldPassword, newPassword);

        } catch (Exception e){
            response = new BaseResponse(StatusCode.UpdatePasswordFail);
        }
        return response;
    }
}
