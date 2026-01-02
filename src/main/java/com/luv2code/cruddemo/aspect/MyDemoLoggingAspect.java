package com.luv2code.cruddemo.aspect;

import com.luv2code.cruddemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @AfterReturning(
            pointcut = "execution(* com.luv2code.cruddemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){

        //print out the method we are advising on
        String method = theJoinPoint.toShortString();
        System.out.println("\n====>>> Executing @AfterReturning on method: " + method);

        // Edit the result
        if (!result.isEmpty()){
            Account tempAccount = new Account();
            tempAccount.setName("Booo");
            result.add(tempAccount);
        }

        System.out.println("\n====>>> Result is: " + result);

        // convert the account name to uppercase
        convertAccountToUpperCase(result);

        System.out.println("\n====>>> Result is: " + result);
    }

    private void convertAccountToUpperCase(List<Account> result) {
        for (Account account: result){
            String name = account.getName().toUpperCase();
            account.setName(name);
        }
    }

}
