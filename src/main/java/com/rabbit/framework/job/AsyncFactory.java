package com.rabbit.framework.job;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rabbit.common.utils.SpringUtils;
import com.rabbit.project.domain.SysOperLog;
import com.rabbit.project.service.ISysOperLogService;


import java.util.TimerTask;

/**
 * 异步任务工厂类
 * @author wangql
 */
public class AsyncFactory {


    /**
     * 保存操作日志记录
     * @param log log对象
     * @return 任务类
     */
    public static TimerTask saveOperatorLog(SysOperLog log) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(ISysOperLogService.class).save(log);
            }
        };
    }
}
