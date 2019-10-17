package com.fangyu.pmp.server.controller;

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
}
