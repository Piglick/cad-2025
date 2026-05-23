package ru.bsuedu.cad.lab.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParserTimingAspect {

    @Around("execution(* ru.bsuedu.cad.lab.parser.CSVParser.parse(..))")
    public Object measureParsingTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();

        Object result = joinPoint.proceed();

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("CSV parsing time: " + duration / 1_000_000.0 + " ms");

        return result;
    }
}