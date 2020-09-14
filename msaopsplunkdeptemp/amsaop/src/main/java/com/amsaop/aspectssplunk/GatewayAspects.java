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
@Order(6)
public class GatewayAspects {

	Map<String,Object> connection =AmsaopApplication.splunkConnect();
	 Service   service = Service.connect(connection);
	 Receiver receiver = service.getReceiver();
	 
		 Args logArgs=new Args();
	
	@Pointcut("within(com.deptempgateway.checks.*)")
	public void mainMeth()
	{
		
	}
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void fallBackMeth()
	{
		
	}
	
	@Around("mainMeth()")
	public Object adviceMain(ProceedingJoinPoint proceedingJoinPoint)
	{
		
		Object objVal =null;
		logArgs.put("sourcetype", "Test_splunk");
		String methodName = proceedingJoinPoint.getSignature().getName();
	 try {
		 
		 receiver.log("main", logArgs, " starting gateway service");
		 System.out.println("starting gateway service");
		objVal =proceedingJoinPoint.proceed();
		 receiver.log("main", logArgs, "  gateway service started successfully");
		System.out.println("gateway service started successfully");
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		 receiver.log("main", logArgs, "  error in stating gateway service ");
		 System.out.println("error in stating gateway service");
		e.printStackTrace();
	}
	return objVal;
		
	}
	@Around("fallBackMeth()")
	public Object adviceMainFallBack(ProceedingJoinPoint proceedingJoinPoint)
	{
		
		Object objVal =null;
		logArgs.put("sourcetype", "Test_splunk");
		String methodName = proceedingJoinPoint.getSignature().getName();
	 try {
		 
		 receiver.log("main", logArgs, " starting fallback method");
		 System.out.println("starting fallback method");
		objVal =proceedingJoinPoint.proceed();
		 receiver.log("main", logArgs, "  fallback method executed successfully");
		System.out.println("fallback method executed successfully");
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		 receiver.log("main", logArgs, "  error in fallback method ");
		 System.out.println(" error in fallback method");
		e.printStackTrace();
	}
	return objVal;
		
	}
	
	

}
