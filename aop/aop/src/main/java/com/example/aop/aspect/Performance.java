package com.example.aop.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Performance {
    @Pointcut("execution(* com.example.aop.member.MemberService.getMembers(..))")
    public void getMembers(){}

    @Around("getMembers()")
    public Object measurePerformanceTime(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        try{
            long start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            System.out.println("수행 시간: "+(end-start)+"sec");
        }catch(Throwable throwable){
            System.out.println("exception!");
        }
        return result;
    }

}
