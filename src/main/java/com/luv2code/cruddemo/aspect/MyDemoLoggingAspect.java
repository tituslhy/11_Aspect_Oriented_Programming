package com.luv2code.cruddemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

//    @Before("execution(public void addAccount())")
//    @Before("execution (public void com.luv2code.cruddemo.dao.AccountDAO.addAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*())")
//    @Before("execution(* add*(com.luv2code.cruddemo.Account, ..))")
@Before("execution(* add*(..))")
    public void beforeAddAccountAdvice(){
        // custom code
        System.out.println("\n=====>>> Executing @Before advice on method");
    }
}
