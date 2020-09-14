package com.amsaop.aspectssplunk;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
//@Configuration
public class SplunkAspects {
	
	
	
	@Pointcut(value="execution( * meth*(..))")//used for method level
	public String getVAl()
	{ 
		return "execution( * meth*(..))";
	}
	@Pointcut(value="within(com.amsaop.service.*)")//com.amsaop..* for subpackage (with in used for class level)
	public String getVAlWithIn()
	{ 
		
		return "within(com.amsaop.service.*)";
		
	}@Pointcut("within(@org.springframework.stereotype.Service *)")
	public String serviceMethod()
	{
		return "within(@org.springframework.stereotype.Service *)";
	}
	
	
	@Before(value="getVAlWithIn()")
	public void logbef(JoinPoint jp)
	{
		System.out.println("running before "+jp.getSignature().getName());
		
	}
	@After(value="getVAlWithIn()")
	public void logaf(JoinPoint jp)
	{
		System.out.println("running after "+jp.getSignature().getName());
		
	}

	
	  @AfterReturning(value="getVAlWithIn()") public void logafRet(JoinPoint jp) {
	  System.out.println("running after return "+jp.getSignature().getName());
	  
	  }
	 
	@AfterThrowing(value="getVAlWithIn()",throwing = "ex")
	public void logafThrowing(JoinPoint jp,Exception ex)
	{
		System.out.println("running after throwing exception "+jp.getSignature().getName() + ex);
		
	}
	
	@Around(value="serviceMethod()")
	public void logafThrowingAround(ProceedingJoinPoint pjp)
	{
		//System.out.println("running after throwing exception "+jp.getSignature().getName() + ex);
		
		try {
			
			System.out.println("before around advice" + pjp.getSignature().getName());
			pjp.proceed();
			System.out.println("after around advice");
		} catch (Throwable e) {
			System.out.println("exception around advice");
			e.printStackTrace();
		}
		
	}
	
	
	/* private final Logger log = LoggerFactory.getLogger(this.getClass());

	    *//**
	     * Pointcut that matches all repositories, services and Web REST endpoints.
	     *//*
	    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
	        " || within(@org.springframework.stereotype.Service *)" +
	        " || within(@org.springframework.web.bind.annotation.RestController *)")
	    public void springBeanPointcut() {
	        // Method is empty as this is just a Pointcut, the implementations are in the advices.
	    }

	    *//**
	     * Pointcut that matches all Spring beans in the application's main packages.
	     *//*
	    @Pointcut(
	        "  within(com.amsaop.service.DeptEmpService)" )
	        
	    public void applicationPackagePointcut() {
	        // Method is empty as this is just a Pointcut, the implementations are in the advices.
	    }
	    
	    @Before(value="applicationPackagePointcut()")
	    public void geton()
	    {
	    	System.out.println("before layer");
	    }

	    *//**
	     * Advice that logs methods throwing exceptions.
	     *
	     * @param joinPoint join point for advice
	     * @param e exception
	     *//*
	    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
	        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
	            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
	    }

	    *//**
	     * Advice that logs when a method is entered and exited.
	     *
	     * @param joinPoint join point for advice
	     * @return result
	     * @throws Throwable throws IllegalArgumentException
	     *//*
	    @Around("applicationPackagePointcut() && springBeanPointcut()")
	    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	        if (log.isDebugEnabled()) {
	            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	        }
	        try {
	            Object result = joinPoint.proceed();
	            if (log.isDebugEnabled()) {
	                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                    joinPoint.getSignature().getName(), result);
	            }
	            return result;
	        } catch (IllegalArgumentException e) {
	            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
	                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
	            throw e;
	        }*/
	    
	/*
	 * Map<String, Object> connection = AmsaopApplication.splunkConnect(); Service
	 * service = Service.connect(connection); Receiver receiver =
	 * service.getReceiver(); Args logArgs = new Args();
	 * 
	 * public void saveMeth(ProceedingJoinPoint pjg) { logArgs.put("sourcetype",
	 * "Test_splunk");
	 * 
	 * String methodName = pjg.getSignature().getName(); String className =
	 * pjg.getTarget().getClass().toString(); Object[] array = pjg.getArgs();
	 * 
	 * receiver.log("main", logArgs, " ..............values......................");
	 * 
	 * }
	 * 
	 * private final Logger log = LoggerFactory.getLogger(this.getClass());
	 * 
	 *//**
		 * Pointcut that matches all repositories, services and Web REST endpoints.
		 */
	/*
	 * @Pointcut("within(@org.springframework.stereotype.Repository *)" +
	 * " || within(@org.springframework.stereotype.Service *)" +
	 * " || within(@org.springframework.web.bind.annotation.RestController *)")
	 * public void springBeanPointcut() {
	 * 
	 * // Method is empty as this is just a Pointcut, the implementations are in the
	 * // advices. }
	 * 
	 * @Before(value = "springBeanPointcut()") public void getRet() {
	 * System.out.println("running before all methods"); }
	 *//**
		 * Pointcut that matches all Spring beans in the application's main packages.
		 */
	/*
	 * @Pointcut("within(com.deptempclient..*)" +
	 * "|| within(com.deptempclient.controller..*)"
	 * +"|| within(com.deptempclient.service..*)"
	 * +"|| within(com.deptempclient.dao..*)" +
	 * " || within(com.departmentservice..*)" +
	 * "|| within(com.departmentservice.controller..*)"
	 * +"|| within(com.departmentservice.service..*)"
	 * +"|| within(com.departmentservice.dao..*)" +
	 * " || within(com.employeeservice..*)" +
	 * "|| within(com.employeeservice.controller..*)"
	 * +"|| within(com.employeeservice.service..*)"
	 * +"|| within(com.employeeservice.dao..*)"
	 * 
	 * +"|| within(com.amsaop.service..*)" ) public void
	 * applicationPackagePointcut() { // Method is empty as this is just a Pointcut,
	 * the implementations are in the // advices. }
	 * 
	 * 
	 * @Before(value = "springBeanPointcut() && applicationPackagePointcut()")
	 * public void methBeef() { log.info("performing before"); }
	 * 
	 * 
	 *//**
		 * Advice that logs methods throwing exceptions.
		 *
		 * @param joinPoint join point for advice
		 * @param e         exception
		 */
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @AfterThrowing(pointcut =
	 * "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	 * public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
	 * log.error("Exception in {}.{}() with cause = {}",
	 * joinPoint.getSignature().getDeclaringTypeName(),
	 * joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() :
	 * "NULL"); }
	 * 
	 * 
	 *//**
		 * Advice that logs when a method is entered and exited.
		 *
		 * @param joinPoint join point for advice
		 * @return result
		 * @throws Throwable throws IllegalArgumentException
		 *//*
			 * 
			 * @Around("applicationPackagePointcut() && springBeanPointcut()") public Object
			 * logAround(ProceedingJoinPoint joinPoint) throws Throwable { if
			 * (log.isDebugEnabled()) { log.debug("Enter: {}.{}() with argument[s] = {}",
			 * joinPoint.getSignature().getDeclaringTypeName(),
			 * joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs())); }
			 * try { Object result = joinPoint.proceed(); if (log.isDebugEnabled()) {
			 * log.debug("Exit: {}.{}() with result = {}",
			 * joinPoint.getSignature().getDeclaringTypeName(),
			 * joinPoint.getSignature().getName(), result); } return result; } catch
			 * (IllegalArgumentException e) { log.error("Illegal argument: {} in {}.{}()",
			 * Arrays.toString(joinPoint.getArgs()),
			 * joinPoint.getSignature().getDeclaringTypeName(),
			 * joinPoint.getSignature().getName()); throw e; }
			 * 
			 * }
			 */

}
