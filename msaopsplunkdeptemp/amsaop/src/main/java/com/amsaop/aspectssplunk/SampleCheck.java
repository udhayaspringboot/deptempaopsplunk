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
@Order(8)
public class SampleCheck {
	
	
	Map<String,Object> connection =AmsaopApplication.splunkConnect();
	 Service   service = Service.connect(connection);
	 Receiver receiver = service.getReceiver();
	 
		 Args logArgs=new Args();
	
	@Pointcut("within(@org.springframework.stereotype.Service *)")
	public String serviceMethod()
	{
		return "within(@org.springframework.stereotype.Service *)";
	}
	
	@Pointcut("within(com.samplecheck.service.*)")
	public String servicePackageMeThod()
	{
		return "within(com.samplecheck.service.*)";
	}
	
	@Around("serviceMethod() && servicePackageMeThod()")
	public Object AllMethInService(ProceedingJoinPoint proceedingJoinPoint)
	{
		
		Object objRet =null;
		logArgs.put("sourcetype", "Test_splunk");
		
		String methodName=proceedingJoinPoint.getSignature().getName();
		try {
			
			receiver.log("main", logArgs, " before running the"+methodName+"in sample check service");
			System.out.println("before running the "+proceedingJoinPoint.getSignature().getName()+"in sample check service");
			objRet = proceedingJoinPoint.proceed();
			receiver.log("main", logArgs,"the "+methodName+" executed successfully in sample check service");
			System.out.println("the "+proceedingJoinPoint.getSignature().getName()+" executed successfully in sample check service");
			
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			System.out.println("exception ocuurerd in sample check service for this method "+methodName);
			e.printStackTrace();
		}
		
		
		return objRet;
		
		
		
		
		
		
	}
	

}
