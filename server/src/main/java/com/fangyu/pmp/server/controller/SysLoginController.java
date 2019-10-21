package com.fangyu.pmp.server.controller;

import com.fangyu.pmp.common.response.BaseResponse;
import com.fangyu.pmp.common.response.StatusCode;
import com.fangyu.pmp.server.shiro.ShiroUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

//import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/17 6:33 下午
 */
@Controller
public class SysLoginController extends AbstractController {

    @Autowired
    private Producer producer;

    /**
     * 生成验证码 ~ 方式一
     */
//    @RequestMapping("captcha.jpg")
//    public void captcha(HttpServletResponse response) throws Exception{
//        byte[] captchaChallengeAsJpeg = null;
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        try {
//            // 生产验证码字符串并保存到session中
//            String createText = producer.createText();
//            ShiroUtil.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
//            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = producer.createImage(createText);
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//        } catch (IllegalArgumentException e) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        response.setHeader("Cache-Control", "no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream = response.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
//    }

    /**
     * 生成验证码 ~ 方式二
     * @param response 返回的Response
     * @throws Exception 通用异常
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws Exception {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到shiro session
        ShiroUtil.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        log.info("验证码 ==> {}", text);
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @return
     */
    @RequestMapping(value = "/sys/login")
    //    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(String username, String password, String captcha) {
        log.info("用户名：{} 密码：{} 验证码：{}", username, password, captcha);

        // 校验验证码
        String kaptcha = ShiroUtil.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equals(captcha)){
            return new BaseResponse(StatusCode.InvalidCode);
        }

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
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
            }
        } catch (UnknownAccountException e) {
            log.error("e ==> {}", e.getMessage());
            return new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        } catch (IncorrectCredentialsException e) {
            log.error("e ==> {}", e.getMessage());
            return new BaseResponse(StatusCode.AccountPasswordNotMatch);
        } catch (LockedAccountException e) {
            log.error("e ==> {}", e.getMessage());
            return new BaseResponse(StatusCode.AccountHasBeenLocked);
        } catch (AuthenticationException e) {
            log.error("e ==> {}", e.getMessage());
            return new BaseResponse(StatusCode.AccountValidateFail);
        }

        return new BaseResponse(StatusCode.Success);
    }


    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        // 销毁当前shiro的用户的session
        ShiroUtil.logout();
        return "redirect:/login.html";
    }
}
