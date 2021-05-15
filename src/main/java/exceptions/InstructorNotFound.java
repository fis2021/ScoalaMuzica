package exceptions;

public class InstructorNotFound extends Exception {

    public InstructorNotFound(){
        super(String.format("Instructor not found!"));
    }
}
