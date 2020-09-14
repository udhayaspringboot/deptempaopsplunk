package com.springconfigserver.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.amsaop.aspectssplunk.ConfigServerAspects;

@Aspect
@Component

public class SpringConfigServerAspects {
	
	
	ConfigServerAspects conServAsp = new ConfigServerAspects();
	
	@Pointcut("within(com.springconfigserver.checks.*)")
	public void mainMeth()
	{
		
	}
	
	
	
	@Around("mainMeth()")
	public Object adviceMainConfig(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object ob = conServAsp.adviceMain(proceedingJoinPoint);
		return ob;
		
	}
	

}
