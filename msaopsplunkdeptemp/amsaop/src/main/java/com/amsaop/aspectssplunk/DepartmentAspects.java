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
@Order(1)
public class DepartmentAspects {
	
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

	@Pointcut("within(com.departmentservice.controller.*)")
	public void controllerPackageMeThod()
	{
		
	}
	@Pointcut("within(com.departmentservice.service.*)")
	public void servicePackageMeThod()
	{
		
	}
	@Pointcut("within(com.departmentservice.dao.*)")
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
			
			receiver.log("main", logArgs, " before running the"+methodName+"in department controller");
			System.out.println("before running the "+proceedingJoinPoint.getSignature().getName()+"in department controller");
			objRet = proceedingJoinPoint.proceed();
			receiver.log("main", logArgs,"the "+methodName+" executed successfully in department controller");
			System.out.println("the "+proceedingJoinPoint.getSignature().getName()+" executed successfully in department controller");
			
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			System.out.println("exception ocuurerd in department controller for this method "+methodName);
			receiver.log("main", logArgs,"the "+methodName+" exception ocuurerd in department controller for this method");
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
			
			receiver.log("main", logArgs, " before running the"+methodName+"in department service");
			System.out.println("before running the "+proceedingJoinPoint.getSignature().getName()+"in department service");
			objRet = proceedingJoinPoint.proceed();
			receiver.log("main", logArgs,"the "+methodName+" executed successfully in department service");
			System.out.println("the "+proceedingJoinPoint.getSignature().getName()+" executed successfully in department service");
			
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			System.out.println("exception ocuurerd in department service for this method "+methodName);
			receiver.log("main", logArgs,"the "+methodName+" exception ocuurerd in department service for this method");
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
			
			receiver.log("main", logArgs, " before running the"+methodName+"in department dao");
			System.out.println("before running the "+proceedingJoinPoint.getSignature().getName()+"in department dao");
			objRet = proceedingJoinPoint.proceed();
			receiver.log("main", logArgs,"the "+methodName+" executed successfully in department dao");
			System.out.println("the "+proceedingJoinPoint.getSignature().getName()+" executed successfully in department dao");
			
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			System.out.println("exception ocuurerd in department dao for this method "+methodName);
			receiver.log("main", logArgs,"the "+methodName+"exception ocuurerd in department dao for this method");
			//e.printStackTrace();
		}
		
		
		return objRet;
		
		
		
		
		
	}
	
}
