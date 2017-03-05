package com.realaicy.prod.jc.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

    private static Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    private ThreadLocal<SimpleDateFormat> sdf =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("[yyyy-mm-dd hh:mm:ss:SSS]"));
    @Pointcut("@annotation(com.realaicy.prod.jc.common.aop.annotations.Loggable)")
    public void loggableMethods() {
    }

//    @Pointcut("@args(com.realaicy.product.jc.common.aop.annotations.Entity)")
//    public void methodsAcceptingEntities() {
//    }

//    @Before("repositoryMethods()")
//    public void logMethodCall(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//        logger.info(sdf.get().format(new Date()) + methodName);
//    }

    @Before("loggableMethods()")
    public void logMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info("Executing method: " + methodName);
    }

//    @Before("methodsAcceptingEntities()")
//    public void logMethodAcceptionEntityAnnotatedBean(JoinPoint jp) {
//        logger.info("Accepting beans with @Entity annotation: " + jp.getArgs()[0]);
//    }
}
