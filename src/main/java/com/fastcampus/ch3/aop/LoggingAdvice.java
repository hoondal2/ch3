package com.fastcampus.ch3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component // 빈으로 등록되어야 한다
@Aspect
public class LoggingAdvice {
    @Around("execution(* com.fastcampus.ch3.aop.MyMath.add(..))") // pointcut -> 부가기능이 적용될 메서드의 패턴
    public Object methodCallLog(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("<<[start]" +pjp.getSignature().getName()+ Arrays.toString(pjp.getArgs())); // 타겟 메서드이름과 인자들

        Object result = pjp.proceed(); // 타켓의 메서드 호출 (타켓의 메서드가 pjp로 넘어오고 호출된다)

        System.out.println("result = " + result);
        System.out.println("[end]>>" +(System.currentTimeMillis()-start)+"ms");
        return result;
    }

}
