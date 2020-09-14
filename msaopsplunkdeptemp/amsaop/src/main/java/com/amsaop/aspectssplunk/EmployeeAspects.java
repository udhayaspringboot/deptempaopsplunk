package com.amsaop.aspectssplunk;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.amsaop.AmsaopApplication;
import com.splunk.Args;
import com.splunk.Receiver;
import com.splunk.Service;
@Aspect
@Component
@Order(2)
public class EmployeeAspects  {

	Map<String,Object> connection =AmsaopApplication.splunkConnect();
	 Service   service = Service.connect(connection);
	 Receiver receiver = service.getReceiver();
	 
		 Args logArgs=new Args();
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controllerMethod()
	{
		
	}
	@Pointcut("within(@org.springframework.stereotype.Service *)")
	public void serviceMethod()
	{
		
	}
	
	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	public void repositoryMethod()
	{
		
	}

	@Pointcut("within(com.employeeservice.controller.*)")
	public void controllerPackageMeThod()
	{
		
	}
	@Pointcut("within(com.employeeservice.service.*)")
	public void servicePackageMeThod()
	{
		
	}
	@Pointcut("within(com.employeeservice.dao.*)")
	public void repositoryPackageMeThod()
	{
		
	}

	@Around("controllerMethod() && controllerPackageMeThod()")
	public Object AllMeth(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object objRet =null;
		logArgs.put("sourcetype", "Test_splunk");
		
		String methodName=proceedingJoinPoint.getSignature().getName();
		try {
			
			receiver.log("main", logArgs, " before running the"+methodName+"in employee controller");
			System.out.println("before running the "+proceedingJoinPoint.getSignature().getName()+"in employee controller");
			objRet = proceedingJoinPoint.proceed();
			receiver.log("main", logArgs,"the "+methodName+" executed successfully in employee controller");
			System.out.println("the "+proceedingJoinPoint.getSignature().getName()+" executed successfully in employee controller");
			
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			System.out.println("exception ocuurerd in employee controller for this method "+methodName);
			receiver.log("main", logArgs,"the "+methodName+" exception ocuurerd in employee controller for this method");
			//e.printStackTrace();
		}
		
		
		return objRet;
		
		
		
		
	}
	
	@Around("serviceMethod() && servicePackageMeThod()")
	public Object AllMethInService(ProceedingJoinPoint proceedingJoinPoint)
	{
		
		Object objRet =null;
		logArgs.put("sourcetype", "Test_splunk");
		
		String methodName=proceedingJoinPoint.getSignature().getName();
		try {
			
			receiver.log("main", logArgs, " before running the"+methodName+"in employee service");
			System.out.println("before running the "+proceedingJoinPoint.getSignature().getName()+"in employee service");
			objRet = proceedingJoinPoint.proceed();
			receiver.log("main", logArgs,"the "+methodName+" executed successfully in employee service");
			System.out.println("the "+proceedingJoinPoint.getSignature().getName()+" executed successfully in employee service");
			
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			System.out.println("exception ocuurerd in employee service for this method "+methodName);
			receiver.log("main", logArgs,"the "+methodName+" exception ocuurerd in employee service for this method");
			//e.printStackTrace();
		}
		
		
		return objRet;
		
		
		
		
		
		
	}
	
	@Around("repositoryMethod() && repositoryPackageMeThod()")
	public Object AllMethInDao(ProceedingJoinPoint proceedingJoinPoint)
	{
		Object objRet =null;
		logArgs.put("sourcetype", "Test_splunk");
		
		String methodName=proceedingJoinPoint.getSignature().getName();
		try {
			
			receiver.log("main", logArgs, " before running the"+methodName+"in employee dao");
			System.out.println("before running the "+proceedingJoinPoint.getSignature().getName()+"in employee dao");
			objRet = proceedingJoinPoint.proceed();
			receiver.log("main", logArgs,"the "+methodName+" executed successfully in employee dao");
			System.out.println("the "+proceedingJoinPoint.getSignature().getName()+" executed successfully in employee dao");
			
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			System.out.println("exception ocuurerd in employee dao for this method "+methodName);
			receiver.log("main", logArgs,"the "+methodName+" exception ocuurerd in employee dao for this method");
			//e.printStackTrace();
		}
		
		
		return objRet;
		
		
		
		
		
	}

}
