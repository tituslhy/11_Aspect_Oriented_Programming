package com.luv2code.cruddemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    /**
     * This method is executed before any method called `public void addAccount()`
     */
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice(){
        // custom code
        System.out.println("\n=====>>> Executing @Before advice on addAccount()");
    }
}
