package com.luv2code.cruddemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.luv2code.cruddemo.dao.*.*(..))")
    private void forDaoPackage(){}

//    @Before("execution(public void addAccount())")
//    @Before("execution (public void com.luv2code.cruddemo.dao.AccountDAO.addAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*())")
//    @Before("execution(* add*(com.luv2code.cruddemo.Account, ..))")
//    @Before("execution(* add*(..))")
//    @Before("execution(* com.luv2code.cruddemo.dao.*.*(..))")
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice(){
        // custom code
        System.out.println("\n=====>>> Executing @Before advice on method");
    }

    @Before("forDaoPackage()")
    public void performApiAnalytics(){
        System.out.println("\n=====>>> Doing fancy API analytics");
    }
}
