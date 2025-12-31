package com.luv2code.cruddemo.aspect;

import com.luv2code.cruddemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class MyDemoLoggingAspect {

    /**
     * Defining some pointcut expressions
     */
//    @Before("execution(public void addAccount())")
//    @Before("execution (public void com.luv2code.cruddemo.dao.AccountDAO.addAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*())")
//    @Before("execution(* add*(com.luv2code.cruddemo.Account, ..))")
//    @Before("execution(* add*(..))")
//    @Before("execution(* com.luv2code.cruddemo.dao.*.*(..))")
//    @Before("forDaoPackage()")
    @Before("com.luv2code.cruddemo.aspect.PointcutUtils.forDaoPackageNoGetterNoSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n=====>>> Executing @Before advice on method");

        // display method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments
        Object[] args = theJoinPoint.getArgs();

        // loop through args
        for (Object tempArg: args){
            System.out.println(tempArg);

            if (tempArg instanceof Account){
                //downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }

    }

}
