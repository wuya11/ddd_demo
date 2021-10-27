package com.ddd.demo.configuration.executor;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 线程池适配器
 *
 * @author wl
 * @date 2021-2-4
 */
public class ThreadPoolExecutorAdapter extends ThreadPoolExecutor {
    private UncaughtExceptionHandler uncaughtExceptionHandler = new DefaultUncaughtExceptionHandler();

    public ThreadPoolExecutorAdapter(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadPoolExecutorAdapter(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadPoolExecutorAdapter(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadPoolExecutorAdapter(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(new RequestContextCallable<>(task, uncaughtExceptionHandler));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return super.submit(new RequestContextRunnable(task, uncaughtExceptionHandler), result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(new RequestContextRunnable(task, uncaughtExceptionHandler));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return super.invokeAll(tasks.stream().map(task -> new RequestContextCallable<>(task, uncaughtExceptionHandler)).collect(Collectors.toList()));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return super.invokeAll(tasks.stream().map(task -> new RequestContextCallable<>(task, uncaughtExceptionHandler)).collect(Collectors.toList()), timeout, unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return super.invokeAny(tasks.stream().map(task -> new RequestContextCallable<>(task, uncaughtExceptionHandler)).collect(Collectors.toList()));
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return super.invokeAny(tasks.stream().map(task -> new RequestContextCallable<>(task, uncaughtExceptionHandler)).collect(Collectors.toList()), timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(new RequestContextRunnable(command, uncaughtExceptionHandler));
    }

    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return uncaughtExceptionHandler;
    }

    public void setUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }
}
