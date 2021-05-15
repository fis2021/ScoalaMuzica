package model;

import org.dizitart.no2.objects.Id;

public class Student {
    private int entryHour;
    private int exitHour;

    @Id
    private String studentName;

    public Student(String studentName, int entryHour, int exitHour) {
        this.studentName = studentName;
        this.entryHour = entryHour;
        this.exitHour = exitHour;
    }

    public Student() {
    }

    public String getStudentName() {
        return studentName;
    }

    public int getEntryHour() {
        return entryHour;
    }

    public int getExitHour() {
        return exitHour;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setEntryHour(int entryHour) {
        this.entryHour = entryHour;
    }

    public void setExitHour(int exitHour) {
        this.exitHour = exitHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        model.Student student1 = (model.Student) o;

        if (entryHour != student1.entryHour) return false;
        if (exitHour != student1.exitHour) return false;
        return studentName.equals(student1.studentName);
    }

    @Override
    public int hashCode() {
        int result = studentName.hashCode();
        result = 31 * result + entryHour;
        result = 31 * result + exitHour;
        return result;
    }
}