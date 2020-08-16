package com.nfnote.exception;

/**
 * Created by codedrinker on 2019/5/28.
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    INVALID_URL(10001, "兄弟，你输入的是链接么？"),
    SYS_ERROR(10002, "兄弟，你都把系统搞坏了！要不然重试一下吧"),
    INVALID_REQUEST(10003, "操作太快了，慢点呗兄弟！"),;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
