package com.realaicy.prod.jc.common.aop;

import com.realaicy.prod.jc.common.aop.annotations.HasRealAuth;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.logging.Logger;

@Aspect
@Component
public class RealAuthAspect {

    private static Logger logger = Logger.getLogger(RealAuthAspect.class.getName());

//    @Pointcut("within(@org.springframework.stereotype.Repository *)")
//    public void repositoryClassMethods() {
//    }

    @Pointcut("@annotation(com.realaicy.prod.jc.common.aop.annotations.HasRealAuth)")
    public void realSecuriedMethods() {
    }

    @Around("realSecuriedMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        HasRealAuth myAnnotation = method.getAnnotation(HasRealAuth.class);
        return pjp.proceed();
    }

}
