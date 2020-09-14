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
@Order(5)
public class HystrixAspects {
	
	Map<String,Object> connection =AmsaopApplication.splunkConnect();
	 Service   service = Service.connect(connection);
	 Receiver receiver = service.getReceiver();
	 
		 Args logArgs=new Args();
	
	@Pointcut("within(com.hystrixdashboard.checks.*)")
	public void mainMeth()
	{
		
	}
	
	@Around("mainMeth()")
	public Object adviceMain(ProceedingJoinPoint proceedingJoinPoint)
	{
		
		Object objVal =null;
		logArgs.put("sourcetype", "Test_splunk");
		String methodName = proceedingJoinPoint.getSignature().getName();
	 try {
		 
		 receiver.log("main", logArgs, " starting hystrix dashboard");
		 System.out.println("starting hystrix dashboard");
		objVal =proceedingJoinPoint.proceed();
		 receiver.log("main", logArgs, "  hystrix dashboard started successfully");
		System.out.println("hystrix dashboard started successfully");
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		 receiver.log("main", logArgs, "  error in stating hystrix dashboard ");
		 System.out.println(" error in stating hystrix dashboard");
		e.printStackTrace();
	}
	return objVal;
		
	}


}
