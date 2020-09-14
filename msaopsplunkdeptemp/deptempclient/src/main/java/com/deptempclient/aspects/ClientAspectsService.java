package com.deptempclient.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.amsaop.aspectssplunk.ClientAspects;

@Aspect
@Component
public class ClientAspectsService {
	
	ClientAspects cliAsp = new ClientAspects();
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controllerMethod()
	{
		
	}
	
	@Pointcut("within(com.deptempclient.controller.*)")
	public void controllerPackageMeThod()
	{
		
	}
	

	@Around("controllerMethod() && controllerPackageMeThod()")
	public Object AllMeth(ProceedingJoinPoint proceedingJoinPoint)
	{
		
		Object obj = cliAsp.AllMeth(proceedingJoinPoint);
		return obj;
		
	}

}
