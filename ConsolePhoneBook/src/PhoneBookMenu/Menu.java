package PhoneBookMenu;

import PhoneBookCore.PhoneBook;
import PhoneBookCore.PhoneContact;
import PhoneBookCore.PhoneNumberAndType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private PhoneBook phoneBook;

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

                    return;
                case "4":

                    return;
                case "5":

                    return;
                case "6":

                    return;
                case "7":

                    return;
                case "8":

                    return;
                case "0":

                    return;

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

        System.out.print("BIRTH DATE: ");
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
        phoneBook.toString();
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
