package com.rabbit.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程操作工具类
 * @author ruoyi
 */
@Slf4j
public class ThreadUtils {
    private static final int WAIT_SECONDS = 120;

    /**
     * sleep 等待
     * @param time 毫秒
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试停止线程池
     * 首先使用 shutdown 停止接收新任务并尝试完成已有任务
     * 如果超时，则调用 shutdownNow
     * 如果还是超时，强制退出
     * @param pool 线程池
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if(pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if(pool.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if(pool.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS)) {
                        log.info("Pool did not terminate");
                    }
                }
            } catch (InterruptedException e) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
