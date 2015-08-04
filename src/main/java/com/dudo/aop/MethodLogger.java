package com.dudo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangkai9
 * @date 2015/7/21
 */
@Aspect
public class MethodLogger {
    static Logger logger = LoggerFactory.getLogger(MethodLogger.class);

    @Around("execution(* *(..)) && @annotation(Loggable)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        logger.info(
                "#%s(%s): %s in %[msec]s",
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );
        return result;
    }
}