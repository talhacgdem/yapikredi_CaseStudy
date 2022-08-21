package com.talhacgdem.yapikredi;

import com.talhacgdem.yapikredi.model.Employee;
import com.talhacgdem.yapikredi.repository.EmployeeRepository;
import com.talhacgdem.yapikredi.repository.LeaveRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class YapikrediApplication {

	public static void main(String[] args) throws ParseException {

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(YapikrediApplication.class, args);

		EmployeeRepository employeeRepository = configurableApplicationContext.getBean(EmployeeRepository.class);
		LeaveRepository leaveRepository = configurableApplicationContext.getBean(LeaveRepository.class);

		Employee employee = new Employee("Talha","Çiğdem", new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-22"));
		employeeRepository.save(employee);
		employee = new Employee("Emre","Doğan", new SimpleDateFormat("yyyy-MM-dd").parse("2010-03-22"));
		employeeRepository.save(employee);
		employee = new Employee("Sabahattin","Oluk", new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-22"));
		employeeRepository.save(employee);
		employee = new Employee("Mustafa","Sis", new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-22"));
		employeeRepository.save(employee);


	}

}
