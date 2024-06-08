package org.javaacademy.afisha.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingTicketServiceAspect {

    @Pointcut("within(org.javaacademy.afisha.service.TicketService)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String fullMethodName = className + "." + methodName;
        log.info("Вызов метода {}", fullMethodName);
        Object result = joinPoint.proceed();
        log.info("Результат работы метода {}: {}", fullMethodName, result);
        return result;
    }
}
