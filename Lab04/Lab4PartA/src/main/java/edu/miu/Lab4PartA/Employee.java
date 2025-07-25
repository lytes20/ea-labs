package edu.miu.Lab4PartA;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    public int employeeNumber;
    public String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    public Department department;

    protected Employee() {
    }

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
