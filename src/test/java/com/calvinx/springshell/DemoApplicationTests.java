package com.calvinx.springshell;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@Import(TestApplicationRunner.class)
class DemoApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		System.out.println("Sanity check on ApplicationContext");
		System.out.println("##########");
		System.out.println(context);
		System.out.println("##########");
	}

}
