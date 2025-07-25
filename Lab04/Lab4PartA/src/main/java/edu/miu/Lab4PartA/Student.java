package edu.miu.Lab4PartA;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long studentId;

    private String firstname;

    public Long getStudentId() {
        return studentId;
    }

    private String lastname;

    protected Student() {}

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}