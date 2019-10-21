package com.fangyu.pmp.common.utils;

import org.springframework.validation.BindingResult;

/**
 * 请求参数统一校验工具
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/22 12:09 上午
 */
public class ValidatorUtil {

    // 统一处理校验的结果
    public static String checkResult(BindingResult result){
        StringBuilder sb = new StringBuilder("");
        if (result != null && result.hasErrors()){
//            List<ObjectError> errors = result.getAllErrors();
//            for(ObjectError error: errors){
//                sb.append(error.getDefaultMessage()).append("\n");
//            }
            // java8 stream lamda
            result.getAllErrors().stream().forEach(objectError -> sb.append(objectError.getDefaultMessage()).append("\n"));
        }
        return sb.toString();
    }
}
