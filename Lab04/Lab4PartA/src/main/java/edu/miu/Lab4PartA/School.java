package edu.miu.Lab4PartA;


import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class School {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    @MapKey(name = "studentId") //
    private Map<Long, Student> students = new HashMap<>();


    protected School() {}

    public School(String name) {
        this.name = name;
    }


    public void setStudents(Map<Long, Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        if (student.getStudentId() != null) {
            this.students.put(student.getStudentId(), student);
        }
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentsCount=" + students.size() +
                '}';
    }
}