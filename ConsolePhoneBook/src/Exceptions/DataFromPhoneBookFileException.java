package exceptions;

public class DataFromPhoneBookFileException extends RuntimeException {

    public DataFromPhoneBookFileException() {
        super("Can't get data from 'phoneBook.txt'");
    }

}
