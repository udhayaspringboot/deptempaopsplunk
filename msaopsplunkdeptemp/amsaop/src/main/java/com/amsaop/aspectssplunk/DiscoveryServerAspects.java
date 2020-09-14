package com.amsaop.aspectssplunk;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.amsaop.AmsaopApplication;
import com.splunk.Args;
import com.splunk.Receiver;
import com.splunk.Service;

@Aspect
@Component
@Order(3)
public class DiscoveryServerAspects {
	Map<String,Object> connection =AmsaopApplication.splunkConnect();
	 Service   service = Service.connect(connection);
	 Receiver receiver = service.getReceiver();
	 
		 Args logArgs=new Args();
	
	@Pointcut("within(com.discoveryserver.checks.*)")
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
		 
		 receiver.log("main", logArgs, " starting discovery server");
		 System.out.println("starting discovery server");
		objVal =proceedingJoinPoint.proceed();
		 receiver.log("main", logArgs, "  discovery server started successfully");
		 System.out.println(" discovery server started successfully");
		
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		 receiver.log("main", logArgs, "  error in stating discovery server ");
		 System.out.println("error in stating discovery server");
		e.printStackTrace();
	}
	return objVal;
		
	}

}
