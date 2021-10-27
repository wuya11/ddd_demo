package com.ddd.demo.configuration.executor;


import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 包装Callable,实现在线程池中复用线程也能父子线程共享数据
 *
 * 区别于InheritableThreadLocal,这个只能实现一次性创建线程父子共享数据
 *
 * @author wl
 * @date 2021-2-4
 */
public class RequestContextCallable<K> implements Callable<K> {
    private final Callable<K> actual;
    /**
     * 检测是否已经包装过,避免重复包装,造成内存浪费
     */
    private final boolean wrapped;
    private final UncaughtExceptionHandler uncaughtExceptionHandler;
    private Map<Object, Object> values;
//    private User user;

    public RequestContextCallable(Callable<K> actual) {
        this(actual, null);
    }

    public RequestContextCallable(Callable<K> actual, UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.actual = actual;
        this.wrapped = actual instanceof RequestContextCallable;
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        if (!wrapped) {
//            this.values = ThreadHolderUtil.getValueMap();
//            this.user = ThreadHolderUtil.getUser();
        }
    }

    @Override
    public K call() throws Exception {
        try {
            if (!wrapped) {
//                ThreadHolderUtil.setValueMap(values);
//                ThreadHolderUtil.setUser(user);
            }
            return actual.call();
        } catch (Throwable ex) {
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(ex);
            }
            throw ex;
        } finally {
            if (!wrapped) {
//                ThreadHolderUtil.clearValueMap();
//                ThreadHolderUtil.clearUser();
            }
        }
    }
}