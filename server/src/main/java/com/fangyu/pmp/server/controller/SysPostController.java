package com.fangyu.pmp.server.controller;

import com.fangyu.pmp.common.response.BaseResponse;
import com.fangyu.pmp.common.response.StatusCode;
import com.fangyu.pmp.server.service.SysPostService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/21 9:11 下午
 */
@RestController
@RequestMapping("/sys/post")
public class SysPostController extends AbstractController {

    @Autowired
    private SysPostService postService;

    // 分页列表模糊查询
    @RequestMapping("/list")
    public BaseResponse list(@RequestParam Map<String, Object> paramMap) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String, Object> resMap = Maps.newHashMap();
        try {
            postService.queryPage(paramMap);
        } catch (Exception e){
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

}
