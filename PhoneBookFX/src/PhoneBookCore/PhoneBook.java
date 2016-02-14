package PhoneBookCore;

import Exceptions.getDataFromPhoneBookFileException;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class PhoneBook implements Serializable {

    private Set<PhoneContact> phoneBook;
    private String userName = "userName";
    private String password = "password";
    private File phoneBookFile;
    private String decodetData;

    public PhoneBook() {
        this.initialize();
    }

    public void create(){
        this.phoneBook = new HashSet();
        System.out.println("PhoneBook collection successfully created.");
    }

    public void add(PhoneContact phoneContact){
        this.phoneBook.add(phoneContact);
        saveToFile();
    }

    public void initialize () {
        create();
        try {
            this.phoneBookFile = new File ("phoneBook.txt");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement element:e.getStackTrace()){
                System.out.println(element.toString());
            }
        }
        if (!this.phoneBookFile.isDirectory() && this.phoneBookFile.exists()) {
            System.out.println("'phoneBook.txt' is present.");
            decodeDataFromPhoneBookFile();
        } else {
            System.out.println("'phoneBook.txt' is NOT present.");
        }
    }

    private void saveToFile(){
        try (FileWriter fw = new FileWriter(this.phoneBookFile);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
             pw.print(this);
            System.out.println("'phoneBook' successfully write to file.");
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement element:e.getStackTrace()){
                System.out.println(element.toString());
            }
        }
    }

    private String decodeDataFromPhoneBookFile(){
        String encodedData = getDataFromPhoneBookFile();
        if (encodedData == null){
            throw new getDataFromPhoneBookFileException();
        }
        byte [] bytesDecoded = Base64.getDecoder().decode(encodedData.getBytes());
        encodedData = new String(bytesDecoded);
        System.out.println(encodedData);
        return encodedData;
    }

    private String getDataFromPhoneBookFile(){
        String result = "", tmp;
        try (FileReader fr = new FileReader(this.phoneBookFile);
             BufferedReader br = new BufferedReader(fr)){
             while ((tmp = br.readLine()) != null){
                 result += tmp;
             }
             System.out.println("Got line from 'phoneBook.txt':\n" + result);
             return result;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement element:e.getStackTrace()){
                System.out.println(element.toString());
            }
        }
        return null;
    }



    @Override
    public String toString() {
        String result = "";
            result += this.userName + "\n" + this.password + "\n";
        for (PhoneContact phoneContact : this.phoneBook){
            result += phoneContact;
        }
        byte [] bytesEncoded = Base64.getEncoder().encode(result.getBytes());
        result = new String(bytesEncoded);
        return result;
    }



}
