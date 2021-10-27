package com.ddd.demo.configuration;

import com.ddd.demo.configuration.executor.ThreadPoolExecutorAdapter;
import com.ddd.demo.configuration.executor.second.TaskAdapterDecorator;
import org.springframework.aop.interceptor.AsyncExecutionAspectSupport;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步线程池使用
 *
 * @author wl
 * @date 2020-03-31
 */
@Configuration
public class AsyncConfiguration implements AsyncConfigurer {
    @Bean(name = AsyncExecutionAspectSupport.DEFAULT_TASK_EXECUTOR_BEAN_NAME)
    public ThreadPoolExecutor executor() {
        ThreadPoolExecutorAdapter threadPoolExecutor = new ThreadPoolExecutorAdapter(2, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.setUncaughtExceptionHandler(e -> System.out.println("执行tool-study异步任务失败" + e));
        return threadPoolExecutor;
    }

    /**
     * 基于JDK底层 重新传入参数，保证调用链信息或用户信息，可以传递到子线程
     */
    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }


    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> System.out.print(String.format("执行异步任务'%s'", method + ex.toString()));
    }

    /**
     * 普通自定义线程池，便于测试，说明子线程拿不到扩展信息
     */
    @Bean(name = "task_normal")
    public Executor getTaskNormalExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(400);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        String threadNamePrefix = "task_normal-";
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 使用自定义的跨线程的请求级别线程工厂类19
        taskExecutor.setAwaitTerminationSeconds(3);
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 基于spring 装饰器模式，该方案也可以拿到主线程传入的信息
     */
    @Bean(name = "decorator_task")
    public Executor getDecoratorExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(400);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        String threadNamePrefix = "decorator_task-";
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 使用自定义的跨线程的请求级别线程工厂类19
        taskExecutor.setAwaitTerminationSeconds(3);
        //task装饰【重点】
        taskExecutor.setTaskDecorator(new TaskAdapterDecorator());
        taskExecutor.initialize();
        return taskExecutor;
    }
}