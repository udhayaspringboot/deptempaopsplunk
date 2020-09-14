package com.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ConfigurableApplicationContext;

import com.hystrixdashboard.checks.MainCheck;

@SpringBootApplication
@EnableHystrixDashboard
public class AhystrixdashboardApplication {
	
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext con = SpringApplication.run(AhystrixdashboardApplication.class, args);
		
	     MainCheck mc=	con.getBean(MainCheck.class);
	     mc.exeMain();
		
		 
	}

	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(AhystrixdashboardApplication.class, args);
	 * 
	 * splunkConnect();
	 * 
	 * getConnection();
	 * 
	 * }
	 * 
	 * public static Map<String,Object> splunkConnect() {
	 * HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2); // Create a
	 * map of arguments and add login parameters
	 * 
	 * Map<String,Object> connection = new HashMap<>(); connection.put("username",
	 * "udhayasplunk"); connection.put("password", "Qwerty123#");
	 * connection.put("host", "LAPTOP-1NN8E0HU"); connection.put("port", 8089);
	 * 
	 * return connection;
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * public static void getConnection() { Map<String,Object> connection
	 * =splunkConnect(); Service service = Service.connect(connection);
	 * 
	 * for (Application app : service.getApplications().values()) {
	 * System.out.println(app.getName()); }
	 * 
	 * Receiver receiver=service.getReceiver(); Args logArgs=new Args();
	 * logArgs.put("sourcetype", "Test_splunk");
	 * 
	 * //receiver.log("main", logArgs, "splunk integration"); receiver.log("main",
	 * logArgs, "Hystrix application is running"); }
	 * 
	 */
}
