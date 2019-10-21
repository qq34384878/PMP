package com.fangyu.pmp.server.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/17 5:02 下午
 */

@Controller
public class SysPageController {

    @RequestMapping("modules/{module}/{page}.html")
    public String page(@PathVariable String module, @PathVariable String page){
        return "modules/" + module + "/" + page;
    }

    @RequestMapping(value = {"index.html", "/"})
    public String index(){
        return "index";
    }

    @RequestMapping("login.html")
    public String login(){
        // 如果Shiro的安全验证工具类确认已经登录，直接重定向到首页
        if (SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:index.html";
        }
        return "login";
    }

    @RequestMapping("main.html")
    public String main(){
        return "main";
    }

    @RequestMapping("404.html")
    public String notFound(){
        return "404";
    }
}
