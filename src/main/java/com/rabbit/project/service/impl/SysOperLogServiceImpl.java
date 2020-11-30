package com.rabbit.project.service.impl;

import com.rabbit.project.domain.SysOperLog;
import com.rabbit.project.mapper.SysOperLogMapper;
import com.rabbit.project.service.ISysOperLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author wangql
 * @since 2020-11-30
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {

}
