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
@Order(4)
public class ConfigServerAspects {

	Map<String, Object> connection = AmsaopApplication.splunkConnect();
	Service service = Service.connect(connection);
	Receiver receiver = service.getReceiver();

	Args logArgs = new Args();

	@Pointcut("within(com.springconfigserver.checks.*)")
	public void mainMeth() {

	}

	@Around("mainMeth()")
	public Object adviceMain(ProceedingJoinPoint proceedingJoinPoint) {

		Object objVal = null;
		logArgs.put("sourcetype", "Test_splunk");
		String methodName = proceedingJoinPoint.getSignature().getName();
		try {

			receiver.log("main", logArgs, " starting spring config server");
			System.out.println("starting spring config server");
			objVal = proceedingJoinPoint.proceed();
			receiver.log("main", logArgs, "  spring config server started successfully");
			System.out.println("spring config server started successfully");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			receiver.log("main", logArgs, "  error in stating spring config server ");
			System.out.println("error in stating spring config server");
			e.printStackTrace();
		}
		return objVal;

	}

}
