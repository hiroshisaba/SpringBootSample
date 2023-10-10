package com.example.SpringBootSample.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Before("execution(* *..*.*UserService.*(..))")
    public void startLog(JoinPoint joinPoint) {
        log.info("メソッド開始：" + joinPoint.getSignature());
    }

    @After("execution(* *..*.*UserService.*(..))")
    public void endLog(JoinPoint joinPoint) {
        log.info("メソッド終了：" + joinPoint.getSignature());
    }

    // @Around("bean(*Controller)")
    // @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.info("メソッド開始：" + proceedingJoinPoint.getSignature());
        try {
            Object result = proceedingJoinPoint.proceed();
            log.info("メソッド終了：" + proceedingJoinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.error("メソッド異常終了：" + proceedingJoinPoint.getSignature());
            throw e;
        }
    }
}
