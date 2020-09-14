package com.departmentservice.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.amsaop.aspectssplunk.DepartmentAspects;

@Aspect
@Component
public class DepartmentServiceAspects {
	
	DepartmentAspects deptAspects = new DepartmentAspects();
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controllerMethod()
	{
		
	}
	
	@Pointcut("within(com.departmentservice.controller.*)")
	public void controllerPackageMeThod()
	{
		
	}
	
	
	@Around("controllerMethod() && controllerPackageMeThod()")
	public Object AllMethDept(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object ob = deptAspects.AllMeth(proceedingJoinPoint);
		return ob;
	}
	
	@Pointcut("within(@org.springframework.stereotype.Service *)")
	public void serviceMethod()
	{
		
	}
	
	@Pointcut("within(com.departmentservice.service.*)")
	public void servicePackageMeThod()
	{
		
	}
	@Around("serviceMethod() && servicePackageMeThod()")
	public Object AllMethService(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object ob = deptAspects.AllMethInService(proceedingJoinPoint);
		return ob;
	}
	
	
	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	public void repositoryMethod()
	{
		
	}
	
	@Pointcut("within(com.departmentservice.dao.*)")
	public void repositoryPackageMeThod()
	{
		
	}

	@Around("repositoryMethod() && repositoryPackageMeThod()")
	public Object AllMethRepo(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object ob = deptAspects.AllMethInDao(proceedingJoinPoint);
		return ob;
	}
	
	

}
