package exceptions;

public class UnacceptedExitHour extends Exception {
    public UnacceptedExitHour() {
        super(String.format("Can't send the request,\nyou already have an ongoing meeting at that moment!"));
    }
}
