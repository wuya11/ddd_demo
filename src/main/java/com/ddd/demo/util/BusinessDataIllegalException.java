package com.ddd.demo.util;

import java.util.List;

/**
 * 业务数据非法异常
 *
 * @author wl
 */
public class BusinessDataIllegalException extends IllegalArgumentException {
    /**
     * 业务状态码
     */
    private String code;
    /**
     * 错误列表
     */
    private List<Error> errors;

    /**
     * @deprecated 此异常适用于与前端状态码耦合的异常，如果仅仅是为了抛出系统内部业务异常，建议使用自定义异常
     */
    @Deprecated
    public BusinessDataIllegalException(String message) {
        super(message);
    }

    public BusinessDataIllegalException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @deprecated 此异常适用于与前端状态码耦合的异常，如果仅仅是为了抛出系统内部业务异常，建议使用自定义异常
     */
    @Deprecated
    public BusinessDataIllegalException(Throwable throwable) {
        super(throwable);
    }

    /**
     * @deprecated 此异常适用于与前端状态码耦合的异常，如果仅仅是为了抛出系统内部业务异常，建议使用自定义异常
     */
    @Deprecated
    public BusinessDataIllegalException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BusinessDataIllegalException(String code, String message, List<Error> errors) {
        this(code, message);
        this.errors = errors;
    }

    public BusinessDataIllegalException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }
}

