package exceptions;

public class InvalidUsername extends Exception {

    public InvalidUsername() {
        super(String.format("Username does not exist!"));

    }
}