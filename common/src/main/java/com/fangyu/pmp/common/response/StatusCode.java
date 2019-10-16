package com.fangyu.pmp.common.response;

/**
 * 通用响应状态码enum
 * @author fangyu
 * @version v1.0.0
 * @since 2019/10/16 9:47 下午
 */
public enum  StatusCode {
    Success(0, "成功"),
    Fail(-1, "失败"),

    InvalidParams(201, "非法的参数!"),
    UserNotLogin(202, "用户未登录"),

    UnknownError(500, "未知异常，请联系管理员!"),
    InvalidCode(501, "验证码不正确!"),
    AccountPasswordNotMatch(502, "账号密码不匹配!"),
    AccountHasBeenLocked(503, "账号已被锁定，请联系管理员!"),
    AccountValidateFail(504, "账号已被锁定，请联系管理员!"),

    PasswordCanNotBlank(1000, "密码不能为空!"),
    OldPasswordNotMatch(1001, "原密码不正确!"),
    UpdatePasswordFail(1002, "修改密码失败，请联系管理员!"),

    SysUserCanNotBeDelete(1003, "系统管理员不能被删除!"),
    CurrUserCanNotBeDelete(1004, "当前用户不能删除自己!"),

    DeptHasSubDeptCanNotBeDelete(2001, "该部门下包含有子部门，请先删除子部门!"),

    SpecificMenuCanNotBeDelete(3001, "指定的菜单不能被删除，要删除就数据库删除~(=@__@=)!"),

    PostCodeHasExist(4001, "岗位编码已存在!"),
    ;

    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
