package exceptions;

public class UnacceptedEntryHour extends Exception {
    public UnacceptedEntryHour() {
        super(String.format("Can't send the request,\nyou already have an ongoing meeting at that moment!"));
    }
}
