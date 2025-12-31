package com.luv2code.cruddemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Aspect
@Component
public class MyApiAnalyticsAspect {

    @Before("com.luv2code.cruddemo.aspect.PointcutUtils.forDaoPackageNoGetterNoSetter()")
    public void performApiAnalytics(){
        System.out.println("\n=====>>> Doing fancy API analytics");
    }

}
