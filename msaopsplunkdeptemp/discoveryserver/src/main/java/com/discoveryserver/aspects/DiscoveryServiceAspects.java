package com.discoveryserver.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amsaop.aspectssplunk.DiscoveryServerAspects;

@Aspect
@Component
public class DiscoveryServiceAspects {
	
	
	DiscoveryServerAspects disSerAsp = new DiscoveryServerAspects();
	
	@Pointcut("within(com.discoveryserver.checks.*)")
	public void mainMeth()
	{
		
	}
	
	
	
	
	
	@Around("mainMeth()")
	public Object disMain(ProceedingJoinPoint proceedingJoinPoint)
	{
		//calling springAOP external method to print splunk logs
		Object ob = disSerAsp.adviceMain(proceedingJoinPoint);
		
		return ob;
		
	}

}
