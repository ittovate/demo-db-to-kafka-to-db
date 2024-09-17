package com.ittovative.demodbtokafka.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.ittovative.demodbtokafka.util.AspectUtil.getClassName;
import static com.ittovative.demodbtokafka.util.AspectUtil.getMethodArgs;
import static com.ittovative.demodbtokafka.util.AspectUtil.getMethodName;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Around advice for logging
     * before and after executing project methods.
     *
     * @param joinPoint which contains details about method called
     * @return the return value of the method
     */
    @Around("execution(* com.ittovative.demodbtokafka.*.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) {
        String className = getClassName(joinPoint);
        String methodName = getMethodName(joinPoint);
        StringBuilder args = getMethodArgs(joinPoint);
        Object returnVal = null;

        LOGGER.info("Executing ===> {}.{} with arguments: [{}]", className, methodName, args);
        try {
            returnVal = joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("Exception {} in ===> {}.{} with arguments: [{}]", throwable, className, methodName, args);
        }
        LOGGER.info("Finished ===> {}.{} with arguments: [{}] and returned {}", className, methodName, args, returnVal);

        return returnVal;
    }
}
