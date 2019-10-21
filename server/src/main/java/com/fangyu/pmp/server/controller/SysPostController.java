package com.fangyu.pmp.server.controller;

import com.fangyu.pmp.common.response.BaseResponse;
import com.fangyu.pmp.common.response.StatusCode;
import com.fangyu.pmp.common.utils.PageUtil;
import com.fangyu.pmp.common.utils.ValidatorUtil;
import com.fangyu.pmp.model.entity.SysPostEntity;
import com.fangyu.pmp.server.service.SysPostService;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            PageUtil page = postService.queryPage(paramMap);
            resMap.put("page", page);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail);
        }
        response.setData(resMap);
        return response;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse save(@RequestBody @Validated SysPostEntity entity, BindingResult result) {
        String res = ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(), res);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            postService.savePost(entity);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

}
