package PhoneBookCore;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
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

    public boolean initialize () {
        this.phoneBook = new ArrayList<>();
        this.phoneBookFile = new File ("phoneBook.ser");

        if (!this.phoneBookFile.isDirectory() && this.phoneBookFile.exists()) {
            System.out.println("'phoneBook.ser' is present.");
            PhoneBook restoredPhoneBook = deserializeFromFile();
            this.phoneBook = restoredPhoneBook.phoneBook;
            this.userName = restoredPhoneBook.userName;
            this.password = restoredPhoneBook.password;
            return true;
        } else {
            return false;
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

    public void delete(PhoneContact phoneContactForDeletiong) {
        List tmpPhoneBook = new ArrayList<PhoneContact>();
        for(PhoneContact phoneContact : phoneBook ){
            if(!phoneContact.equals(phoneContactForDeletiong)){
                tmpPhoneBook.add(phoneContact);
            }
        }
        phoneBook = tmpPhoneBook;
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

    public List<Integer> serchByFirstName(String firstName){
        List<Integer> searchResults = new ArrayList<>();
        sort(SortVariants.BY_FIRST_NAME);
        for(int i =0 ; i < phoneBook.size(); i++){
            if(phoneBook.get(i).getFirstName().contains(firstName)){
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    public List<Integer> serchByLastName(String lastName){
        List<Integer> searchResults = new ArrayList<>();
        sort(SortVariants.BY_LAST_NAME);
        for(int i =0 ; i < phoneBook.size(); i++){
            if(phoneBook.get(i).getFirstName().contains(lastName)){
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    public List<Integer> searchByFirstOrLastName(String firstOrLastName){
        List<Integer> searchResults = new ArrayList<>();
        sort(SortVariants.BY_LAST_NAME);
        for(int i =0 ; i < phoneBook.size(); i++){
            if(phoneBook.get(i).getFirstName().equals(firstOrLastName) || phoneBook.get(i).getLastName().equals(firstOrLastName)){
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    public List<Integer> searchByAnyPartOfName(String anyPartOfName){
        List<Integer> searchResults = new ArrayList<>();
        sort(SortVariants.BY_LAST_NAME);
        for(int i =0 ; i < phoneBook.size(); i++){
            if(phoneBook.get(i).getFirstName().contains(anyPartOfName)){
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    public List<Integer> searchByPhoneNumber(String phoneNumber){
        List<Integer> searchResults = new ArrayList<>();
        sort(SortVariants.BY_LAST_NAME);
        for(int i =0 ; i < phoneBook.size(); i++){
            List<PhoneNumberAndType> phoneNumberAndTypes = phoneBook.get(i).getPhoneNumberAndTypeList();
            for (PhoneNumberAndType phoneNumberAndType : phoneNumberAndTypes){
                if (phoneNumberAndType.getPhoneNumber().equals(phoneNumber)){
                    searchResults.add(i);
                    break;
                }
            }
        }
        return searchResults;
    }

    public List<Integer> searchByAge (Integer expectedAge){
        List<Integer> searchResults = new ArrayList<>();
        sort(SortVariants.BY_LAST_NAME);
        for(int i =0 ; i < phoneBook.size(); i++){
           LocalDate birthDate = phoneBook.get(i).getBirthDate();
            if (birthDate == null) {
                break;
            }
            Period period = Period.between(birthDate,LocalDate.now());
            if(period.getYears() == expectedAge){
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    public List<Integer> searchByAgeRange (Integer begin, Integer end){
        List<Integer> searchResults = new ArrayList<>();
        sort(SortVariants.BY_LAST_NAME);
        for(int i =0 ; i < phoneBook.size(); i++){
            LocalDate birthDate = phoneBook.get(i).getBirthDate();
            if (birthDate == null) {
                break;
            }
            Period period = Period.between(birthDate,LocalDate.now());
            if( begin <= period.getYears() && period.getYears() <= end){
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phoneBook.size(); i++){
            sb.append("---------- PHONE CONTACT NUMBER: " + (i + 1) + " ----------\n");
            sb.append(phoneBook.get(i).toString());
        }
        return sb.toString();
    }

    public String toString(List<Integer> searchResults) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < searchResults.size(); i++){
            sb.append("---------- PHONE CONTACT NUMBER: " + (searchResults.get(i) + 1) + " ----------\n");
            sb.append(phoneBook.get(searchResults.get(i)).toString());
        }
        return sb.toString();
    }

    public enum SortVariants {
        BY_FIRST_NAME, BY_LAST_NAME
    }
}
