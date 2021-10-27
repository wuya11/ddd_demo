package com.ddd.demo.configuration.executor;



import java.util.Map;

/**
 * 包装Runnable,实现在线程池中复用线程也能父子线程共享数据
 *
 * 区别于InheritableThreadLocal,这个只能实现一次性创建线程父子共享数据
 *
 * @author wl
 * @date 2021-2-4
 */
public class RequestContextRunnable implements Runnable {
    private final Runnable actual;
    /**
     * 检测是否已经包装过,避免重复包装,造成内存浪费
     */
    private final boolean wrapped;
    private final UncaughtExceptionHandler uncaughtExceptionHandler;
    /**
     * //TODO 后期考虑要不要使用SoftReference
     */
    private Map<Object, Object> values;
//    private User user;

    public RequestContextRunnable(Runnable actual) {
        this(actual, null);
    }

    public RequestContextRunnable(Runnable actual, UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.actual = actual;
        this.wrapped = actual instanceof RequestContextRunnable;
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        if (!wrapped) {
//            this.values = ThreadHolderUtil.getValueMap();
//            this.user = ThreadHolderUtil.getUser();
        }
    }

    @Override
    public void run() {
        try {
            if (!wrapped) {
//                ThreadHolderUtil.setValueMap(values);
//                ThreadHolderUtil.setUser(user);
            }
            actual.run();
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
