package exceptions;

public class GetDataFromPhoneBookFileException extends Exception {

    public GetDataFromPhoneBookFileException() {
        super("Can't get data from 'phoneBook.txt'");
    }

}
