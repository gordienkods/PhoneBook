package PhoneBookCore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;

public class PhoneContact implements Serializable {

    private String firstName = "undefined";
    private String lastName = "undefined";
    private ArrayList<PhoneNumberAndType> phoneNumberAndTypeList = new ArrayList<>();
    private String email = "undefined";
    private String birthDate = "undefined";
    private String address = "undefined";

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumberAndType(String phoneNumber, String phoneType) {
        PhoneNumberAndType phoneNumberAndType = new PhoneNumberAndType(phoneNumber, phoneType);
        this.phoneNumberAndTypeList.add(phoneNumberAndType);
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumberAndType phoneNumberAndType = new PhoneNumberAndType(phoneNumber);
        this.phoneNumberAndTypeList.add(phoneNumberAndType);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString(){
        String phoneAndType = "";
        String phoneContact = "";
        for(int i = 0; i < this.phoneNumberAndTypeList.size(); i++){
            phoneAndType +=
            "PHONE NUMBER: " + phoneNumberAndTypeList.get(i).getPhoneNumber() + "\n" +
            "PHONE TYPE: "+ phoneNumberAndTypeList.get(i).getPhoneType() + "\n";
        }
        phoneContact =
        "------------------------------------------\n" +
        "FIRST NAME: " + this.firstName + "\n" +
        "LAST NAME: " + this.lastName + "\n" +
        "EMAIL: " + this.email + "\n" +
        phoneAndType +
        "BIRTH DATE: " + birthDate + "\n" +
        "ADDRESS: " + this.address + "\n" +
        "------------------------------------------\n";
        return phoneContact;
    }
}

class PhoneNumberAndType implements Serializable {

    String phoneNumber = "undefined";
    String phoneType = "undefined";

    public PhoneNumberAndType(String phoneNumber, String phoneType) {
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
    }

    public PhoneNumberAndType(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }
}
