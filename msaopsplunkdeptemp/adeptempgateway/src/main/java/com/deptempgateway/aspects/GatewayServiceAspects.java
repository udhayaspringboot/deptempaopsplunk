package com.deptempgateway.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.amsaop.aspectssplunk.DepartmentAspects;
import com.amsaop.aspectssplunk.GatewayAspects;

@Aspect
@Component
public class GatewayServiceAspects {
	
	GatewayAspects gatAsp = new GatewayAspects();
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controllerMethod()
	{
		
	}
	
	@Pointcut("within(com.deptempgateway.controller.*)")
	public void controllerPackageMeThod()
	{
		
	}
	
	
	@Around("controllerMethod() && controllerPackageMeThod()")
	public Object allMethDept(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object ob = gatAsp.adviceMainFallBack(proceedingJoinPoint);
		return ob;
	}
	
	
	@Pointcut("within(com.deptempgateway.checks.*)")
	public void mainCheckMeth()
	{
		
	}
	@Around("mainCheckMeth()")
	public Object allMethService(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object ob = gatAsp.adviceMain(proceedingJoinPoint);
		
		return ob;
		
	}
	
	
	

}
