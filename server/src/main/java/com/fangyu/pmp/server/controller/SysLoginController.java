package com.fangyu.pmp.server.controller;

import com.fangyu.pmp.common.response.BaseResponse;
import com.fangyu.pmp.common.response.StatusCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/17 6:33 下午
 */
@Controller
public class SysLoginController extends AbstractController{

    /**
     * 登录
     * @param username
     * @param password
     * @param captcha
     * @return
     */
    @RequestMapping(value = "/sys/login")
    //    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(String username, String password, String captcha){
        log.info("用户名：{} 密码：{} 验证码：{}", username, password, captcha);

        try {
//            From http://shiro.apache.org  文档
//            if ( !currentUser.isAuthenticated() ) {
//                //collect user principals and credentials in a gui specific manner
//                //such as username/password html form, X509 certificate, OpenID, etc.
//                //We'll use the username/password example here since it is the most common.
//                //(do you know what movie this is from? ;)
//                UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
//                //this is all you have to do to support 'remember me' (no config - built in!):
//                token.setRememberMe(true);
//                currentUser.login(token);
//            }

            // 提交登录
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()){
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
            }
        } catch (Exception e){
            log.error("e ==> {}", e.getMessage());
            return new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }

        return new BaseResponse(StatusCode.Success);
    }
}
