package com.ddd.demo.configuration.executor;

/**
 * 异步异常处理
 *
 * @author wl
 * @date 2021-2-4
 */
@FunctionalInterface
public interface UncaughtExceptionHandler {
    /**
     * 异常处理
     *
     * @param e 异常
     */
    void uncaughtException(Throwable e);
}
