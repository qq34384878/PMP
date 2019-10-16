package com.fangyu.pmp.server.controller;

import com.fangyu.pmp.common.response.BaseResponse;
import com.fangyu.pmp.common.response.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/16 9:55 下午
 */
@Controller
@RequestMapping("/base")
public class BaseController {

    private static final Logger Log = LoggerFactory.getLogger(BaseController.class);

    /**
     * 第一个案例
     * @param name
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse info(String name){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if (StringUtils.isBlank(name)){
            name = "权限管理平台1";
        }
        response.setData(name);

        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String page(String name, ModelMap modelMap){
        if (StringUtils.isBlank(name)){
            name = "权限管理平台2";
        }
        modelMap.put("name", name);
        modelMap.put("app", name);
        return "pageOne";

    }

}
