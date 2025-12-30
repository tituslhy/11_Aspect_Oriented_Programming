# 11_Aspect_Oriented_Programming
Exploring aspect oriented programming with spring boot

```java
@Before annotated methods run the method before methods specified
```

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
