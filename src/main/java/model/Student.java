package model;

public class Student {
    private String student;
    private int hour;

    public Student(String student, int hour) {
        this.student = student;
        this.hour=hour;
    }

    public Student() {
    }

    public String getStudent() {
        return student;
    }

    public int getHour() {
        return hour;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        model.Student student1 = (model.Student) o;

        if (hour != student1.hour) return false;
        return student.equals(student1.student);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + hour;
        return result;
    }
}