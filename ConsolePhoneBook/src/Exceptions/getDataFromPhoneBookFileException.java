package Exceptions;

public class GetDataFromPhoneBookFileException extends RuntimeException {

    public GetDataFromPhoneBookFileException() {
        super("Can't get data from 'phoneBook.txt'");
    }

}
