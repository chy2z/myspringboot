package com.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 切点类
 */
@Aspect
@Component
public class LoggingAspect
{
    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    //private Marker marker = MarkerFactory.getMarker("performance");

    /**
     * 切点
     * 修改要监控切入的包名
     */
    @Pointcut("execution(* com.springboot.controller..*Controller*.*(..))")
    public void controllerMethod()
    {

    }

    /**
     * 包裹被通知的方法，可以在方法调用前后执行自定义的行为
     * @param joinPoint
     * @return
     */
    @Around("controllerMethod()")
    public Object aroundControllerAdvice(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                long elapsed = System.currentTimeMillis() - start;
                Signature signature = joinPoint.getSignature();

                if (signature != null) {
                    String clazzName = signature.getDeclaringTypeName();
                    String method = signature.getName();
                    logger.info("{}.{}|{}", clazzName, method, elapsed);
                }

            } catch (Exception e) {
                logger.error("error when log performance", e);
            }

        }

        return null;
    }

    /**
     * 在方法抛出异常后调用通知
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "controllerMethod()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception){
        Signature signature = joinPoint.getSignature();
        if (signature != null)
        {
            String clazzName = signature.getDeclaringTypeName();
            String method = signature.getName();
            String param =  methodParams(joinPoint, signature);
            logger.error("{}.{}.({})",clazzName,method,param,exception);
        }
    }

    /**
     *  在方法调被用前调用通知
     * @param joinPoint
     */
    @Before("controllerMethod()")
    public void deoBefore(JoinPoint joinPoint){
        logger.info("方法执行前...");
        ServletRequestAttributes sra=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=sra.getRequest();
        logger.info("url:"+request.getRequestURI());
        logger.info("ip:"+request.getRemoteHost());
        logger.info("method:"+request.getMethod());
        logger.info("class_method:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        logger.info("args:"+joinPoint.getArgs());
    }

    /**
     * 在方法完成后调用通知，无论方法是否成功
     * @param joinPoint
     */
    @After("controllerMethod()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("方法执行后...");
    }

    /**
     * 在方法成功执行后调用通知
     * @param result
     */
    @AfterReturning(returning="result",pointcut="controllerMethod()")
    public void doAfterReturning(Object result){
        logger.info("执行返回值："+result);
    }

    private String methodParams(JoinPoint pjp, Signature signature) {
        StringBuffer sb = new StringBuffer();
        try {
            CodeSignature cs = (CodeSignature) signature;
            String[] paramNames = cs.getParameterNames();
            Object[] paramValues = pjp.getArgs();

            for (int i = 0; i < paramNames.length; i++) {
                sb.append(paramNames[i]).append("=").append(toStr(paramValues[i]));
                if (i < paramNames.length - 1) {
                    sb.append(", ");
                }
            }
        } catch (Exception e) {
            logger.error("error when generate method param info", e);
        }
        return sb.toString();
    }

    private String toStr(Object o) {
        if (o == null)
        {
            return "nil";
        }
        return o.toString();
    }

}
