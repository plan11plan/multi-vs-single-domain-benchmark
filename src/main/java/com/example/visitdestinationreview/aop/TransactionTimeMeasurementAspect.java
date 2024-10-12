//package com.example.visitdestinationreview.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Aspect
//@Component
//@Slf4j
//public class TransactionTimeMeasurementAspect {
//
//    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
//    public Object measureTransactionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        Object result = joinPoint.proceed();
//
//        stopWatch.stop();
//        log.info("Transaction time for {}: {} ms",
//                 joinPoint.getSignature().toShortString(),
//                 stopWatch.getTotalTimeMillis());
//
//        return result;
//    }
//}