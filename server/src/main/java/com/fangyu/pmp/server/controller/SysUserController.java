package com.fangyu.pmp.server.controller;

import com.fangyu.pmp.common.response.BaseResponse;
import com.fangyu.pmp.common.response.StatusCode;
import com.google.common.collect.Maps;
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
}
