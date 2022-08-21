package com.talhacgdem.yapikredi;

import com.talhacgdem.yapikredi.repository.LeaveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class YapikrediApplicationTests {

	@Autowired
	private LeaveRepository leaveRepository;

	@Test
	void contextLoads() {
	}



}
