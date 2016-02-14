package Exceptions;

public class getDataFromPhoneBookFileException extends RuntimeException {

    public getDataFromPhoneBookFileException() {
        super("Can't get data from 'phoneBook.txt'");
    }

}
