package com.example.demo.service; // Check if your package is actually this!

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // IMPORTANT: Change "com.example.demo" to match your actual base package name
    @Before("execution(* com.example.demo.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("👉 AOP Check: Entering method: " + joinPoint.getSignature().getName());
    }
}