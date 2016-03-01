package exceptions;

public class UnexpectedValidationCase extends RuntimeException {

    public UnexpectedValidationCase() {
        super("Unexpected validation case!");
    }

}
