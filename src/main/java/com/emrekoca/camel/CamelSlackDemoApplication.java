package com.emrekoca.camel;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CamelSlackDemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run(CamelDemoApplication.class, args);
		ApplicationContext context = SpringApplication.run(CamelSlackDemoApplication.class, args);
		CamelSpringBootApplicationController applicationController = context
				.getBean(CamelSpringBootApplicationController.class);
		applicationController.run();
	}
}
