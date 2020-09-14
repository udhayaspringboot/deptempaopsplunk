package com.employeeservice.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.amsaop.aspectssplunk.EmployeeAspects;

@Aspect
@Component
public class EmployeeServiceAspects {

	
	EmployeeAspects empAspects = new EmployeeAspects();

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controllerMethod() {

	}

	@Pointcut("within(com.employeeservice.controller.*)")
	public void controllerPackageMeThod() {

	}

	@Around("controllerMethod() && controllerPackageMeThod()")
	public Object empContMeth(ProceedingJoinPoint proceedingJoinPoint) {
		Object obj = empAspects.AllMeth(proceedingJoinPoint);
		return obj;
	}

	@Pointcut("within(@org.springframework.stereotype.Service *)")
	public void serviceMethod() {

	}

	@Pointcut("within(com.employeeservice.service.*)")
	public void servicePackageMeThod() {

	}

	@Around("controllerMethod() && controllerPackageMeThod()")
	public Object empServMeth(ProceedingJoinPoint proceedingJoinPoint) {
		Object obj = empAspects.AllMethInService(proceedingJoinPoint);
		return obj;
	}

	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	public void repositoryMethod() {

	}

	@Pointcut("within(com.employeeservice.dao.*)")
	public void repositoryPackageMeThod() {

	}

	@Around("controllerMethod() && controllerPackageMeThod()")
	public Object empDaoMeth(ProceedingJoinPoint proceedingJoinPoint) {
		Object obj = empAspects.AllMethInDao(proceedingJoinPoint);
		return obj;
	}

}
