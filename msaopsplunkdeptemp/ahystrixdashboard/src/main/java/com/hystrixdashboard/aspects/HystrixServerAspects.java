package com.hystrixdashboard.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.amsaop.aspectssplunk.ConfigServerAspects;
import com.amsaop.aspectssplunk.HystrixAspects;

@Aspect
@Component

public class HystrixServerAspects {
	
	
	HystrixAspects hysAsp = new HystrixAspects();
	
	@Pointcut("within(com.hystrixdashboard.checks.*)")
	public void mainMeth()
	{
		
	}
	
	
	
	@Around("mainMeth()")
	public Object adviceMainConfig(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object ob = hysAsp.adviceMain(proceedingJoinPoint);
		return ob;
		
	}
	

}
