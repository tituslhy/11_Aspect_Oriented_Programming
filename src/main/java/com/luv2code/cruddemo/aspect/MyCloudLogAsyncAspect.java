package com.luv2code.cruddemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class MyCloudLogAsyncAspect {

    @Before("com.luv2code.cruddemo.aspect.PointcutUtils.forDaoPackageNoGetterNoSetter()")
    public void logToCloudAsync(){
        System.out.println("\n=====>>> Logging to Cloud in async fashion");
    }

}
