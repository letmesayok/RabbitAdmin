package com.rabbit.framework.job;

import com.rabbit.common.utils.SpringUtils;
import com.rabbit.common.utils.ThreadUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wangql
 * 异步任务管理器
 */
public class AsyncManager {

    /**
     * 操作延迟 10 ms
     */
    private final int DELAY_TIME = 10;
    private static AsyncManager asyncManager = new AsyncManager();
    private AsyncManager(){};

    private ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    /**
     * 单例模式
     * @return 获取实例
     */
    public static AsyncManager getInstance() {
        return asyncManager;
    }

    /**
     * 执行任务
     * @param task 任务
     */
    public void execute(TimerTask task) {
        executor.schedule(task, DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        ThreadUtils.shutdownAndAwaitTermination(executor);
    }
}
