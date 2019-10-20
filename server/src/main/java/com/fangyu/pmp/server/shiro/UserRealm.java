package com.fangyu.pmp.server.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fangyu.pmp.model.entity.SysUserEntity;
import com.fangyu.pmp.model.mapper.SysUserDao;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/17 7:37 下午
 */

@Component
public class UserRealm extends AuthorizingRealm {

    /**
     * logger
     */
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 资源-权限分配 ~ 授权
     *
     * @param principalCollection 资源收集
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 用户认证 ~ 登录认证
     *
     * @param authenticationToken 身份验证token
     * @return 身份认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        final String userName = token.getUsername();
        final String password = String.valueOf(token.getPassword());

        log.info("用户名: {}, 密码: {} ", userName, password);

        SysUserEntity entity = sysUserDao.selectOne(new QueryWrapper<SysUserEntity>().eq("username", userName));
//        SysUserEntity entity = sysUserDao.selectByUserName(userName); //演示sql注入攻击
        log.info("entity => {}", entity);
        // 账号不存在
        if (entity == null) {
            throw new UnknownAccountException("账号不存在！");
        }

        // 账户被禁用
        if (0 == entity.getStatus()) {
            throw new DisabledAccountException("账户已被禁用，请联系管理员");
        }
        //第一种 : 明文匹配
        //账户名密码不匹配
//        if (!entity.getPassword().equals(password)){
//            throw new IncorrectCredentialsException("账户密码不匹配!");
//        }
//        return new SimpleAuthenticationInfo(entity,password,getName());

        //第三种验证逻辑
        String realPassword=ShiroUtil.sha256(password,entity.getSalt());
        if (StringUtils.isBlank(realPassword) || !realPassword.equals(entity.getPassword())){
            throw new IncorrectCredentialsException("账户密码不匹配!");
        }
        return new SimpleAuthenticationInfo(entity, password,getName());

        //第二种验证逻辑-交给shiro的密钥匹配器去实现
//        return new SimpleAuthenticationInfo(entity, entity.getPassword(), ByteSource.Util.bytes(entity.getSalt()), getName());
    }

    /**
     * 设置密码匹配器
     * 密码验证器~匹配逻辑 ~ 第二种验证逻辑
     * @param credentialsMatcher 凭证匹配器
     */
//    @Override
//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
//        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
//        shaCredentialsMatcher.setHashIterations(ShiroUtil.hashIterations);
//        super.setCredentialsMatcher(credentialsMatcher);
//    }
}
