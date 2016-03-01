import phoneBookCore.phoneBook.PhoneBook;
import phoneBookMenu.Menu;

public class Main {

    private static final PhoneBook PHONE_BOOK = new PhoneBook();

    public static void main(String[] args) {
        Menu menu = new Menu(PHONE_BOOK );
        menu.start();
    }
}
