package model;

import java.util.ArrayList;
import java.util.List;

import org.dizitart.no2.objects.Id;

public class Instructor {
    private List<Student> requests;
    private List<Student> students;

    @Id
    private String username;

    public Instructor(String username) {
        this.username = username;
        this.requests = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public Instructor() {
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Student> getRequests() {
        return requests;
    }

    public void setRequests(List<Student> requests) {
        this.requests = requests;
    }
}
