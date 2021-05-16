package exceptions;

public class StudentNotFound extends Exception {

    public StudentNotFound(){
        super(String.format("Student not found!"));
    }
}
