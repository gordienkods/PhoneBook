package PhoneBookCore;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PhoneBook implements Serializable {

    private List<PhoneContact> phoneBook;
    private String userName = "userName";
    private String password = "password";
    private File phoneBookFile;
    private Integer nameUniqueDigit = 1;

    public PhoneBook() {
        this.initialize();
    }

    public void create(){
        this.phoneBook = new ArrayList<>();
        System.out.println("PhoneBook collection successfully created.");
    }

    public void add(PhoneContact phoneContact){
        String tmpFirstName = "";
        tmpFirstName = phoneContact.getFirstName();
        while(isPhoneContactPresents(phoneContact)){
              if (this.nameUniqueDigit != 1) {
                  tmpFirstName = tmpFirstName.replace("_" + this.nameUniqueDigit, "" );
              }
              tmpFirstName += "_" + this.nameUniqueDigit++;
              phoneContact.setFirstName(tmpFirstName);
        }
        this.phoneBook.add(phoneContact);
        System.out.println("ALL PHONE BOOK:\n" + this.phoneBook.toString());
        serializeToFile();
        this.nameUniqueDigit = 1;
    }

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

    public void sort(SortVariants variants){
        switch (variants){
            case BY_FIRST_NAME : Collections.sort(phoneBook, new Comparator<PhoneContact>() {
                @Override
                public int compare(PhoneContact o1, PhoneContact o2) {
                    return o1.getFirstName().compareTo(o2.getFirstName());
                }
            });
            case BY_LAST_NAME : Collections.sort(phoneBook, new Comparator<PhoneContact>() {
                @Override
                public int compare(PhoneContact o1, PhoneContact o2) {
                    return o1.getLastName().compareTo(o2.getLastName());
                }
            });
            default :  Collections.sort(phoneBook, new Comparator<PhoneContact>() {
                @Override
                public int compare(PhoneContact o1, PhoneContact o2) {
                    return o1.getLastName().compareTo(o2.getLastName());
                }
            });
        }

    }

    public Integer size() {
        return phoneBook.size();
    }

    public PhoneContact get(Integer i) {
        return phoneBook.get(i);
    }

    public void serializeToFile() {
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
        StringBuilder sb = new StringBuilder();
//            result += this.userName + "\n" + this.password + "\n";
        for (int i = 0; i < phoneBook.size(); i++){
            sb.append("---------- PHONE CONTACT NUMBER: " + (i + 1) + " ----------\n");
            sb.append(phoneBook.get(i).toString());
        }
        return sb.toString();
    }

    public enum SortVariants {
        BY_FIRST_NAME, BY_LAST_NAME
    }
}
