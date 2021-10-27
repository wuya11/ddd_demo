package com.ddd.demo.ddd.infrastructure.compensate.exception;

import com.ddd.demo.util.BusinessDataIllegalException;

import java.util.List;

/**
 * 售后补偿异常封装
 *
 * @author wl
 * @date 2021/8/26
 */
public class CompensateException extends BusinessDataIllegalException {

    private final static String COMPENSATE_EXCEPTION = "compensate_exc";

    public CompensateException(CompensateFailEnum compensateFailEnum) {
        super(compensateFailEnum.getErrorCode(), compensateFailEnum.getErrorMessage());
    }

    public CompensateException(String message) {
        super(COMPENSATE_EXCEPTION, message);
    }

    public CompensateException(String code, String message) {
        super(code, message);
    }

    public CompensateException(String code, String message, List<Error> errors) {
        super(code, message, errors);
    }

    public CompensateException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}
