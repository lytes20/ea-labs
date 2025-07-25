package edu.miu.Lab4PartA;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class Lab4PartAApplication implements CommandLineRunner {


	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;


	public static void main(String[] args) {
		SpringApplication.run(Lab4PartAApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Department department = new Department("Credit");

		Employee emp1 = new Employee("Karamael", department);
		Employee emp2 = new Employee("Kevin", department);


//		employeeRepository.save(emp1);
//		employeeRepository.save(emp2);
		department.addEmployee(emp1);
		department.addEmployee(emp2);


		departmentRepository.save(department);

		employeeRepository.findAll().forEach(System.out::println);

		departmentRepository.findAll().forEach(System.out::println);
	}
}
