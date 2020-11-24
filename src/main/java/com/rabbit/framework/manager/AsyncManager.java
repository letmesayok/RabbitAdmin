package com.rabbit.framework.manager;

import java.util.TimerTask;

/**
 * @author wangql
 * 异步任务管理器
 */
public class AsyncManager {

    private final int DELAY_TIME = 10;
    private static AsyncManager asyncManager = new AsyncManager();
    private AsyncManager(){};

    /**
     * 单例模式
     * @return 获取实例
     */
    public static AsyncManager getInstance() {
        return asyncManager;
    }

    public void execute(TimerTask task) {

    }
}
