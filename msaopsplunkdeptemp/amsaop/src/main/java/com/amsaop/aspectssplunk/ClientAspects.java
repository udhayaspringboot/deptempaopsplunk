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
@Order(7)
public class ClientAspects {
	Map<String,Object> connection =AmsaopApplication.splunkConnect();
	 Service   service = Service.connect(connection);
	 Receiver receiver = service.getReceiver();
	 
		 Args logArgs=new Args();
	
	
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
		Object objRet =null;
		logArgs.put("sourcetype", "Test_splunk");
		String methodName = proceedingJoinPoint.getSignature().getName();
		try {
			receiver.log("main", logArgs, " before running the"+methodName+"in client controller");
			System.out.println("before running the "+proceedingJoinPoint.getSignature().getName()+"in client controller");
			objRet = proceedingJoinPoint.proceed();
			receiver.log("main", logArgs, "the "+methodName+" executed successfully in client controller");
			System.out.println("the "+proceedingJoinPoint.getSignature().getName()+" executed successfully in client controller");
			
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			System.out.println("exception ocuurerd in client controller");
			receiver.log("main", logArgs, "the "+methodName+" exception ocuurerd in client controller");
			//e.printStackTrace();
		}
		
		
		return objRet;
		
		
		
		
	}
	
	

}
