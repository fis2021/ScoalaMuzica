package exceptions;

public class InvalidPassword extends Exception {

    public InvalidPassword() {
        super(String.format("Incorrect password!"));

    }
}