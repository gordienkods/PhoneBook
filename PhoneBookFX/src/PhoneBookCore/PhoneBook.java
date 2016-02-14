package PhoneBookCore;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PhoneBook implements Serializable {

    private Set<PhoneContact> phoneBook;
    private String userName = "";
    private String password = "";
    private File phoneBookFile;

    public PhoneBook() {
        this.initialize();
    }

    public void create(){
        this.phoneBook = new HashSet();
        System.out.println("PhoneBook collection successfully created.");
    }

    public void add(PhoneContact phoneContact){
        this.phoneBook.add(phoneContact);
        save();
    }

    public Integer size (){
        return this.phoneBook.size();
    }

    public void initialize () {
        create();
        try {
            this.phoneBookFile = new File ("phoneBook.txt");
            System.out.println("'phoneBook.txt' successfully created.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement element:e.getStackTrace()){
                System.out.println(element.toString());
            }
        }
        if (!this.phoneBookFile.isDirectory() && this.phoneBookFile.exists()) {
            System.out.println("'phoneBook.txt' is present.");
        } else {
            System.out.println("'phoneBook.txt' is NOT present.");
        }
    }

    private Boolean isExist(){
        if (!this.phoneBookFile.isDirectory() && this.phoneBookFile.exists()){

            return true;
        } else {

            return false;
        }
    }

    private void save(){
        try (FileWriter fw = new FileWriter(this.phoneBookFile);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
             pw.print(this.phoneBook);
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement element:e.getStackTrace()){
                System.out.println(element.toString());
            }
        }
    }

    private void plugInPhoneBook(){

    }



}
