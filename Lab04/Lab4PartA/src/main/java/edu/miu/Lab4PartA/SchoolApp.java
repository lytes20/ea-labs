package edu.miu.Lab4PartA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolApp implements CommandLineRunner {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args){
        SpringApplication.run(SchoolApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student("Gideon", "Bamuleseyo");

        School school = new School("MIU");

        studentRepository.save(student);
    }
}
