package edu.miu.Lab4PartA;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue
    public int id;
    public String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    public List<Employee> employees = new ArrayList<>();

    protected Department() {

    }

    public Department(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.department = this;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" +
                '}';
    }
}
