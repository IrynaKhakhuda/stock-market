package com.stock.market.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Aspect
@Component
@Log4j2
@ConditionalOnExpression("${aspect.enabled}")
public class TotalExecutionTimeAdvice {

    @Around("@annotation(com.stock.market.aspects.ExecutionTimeTracked)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endtime = System.currentTimeMillis();
        log.info("Class: {} . Method: {}() . Time taken for Execution is : {} ms",
                point.getSignature().getDeclaringType().getSimpleName(), point.getSignature().getName(), (endtime - startTime));
        return object;
    }
}
