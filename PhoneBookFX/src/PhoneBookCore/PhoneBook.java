package PhoneBookCore;

import java.util.HashSet;

public class PhoneBook {

    private HashSet<PhoneContact> PhoneBook;
    private String userName = "";
    private String password = "";

    public void initialize(){

    }

    public void create(){
        this.PhoneBook = new HashSet();
    }

    public Boolean add(PhoneContact phoneContact){
        return this.PhoneBook.add(phoneContact);
    }

    public Integer size (){
        return this.PhoneBook.size();
    }

}
