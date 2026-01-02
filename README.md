# 11_Aspect_Oriented_Programming
Exploring aspect oriented programming with spring boot. Pointcut expressions essentially allow us to add common aspects (logging, pre/post processing) to methods without editing these methods.

```java
@Before annotated methods run the method before methods specified
```

## Pointcut Expression Language
This uses AspectJ's Pointcut Expression Language
```java
execution(
        modifiers-pattern?
        return-type-pattern
        declaring-type-pattern?
        method-name-pattern(param-pattern)
        throws-pattern?
)
```
Any pattern with a "?" is optional and does not need to be given

### Parameter Pattern Wildcards
- `()` matches a method with no arguments
- `(*)` matches a method with one argument of any type
- `(..)` matches a method with 0 or more arguments of any type

### Examples:
```java
@Before(
        "execution(public void com.luv2code.cruddemo.dao.AccountDAO.addAccount())"
)
```
This will execute the aspect method only if the AccountDAO's addAccount method is executed.

```java
@Before("execution(public void addAccount())")
```
This will execute for all "addAccount()" methods in any class. The modifier is "public", the return type is a "void" and the method is "addAccount"

```java
@Before("execution(public void add*())")
```
This executes the aspect method before the execution of any method that starts with "add" and returns a void.

```java
@Before("execution(public VerificationResult processCreditCard*()")
```
This matches methods starting with processCreditCard in any class that returns a void

Alternatively:
```java
@Before("execution(public * processCreditCard*())")
```
This loosens the return type such that the aspect method runs before any `processCreditCard` method regardless of return type.

The modifier is optional, so actually this will work just the same:
```java
@Before("execution(* processCreditCard*())")
```

This runs the aspect method on the `addAccount` method that consumes a parameter of type `Account`
```java
@Before("execution(* addAccount(com.luv2code.cruddemo.Account))")
```

This examples matches on any methods located in a package:
```java
@Before("execution(* com.luv2code.cruddemo.dao.*.*(..")
```
i.e. the aspect method runs on any method regardless of parameters and class it belongs to as long as it is within the target package.

## Pointcut Declarations
This allows us to reuse pointcut expressions by declaring them only once. Here's an example of how to do it:

```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.luv2code.cruddemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice(){
        ...
    }
    
    @Before("forDaoPackage()")
    public void performApiAnalytics(){
        ...
    }
}
```
In this class, you're defining the pointcut expression once in the `@Pointcut` annotated method, and using applying it in two aspect methods that will run for any method that is executed within the cruddemo.dao package.

## Combining Pointcuts
The problem is how to apply multiple pointcut expressions to a single advice and/or execute an advice ony if certain conditions are met.

For example, I might not want to execute aspect methods on getter/setter methods but I do for every other method. 

We can use logic operators like `&&`, `||` and `!`

### Examples
```java
@Before("expressionOne() && expressionTwo()")
@Before("expressionOne() || expressionTwo()")
@Before("expressionOne() && !expressionTwo()")
```

More concretely:

```java
import org.aspectj.lang.annotation.Pointcut;

@Pointcut("execution(* com.luv2code.cruddemo.dao.*.*(..))")
private void forDaoPackage(){}

@Pointcut("execution(* com.luv2code.cruddemo.dao.*.get*(..))")
private void getter(){}

@Pointcut("execution(* com.luv2code.cruddemo.dao.*.set*(..))")
private void setter(){}

@Pointcut("forDaoPackage() && !(getter() || setter())")
private void forDaoPackageNoGetterNoSetter()
```

This means that our "combo" pointcut runs for all methods in the package except for the getters and setters.

## Ordering Aspects
Place advices in separate Aspects (classes) to allow aspects to be ordered using the `@Order` annotation. 

For example:

```java
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAspect{
        ...
    }
```

Lower numbers have higher precedence and negative numbers are allowed! Numbers do not even have to be consecutive.

If aspects have the same order annotation - the order at those points are undefined. These aspects will still run but not in any fixed order.

## Join Points
Allows for access and display method signatures to improve the quality of logging

```java
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Before("...")
public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

    // display the method signature
    MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
    System.out.println("Method: " + methodSig);
    
    // display method arguments
    Object[] args = theJoinPoint.getArgs();
    
    // loop through args
    for (Object tempArg: args){
        System.out.println(tempArg);
    }
}
```

## Accessing the return value (`@AfterReturning` advice)
You can use any parameter name - just stay consistent. The parameter defined under `returning` must match the parameter name in the method call.

```java
import com.luv2code.cruddemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;

@AfterReturning(
        pointcut = "execution(* com.luv2code.cruddemo.dao.AccountDAO.findAccounts(..))",
        returning = "result"
)
public void afterReturningFindAccountsAdvice(
        JoinPoint theJoinPoint, List<Account> result){
    
    // Just print out the results of the method call.
    System.out.println("\n=====>>> result is: " + result);
}

```