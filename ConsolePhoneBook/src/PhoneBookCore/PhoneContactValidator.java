package PhoneBookCore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhoneContactValidator {

    private PhoneContact phoneContact;
    private ArrayList<String> validateErrors = new ArrayList();
    private SimpleDateFormat birthDateFormat = new SimpleDateFormat("dd.MM.yyyy");


    public PhoneContactValidator (PhoneContact phoneContact){
        this.phoneContact = phoneContact;
    }

    public ArrayList getValidateErrors() {
        return validateErrors;
    }

    private boolean validateFirstLastName(){
        if(this.phoneContact.getFirstName().equals("undefined") && this.phoneContact.getLastName().equals("undefined")){
            this.validateErrors.add("Contact should contains 'First Name' or 'Last Name'.");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateBirthDate(){
        try {
            birthDateFormat.format(phoneContact.getBirthDate());
        } catch (IllegalArgumentException e) {
            this.validateErrors.add("Unexpected birth day format! Please, use next format: 'dd.MM.yyyy'.");
            return false;
        }
        return true;
    }

    public boolean validate(){
        validateFirstLastName();
        validateBirthDate();
        if (this.validateErrors.size() > 0){
            return false;
        } else {
            return true;
        }
    }

    public String printErrors(){
        StringBuilder sb = new StringBuilder();
        for (String s : validateErrors){
            sb.append(s + "\n");
        }
        return sb.toString();
    }

}
