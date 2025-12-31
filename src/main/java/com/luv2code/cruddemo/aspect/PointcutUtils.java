package com.luv2code.cruddemo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutUtils {
    @Pointcut("execution(* com.luv2code.cruddemo.dao.*.*(..))")
    public void forDaoPackage(){}

    /**
     * TO match all getter methods in the dao package
     */
    @Pointcut("execution(* com.luv2code.cruddemo.dao.*.get*(..))")
    public void getter(){}

    /**
     * TO match all getter methods in the dao package
     */
    @Pointcut("execution(* com.luv2code.cruddemo.dao.*.set*(..))")
    public void setter(){}

    /**
     * To match all methods except for getter and setter methods
     */
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterNoSetter(){}
}
