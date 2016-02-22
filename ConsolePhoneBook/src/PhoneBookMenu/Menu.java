package PhoneBookMenu;

import PhoneBookCore.PhoneBook;
import PhoneBookCore.PhoneContact;
import PhoneBookCore.PhoneNumberAndType;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private PhoneBook phoneBook;
    private Scanner sc = new Scanner(System.in);

    public Menu (PhoneBook phoneBook)  {
        this.phoneBook = phoneBook;
    }

    public void start(){
        String command;
        Scanner sc = new Scanner(System.in);

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
            command = sc.next();
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
        Scanner sc = new Scanner(System.in);
        PhoneContact phoneContact = new PhoneContact();
        Integer phoneNumberCouner = 0;
        System.out.print("\n\n ***CREATION OF NEW CONTACT***\n\n");

        System.out.print("How many phones do yuo want to add? ");
        phoneNumberCouner = sc.nextInt();

        System.out.print("FIRST NAME: ");
        phoneContact.setFirstName(sc.next());

        System.out.print("LAST NAME: ");
        phoneContact.setLastName(sc.next());

        addPhoneAndPhoneTypesSubMenu(phoneContact,phoneNumberCouner);

        System.out.print("EMAIL: ");
        phoneContact.setEmail(sc.next());

        System.out.print("BIRTH DATE[dd.MM.yyyy]: ");
        phoneContact.setBirthDate(sc.next());

        System.out.print("ADDRESS: ");
        phoneContact.setAddress(sc.next());

        phoneBook.add(phoneContact);

//        PhoneContactValidator validator = new PhoneContactValidator(phoneContact);
//        validator.validate();
//        if (validator.getValidateErrors().size() == 0){
//            phoneBook.add(phoneContact);
//            System.out.println("Phone contact successfully created ad added!");
//        } else {
//            validator.printErrors();
//        }

    }

    private void addPhoneAndPhoneTypesSubMenu(PhoneContact phoneContact, Integer counter) {
        String tmpPhoneNumber = "", tmpPhoneType = "";
        Scanner sc = new Scanner(System.in);
        if (counter <= 0){
            return;
        }
        for (int i = 0; i < counter; i++){
            System.out.print("PHONE NUMBER [" + (1+i) + " of " + counter +" ]: ");
            tmpPhoneNumber = sc.next();
            System.out.print("PHONE TYPE [" + (1+i) + " of " + counter +" ]: ");
            tmpPhoneType = sc.next();
            phoneContact.setPhoneNumberAndType(tmpPhoneNumber,tmpPhoneType);
        }
    }

    public void editContactMenu (){
        PhoneContact editablePhoneContact;
        phoneBook.sort(PhoneBook.SortVariants.BY_LAST_NAME);
        System.out.println(phoneBook.toString());
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\n ***EDIT CONTACT MENU***\n\n");
        System.out.print("Enter number of contact: ");
        Integer numberOfContact = sc.nextInt() - 1;

        if (numberOfContact < 0 || numberOfContact > phoneBook.size()){
            System.out.println("Unexpected contact number!");
            return;
        }

        editablePhoneContact = phoneBook.get(numberOfContact);

        System.out.println("CURRENT FIRST NAME: " + editablePhoneContact.getFirstName());
        System.out.print("ENTER NEW FIRST NAME: ");
        editablePhoneContact.setFirstName(sc.next());

        System.out.println("CURRENT LAST NAME: " + editablePhoneContact.getLastName());
        System.out.print("ENTER NEW LAST NAME: ");
        editablePhoneContact.setLastName(sc.next());

        editPhoneAndPhoneTypesSubMenu(editablePhoneContact);

        System.out.println("CURRENT EMAIL: " + editablePhoneContact.getEmail());
        System.out.print("ENTER NEW EMAIL: ");
        editablePhoneContact.setEmail(sc.next());

        System.out.println("CURRENT BIRTH DAY: " + editablePhoneContact.getBirthDate());
        System.out.print("ENTER NEW BIRTH DAY: ");
        editablePhoneContact.setBirthDate(sc.next());

        System.out.println("CURRENT ADDRESS: " + editablePhoneContact.getAddress());
        System.out.print("ENTER NEW ADDRESS: ");
        editablePhoneContact.setAddress(sc.next());

        phoneBook.serializeToFile();

        System.out.println("Contact '" + (numberOfContact + 1) + "' successfully updated!");
    }

    public void deleteContactMenu(){
        PhoneContact deletePhoneContact;
        phoneBook.sort(PhoneBook.SortVariants.BY_LAST_NAME);
        System.out.println(phoneBook.toString());
        Scanner sc = new Scanner(System.in);

        System.out.print("\n\n ***DELETE CONTACT MENU***\n\n");
        System.out.print("Enter number of contact: ");
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
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < phoneNumberAndTypeList.size(); i++){
            System.out.println("CURRENT PHONE NUMBER [" + (1+i) + " of " + (1+i) +" ]: " + phoneNumberAndTypeList.get(i).getPhoneNumber());
            System.out.println("CURRENT PHONE TYPE [" + (1+i) + " of " + (1+i) +" ]: " + phoneNumberAndTypeList.get(i).getPhoneType());
            System.out.print("ENTER NEW PHONE NUMBER [" + (1+i) + " of " + (1+i) +" ]: ");
            phoneNumberAndTypeList.get(i).setPhoneNumber(sc.next());
            System.out.print("ENTER NEW PHONE TYPE [" + (1+i) + " of " + (1+i) +" ]: ");
            phoneNumberAndTypeList.get(i).setPhoneType(sc.next());
        }
    }

    private void getAllContactsMenu(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n***Get all contacts menu***\n\n" +
                    "[1] Get all contacts sorted by LAST NAME\n" +
                    "[2] Get all contacts sorted by FIRST NAME\n" +
                    "[0] Exit");
            Integer point = sc.nextInt();
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
        List<Integer> searchResults = phoneBook.searchByFirstOrLastName(sc.next());
        if(searchResults.size() == 0) {
            System.out.println("No matches found!");
        } else {
            System.out.println(phoneBook.toString(searchResults));
        }
    }

    private void searchByAnyPartOfNameMenu(){
        System.out.println("\n\n*** SEARCH BY ANY PART OF NAME MENU ***\n\n");
        System.out.print("Enter any part of FIRST name: ");
        List<Integer> searchResults = phoneBook.searchByAnyPartOfName(sc.next());
        if(searchResults.size() == 0) {
            System.out.println("No matches found!");
        } else {
            System.out.println(phoneBook.toString(searchResults));
        }
    }

    private void searchByPhoneNumberMenu(){
        System.out.println("\n\n*** SEARCH BY PHONE NUMBER MENU ***\n\n");
        System.out.print("Enter phone number: ");
        List<Integer> searchResults = phoneBook.searchByPhoneNumber(sc.next());
        if(searchResults.size() == 0) {
            System.out.println("No matches found!");
        } else {
            System.out.println(phoneBook.toString(searchResults));
        }
    }

    private void searchByAgeMenu(){
        System.out.println("\n\n*** SEARCH BY AGE MENU ***\n\n");
        System.out.print("Enter expected age: ");
        List<Integer> searchResults = phoneBook.searchByAge(sc.nextInt());
        if(searchResults.size() == 0) {
            System.out.println("No matches found!");
        } else {
            System.out.println(phoneBook.toString(searchResults));
        }
    }

    private void clscr (){
//        String ANSI_CLEAR_SEQ = "\u001b[2J";
//        System.out.println(ANSI_CLEAR_SEQ);
//        try {
//            Runtime.getRuntime().exec("cls");
//        } catch (IOException e) {
//            System.err.println("Error occurred during screen cleaning!");
//            e.printStackTrace();
//        }
    }

}
