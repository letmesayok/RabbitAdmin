package com.rabbit.framework.manager;

/**
 * @author wangql
 * 异步任务管理器
 */
public class AsyncManager {

    private static AsyncManager asyncManager = new AsyncManager();
    private AsyncManager(){};
    public static AsyncManager me() {
        return asyncManager;
    }
}
