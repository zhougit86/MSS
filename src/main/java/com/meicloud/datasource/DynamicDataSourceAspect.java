package com.meicloud.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

   private final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);


   @Before("execution(* com.meicloud.dao.mapper.*.*(..))")
   public void before(JoinPoint point) {}

   @After("execution(* com.meicloud.dao.mapper.*.*(..))")
   public void restoreDataSource(JoinPoint point) {}
}
