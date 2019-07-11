package com.example.webflux.demo.constant;

/**
 * 错误码相关
 * @author lwk
 * @date 2019-05-06 17:15
 */
public enum Code {
    /**
     * 错误码相关
     */
    SUCCESS("0000", "操作成功"),
    SYSTEM_ERROR("-9999", "系统异常"),
    PARAMETER_IS_NULL("-0001", "参数为空"),
    TOKEN_IS_NULL("-0002", "登录状态已失效"),
    USER_NOT_EXIST("-0003", "用户不存在"),
    DUPLICATE_SUBMISSION("-0004", "重复提交"),
    PASSWORD_IS_INCORRECT("-0005", "密码不正确"),
    MOBILE_FORMAT_INCORRECT("-0006", "手机号码格式不正确"),
    USER_EXIST("-0007", "用户已存在"),
    ;

    private String code;
    private String msg;

    Code(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
