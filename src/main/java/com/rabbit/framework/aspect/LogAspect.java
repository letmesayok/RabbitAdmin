package com.rabbit.framework.aspect;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rabbit.common.constants.CommonCode;
import com.rabbit.framework.annotation.Log;
import com.rabbit.framework.job.AsyncFactory;
import com.rabbit.framework.job.AsyncManager;
import com.rabbit.project.domain.SysOperLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * @author wangql
 * 日志切面类，用于保存日志
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.rabbit.framework.annotation.Log)")
    public void logPointCut() {}

    /**
     * 正常处理
     * @param joinPoint 切点
     * @param object 参数
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "object")
    public void doAfterReturning(JoinPoint joinPoint, Object object) {
        handleLog(joinPoint, null, object);
    }

    /**
     * 异常处理
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    /**
     * 日志处理
     * @param joinPoint 切点
     * @param exception 异常
     * @param object 参数
     */
    protected void handleLog(JoinPoint joinPoint, Exception exception, Object object) {
        try{
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if(controllerLog == null) {
                return;
            }
            // 获取当前用户名
            String username = StpUtil.getLoginIdAsString();

            // *===== 数据库日志 =====* //
            SysOperLog operLog = new SysOperLog();
            operLog.setStatus(CommonCode.SUCCESS.ordinal());

            // 获取当前 request 对象
            ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if(request == null) {
                log.info("当前会话不存在 request");
                return ;
            }
            // 请求 ip
            String ip = request.getRequest().getRemoteAddr();
            operLog.setOperIp(ip);
            // 返回参数
            operLog.setJsonResult(JSONUtil.toJsonStr(object));
            // 请求路径
            operLog.setOperUrl(request.getRequest().getRequestURI());
            // 操作人
            if(StringUtils.isNotBlank(username)) {
                operLog.setOperName(username);
            }
            // 异常信息
            if(exception != null) {
                operLog.setStatus(CommonCode.FAIL.ordinal());
                operLog.setErrorMsg(exception.getMessage().substring(0, 2000));
            }
            // 请求方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className+"."+methodName+"()");
            // 请求方式
            operLog.setRequestMethod(request.getRequest().getMethod());
            // 注解信息
            operLog.setBusinessType(controllerLog.businessType().ordinal());
            operLog.setTitle(controllerLog.title());
            if(controllerLog.isSaveRequestArgs()) {
                operLog.setOperParam(JSONUtil.toJsonStr(joinPoint.getArgs()));
            }
            // 保存到数据库
            AsyncManager.getInstance().execute(AsyncFactory.saveOperatorLog(operLog));

        } catch (Exception e) {
            log.error("通知异常");
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获得注解
     * @param joinPoint 切点
     * @return 存在返回注解，不存在返回 null
     */
    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if(method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
