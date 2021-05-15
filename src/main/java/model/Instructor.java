package model;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private String username;

    public Instructor(String username) {
        this.username = username;
        this.requests = new ArrayList<>();
    }

    public Instructor() {
    }

    public String getUsername() {
        return username;
    }

    public List<Student> getRequests() {
        return requests;
    }

    public void setRequests(List<Student> requests) {
        this.requests = requests;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private List<Student> requests;
}
