package PhoneBookCore;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PhoneBook implements Serializable {

    private Set<PhoneContact> phoneBook;
    private String userName = "userName";
    private String password = "password";
    private File phoneBookFile;

    public PhoneBook() {
        this.initialize();
    }

    public void create(){
        this.phoneBook = new HashSet();
        System.out.println("PhoneBook collection successfully created.");
    }

    public void add(PhoneContact phoneContact){
        String tmpFirstName = "";
        int attempts = 0;
        if(isPhoneContactPresents(phoneContact)){
            while (isPhoneContactPresents(phoneContact) || attempts < 10) {
                tmpFirstName = phoneContact.getFirstName() + "_" + attempts;
                phoneContact.setFirstName(tmpFirstName);
                attempts++;
            }
        }
        this.phoneBook.add(phoneContact);
        System.out.println("ALL PHONE BOOK:\n" + this.phoneBook.toString());
        serializeToFile();
    }

//    private void checkAndMergePhoneContacts(PhoneContact phoneContact) {
//        PhoneContact [] tmpArray = this.phoneBook.toArray(new PhoneContact[this.phoneBook.size()]);
//        PhoneContact tmp;
//        if (isPhoneContactPresents(phoneContact)){
//
//        }
//    }

    public void initialize () {
        create();
        try {
            this.phoneBookFile = new File ("phoneBook.ser");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement element:e.getStackTrace()){
                System.out.println(element.toString());
            }
        }
        if (!this.phoneBookFile.isDirectory() && this.phoneBookFile.exists()) {
            System.out.println("'phoneBook.ser' is present.");
            PhoneBook restoredPhoneBook = deserializeFromFile();
            this.phoneBook = restoredPhoneBook.phoneBook;
            this.userName = restoredPhoneBook.userName;
            this.password = restoredPhoneBook.password;
            System.out.println("DE SERIALIZES PhoneBook is:\n" + restoredPhoneBook.toString());
        } else {
            System.out.println("'phoneBook.ser' is NOT present.");
        }
    }

    private void serializeToFile() {
        try {
            try (FileOutputStream fos = new FileOutputStream(this.phoneBookFile);
                 ObjectOutputStream ous = new ObjectOutputStream(fos)) {
                 ous.writeObject(this);
                 System.out.println("'phoneBook' has been serialized to file '" + this.phoneBookFile + "'");
            }
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement element:e.getStackTrace()){
                System.out.println(element.toString());
            }
        }
    }

    public PhoneBook deserializeFromFile(){
        PhoneBook phoneBook = null;
        try {
            try (FileInputStream fis = new FileInputStream(this.phoneBookFile);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
                phoneBook = (PhoneBook) ois.readObject();
                System.out.println("'phoneBook' has been de serialized from file '" + this.phoneBookFile + "'");
            }
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement element:e.getStackTrace()){
                System.out.println(element.toString());
            }
        }
        return phoneBook;
    }

    private String getDataFromPhoneBookFile(){
        String result = "", tmp;
        try (FileReader fr = new FileReader(this.phoneBookFile);
             BufferedReader br = new BufferedReader(fr)){
             while ((tmp = br.readLine()) != null){
                 result += tmp;
             }
             System.out.println("Got line from 'phoneBook.ser':\n" + result);
             return result;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement element:e.getStackTrace()){
                System.out.println(element.toString());
            }
        }
        return null;
    }

    private boolean isPhoneContactPresents(PhoneContact phoneContact){
        for (PhoneContact contactInPhoneBook : phoneBook){
            if (contactInPhoneBook.equals(phoneContact)){
                System.out.println("THERE ARE SIMILAR CONTACTS:" +
                        "\n**********************\n" +
                        contactInPhoneBook.toString() +
                        "\n**********************\n" +
                        phoneContact.toString() + "\n" +
                        "**********************\n" );
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
            result += this.userName + "\n" + this.password + "\n";
        for (PhoneContact phoneContact : this.phoneBook){
            result += phoneContact;
        }
        return result;
    }



}
