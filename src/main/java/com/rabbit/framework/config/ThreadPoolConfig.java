package com.rabbit.framework.config;

import com.rabbit.common.utils.ThreadUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 * @author ruoyi
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 核心线程池大小
     */
    private int corePoolSize = 50;
    /**
     * 最大可创建线程数
     */
    private int maxPoolSize = 200;
    /**
     * 队列最大长度
     */
    private int queueCapacity = 1000;
    /**
     * 线程池维护允许的空闲时间
     */
    private int keepAliveSeconds = 300;

    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean("scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        BasicThreadFactory basicThreadFactory = new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build();
        return new ScheduledThreadPoolExecutor(corePoolSize, basicThreadFactory);
    }
}
