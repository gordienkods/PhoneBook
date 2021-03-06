package phoneBookMenu;

import phoneBookCore.phoneBook.PhoneBook;
import phoneBookCore.phoneBook.PhoneContact;
import phoneBookCore.phoneBook.PhoneNumberAndType;
import phoneBookCore.validator.Rule;
import phoneBookCore.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private PhoneBook phoneBook;
    private static final Scanner CONSOLE_SCANNER = new Scanner(System.in);

    public Menu (PhoneBook phoneBook)  {
        this.phoneBook = phoneBook;
    }

    public void start(){
        String command;

        if (phoneBook.initialize()) {
            authorizationMenu();
        } else {
            createNewUserMenu();
        }

        while (true){
            System.out.println(
                    "\n\n ***MAIN MENU*** \n" +
                            "[1] Create new contact\n" +
                            "[2] Edit contact\n" +
                            "[3] Delete contact\n" +
                            "[4] Get all contacts\n" +
                            "[5] Search contacts by first or last name\n" +
                            "[6] Search contacts by any part of name\n" +
                            "[7] Search contacts by phone number\n" +
                            "[8] Search contacts by age\n" +
                            "[0] EXIT");
            System.out.print("Enter number of point: ");
            command = CONSOLE_SCANNER.nextLine();
            switch (command) {
                case "1":
                    createNewContactMenu();
                    break;
                case "2":
                    editContactMenu();
                    break;
                case "3":
                    deleteContactMenu();
                    break;
                case "4":
                    getAllContactsMenu();
                    break;
                case "5":
                    searchByFirstOrLasNameMenu();
                    break;
                case "6":
                    searchByAnyPartOfNameMenu();
                    break;
                case "7":
                    searchByPhoneNumberMenu();
                    break;
                case "8":
                    searchByAgeMenu();
                    break;
                case "0": return;

                default:
                    System.out.println("Unexpected command!");
            }
        }
    }

    private void createNewContactMenu() {
        PhoneContact phoneContact = new PhoneContact();
        String tmp;

        Integer phoneNumberCounter = 0;
        System.out.print("\n\n *** CREATION OF NEW CONTACT ***\n\n");

        System.out.print("\nHow many phones do yuo want to add? ");
        phoneNumberCounter = CONSOLE_SCANNER.nextInt();

        CONSOLE_SCANNER.nextLine();

        do {
            System.out.print("\nFIRST NAME: ");
            tmp = CONSOLE_SCANNER.nextLine();
            if (Validator.validateThisData(tmp, Rule.FIRST_NAME) ) {
                phoneContact.setFirstName(tmp);
                Validator.clearErrors();
                break;
            } else {
                System.out.println(Validator.getErrors());
                Validator.clearErrors();
            }
        } while (true);

        do {
            System.out.print("\nLAST NAME: ");
            tmp = CONSOLE_SCANNER.nextLine();
            if (Validator.validateThisData(tmp, Rule.LAST_NAME) ) {
                phoneContact.setLastName(tmp);
                Validator.clearErrors();
                break;
            } else {
                System.out.println(Validator.getErrors());
                Validator.clearErrors();
            }
        } while (true);

        addPhoneAndPhoneTypesSubMenu(phoneContact,phoneNumberCounter);

        System.out.print("\nEMAIL: ");
        tmp = CONSOLE_SCANNER.nextLine();
        if (Validator.validateThisData(tmp, Rule.EMAIL) ) {
            phoneContact.setEmail(tmp);
        } else {
            System.out.println(Validator.getErrors());
        }
        Validator.clearErrors();


        do {
            System.out.print("\nBIRTH DATE [dd.MM.yyyy]: ");
            tmp = CONSOLE_SCANNER.nextLine();
            if (Validator.validateThisData(tmp, Rule.BIRTH_DATE) ) {
                phoneContact.setBirthDate(tmp);
                Validator.clearErrors();
                break;
            } else {
                System.out.println(Validator.getErrors());
                Validator.clearErrors();
            }
        } while (true);

        System.out.print("ADDRESS: ");
        phoneContact.setAddress(CONSOLE_SCANNER.nextLine());

        phoneBook.add(phoneContact);
    }

    private void addPhoneAndPhoneTypesSubMenu(PhoneContact phoneContact, Integer counter) {
        String tmpPhoneNumber, tmpPhoneType;
        if (counter <= 0){
            return;
        }
        for (int i = 0; i < counter; i++){
            System.out.print("\nPHONE NUMBER [" + (1+i) + " of " + counter +" ]: ");
            tmpPhoneNumber = CONSOLE_SCANNER.nextLine();
            if(!Validator.validateThisData(tmpPhoneNumber, Rule.PHONE_NUMBER)){
                System.out.println(Validator.getErrors());
                Validator.clearErrors();
                return;
            }
            System.out.print("\nPHONE TYPE [" + (1+i) + " of " + counter +" ]: ");
            tmpPhoneType = CONSOLE_SCANNER.nextLine();
            phoneContact.setPhoneNumberAndType(tmpPhoneNumber,tmpPhoneType);
        }
    }

    public void editContactMenu (){
        PhoneContact editablePhoneContact;
        phoneBook.sort(PhoneBook.SortVariants.BY_LAST_NAME);
        System.out.println(phoneBook.toString());
        System.out.print("\n\n*** EDIT CONTACT MENU ***\n\n");
        System.out.print("Enter number of contact: ");
        Integer numberOfContact = CONSOLE_SCANNER.nextInt() - 1;

        CONSOLE_SCANNER.nextLine();

        if (numberOfContact < 0 || numberOfContact > phoneBook.size()){
            System.out.println("Unexpected contact number!");
            return;
        }

        editablePhoneContact = phoneBook.get(numberOfContact);

        System.out.println("\nCURRENT FIRST NAME: " + editablePhoneContact.getFirstName());
        System.out.print("\nENTER NEW FIRST NAME: ");
        editablePhoneContact.setFirstName(CONSOLE_SCANNER.nextLine());

        System.out.println("\nCURRENT LAST NAME: " + editablePhoneContact.getLastName());
        System.out.print("\nENTER NEW LAST NAME: ");
        editablePhoneContact.setLastName(CONSOLE_SCANNER.nextLine());

        editPhoneAndPhoneTypesSubMenu(editablePhoneContact);

        System.out.println("\nCURRENT EMAIL: " + editablePhoneContact.getEmail());
        System.out.print("\nENTER NEW EMAIL: ");
        editablePhoneContact.setEmail(CONSOLE_SCANNER.nextLine());

        System.out.println("\nCURRENT BIRTH DAY: " + editablePhoneContact.getBirthDate());
        System.out.print("\nENTER NEW BIRTH DAY: ");
        editablePhoneContact.setBirthDate(CONSOLE_SCANNER.nextLine());

        System.out.println("\nCURRENT ADDRESS: " + editablePhoneContact.getAddress());
        System.out.print("\nENTER NEW ADDRESS: ");
        editablePhoneContact.setAddress(CONSOLE_SCANNER.nextLine());

        phoneBook.serializeToFile();

        System.out.println("Contact '" + (numberOfContact + 1) + "' successfully updated!");
    }

    public void deleteContactMenu(){
        phoneBook.sort(PhoneBook.SortVariants.BY_LAST_NAME);
        System.out.println(phoneBook.toString());
        Scanner sc = new Scanner(System.in);

        System.out.print("\n\n*** DELETE CONTACT MENU ***\n\n");
        System.out.print("\nEnter number of contact: ");
        Integer numberOfContact = sc.nextInt() - 1;

        if (numberOfContact < 0 || numberOfContact > phoneBook.size()){
            System.out.println("Unexpected contact number!");
            return;
        }

        phoneBook.delete(phoneBook.get(numberOfContact));
        phoneBook.serializeToFile();

        System.out.println("Contact '" + (numberOfContact + 1) + "' successfully deleted!");
    }

    private void editPhoneAndPhoneTypesSubMenu(PhoneContact phoneContact) {
        ArrayList<PhoneNumberAndType> phoneNumberAndTypeList = phoneContact.getPhoneNumberAndTypeList();
        for (int i = 0; i < phoneNumberAndTypeList.size(); i++){
            System.out.println("CURRENT PHONE NUMBER [" + (1+i) + " of " + (1+i) +" ]: " + phoneNumberAndTypeList.get(i).getPhoneNumber());
            System.out.println("CURRENT PHONE TYPE [" + (1+i) + " of " + (1+i) +" ]: " + phoneNumberAndTypeList.get(i).getPhoneType());
            System.out.print("ENTER NEW PHONE NUMBER [" + (1+i) + " of " + (1+i) +" ]: ");
            phoneNumberAndTypeList.get(i).setPhoneNumber(CONSOLE_SCANNER.nextLine());
            System.out.print("ENTER NEW PHONE TYPE [" + (1+i) + " of " + (1+i) +" ]: ");
            phoneNumberAndTypeList.get(i).setPhoneType(CONSOLE_SCANNER.nextLine());
        }
    }

    private void getAllContactsMenu(){
        while (true) {
            System.out.println("\n\n *** Get all contacts menu *** \n\n" +
                    "[1] Get all contacts sorted by LAST NAME\n" +
                    "[2] Get all contacts sorted by FIRST NAME\n" +
                    "[0] Exit");
            Integer point = CONSOLE_SCANNER.nextInt();
            switch (point) {
                case 0:
                    return;
                case 1:
                    phoneBook.sort(PhoneBook.SortVariants.BY_LAST_NAME);
                    System.out.println(phoneBook);
                    break;
                case 2:
                    phoneBook.sort(PhoneBook.SortVariants.BY_FIRST_NAME);
                    System.out.println(phoneBook);
                    break;
                default:
                    phoneBook.sort(PhoneBook.SortVariants.BY_LAST_NAME);
                    System.out.println(phoneBook);
                    break;
            }
        }

    }

    private void searchByFirstOrLasNameMenu(){
        System.out.println("\n\n*** SEARCH BY FIRST OR LAST NAME MENU ***\n\n");
        System.out.print("Enter full FIRST/LAST name: ");
        List<Integer> searchResults = phoneBook.searchByFirstOrLastName(CONSOLE_SCANNER.nextLine());
        if(searchResults.size() == 0) {
            System.out.println("No matches found!");
        } else {
            System.out.println(phoneBook.toString(searchResults));
        }
    }

    private void searchByAnyPartOfNameMenu(){
        System.out.println("\n\n*** SEARCH BY ANY PART OF NAME MENU ***\n\n");
        System.out.print("Enter any part of FIRST name: ");
        List<Integer> searchResults = phoneBook.searchByAnyPartOfName(CONSOLE_SCANNER.nextLine());
        if(searchResults.size() == 0) {
            System.out.println("No matches found!");
        } else {
            System.out.println(phoneBook.toString(searchResults));
        }
    }

    private void searchByPhoneNumberMenu(){
        System.out.println("\n\n*** SEARCH BY PHONE NUMBER MENU ***\n\n");
        System.out.print("Enter phone number: ");
        List<Integer> searchResults = phoneBook.searchByPhoneNumber(CONSOLE_SCANNER.nextLine());
        if(searchResults.size() == 0) {
            System.out.println("No matches found!");
        } else {
            System.out.println(phoneBook.toString(searchResults));
        }
    }

    private void searchByAgeMenu(){
        Integer begin, end, expectedAge;
        System.out.println("\n\n*** SEARCH BY AGE MENU ***\n\n");
        System.out.print("Enter expected age or age range [f.e.: 15 - 20]: ");
        String inputData = CONSOLE_SCANNER.nextLine();
        List<Integer> searchResults;
        if (inputData.contains(" - ")) {
            try {
                begin = Integer.parseInt(inputData.substring(0,inputData.indexOf(" - ")));
                end = Integer.parseInt(inputData.substring(inputData.indexOf(" - ") + 3, inputData.length()));
            } catch (NumberFormatException e) {
                System.out.println("Unexpected format of inputed value!");
                return;
            }
            searchResults = phoneBook.searchByAgeRange(begin,end);
        } else {
            try {
                expectedAge = Integer.parseInt(inputData);
            } catch (NumberFormatException e) {
                System.out.println("Unexpected format of inputed value!");
                return;
            }
            searchResults = phoneBook.searchByAge(expectedAge);
        }

        if(searchResults.size() == 0) {
            System.out.println("No matches found!");
        } else {
            System.out.println(phoneBook.toString(searchResults));
        }
    }

    public void authorizationMenu() {
        System.out.println("Please, enter USER NAME/PASSWORD.");
        String userName, pass;
        while(true){
            System.out.print("\nUSER NAME:");
            userName = CONSOLE_SCANNER.nextLine();
            System.out.print("\nPASSWORD:");
            pass = CONSOLE_SCANNER.nextLine();
            if (phoneBook.getUserName().equals(userName) && phoneBook.getPassword().equals(pass)){
                System.out.println("ACCESS GRANTED");
                return;
            } else {
                System.out.println("INCORRECT USER NAME/PASSWORD!");
            }
        }
    }

    public void createNewUserMenu(){
        System.out.println("PLEASE, SET UP USER NAME AND PASSWORD");
        System.out.print("ENTER USER NAME: ");
        phoneBook.setUserName(CONSOLE_SCANNER.nextLine());
        System.out.print("ENTER PASSWORD: ");
        phoneBook.setPassword(CONSOLE_SCANNER.nextLine());
    }

}
