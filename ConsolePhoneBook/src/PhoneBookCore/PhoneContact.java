package PhoneBookCore;

import java.io.Serializable;
import java.util.ArrayList;

public class PhoneContact implements Serializable, Comparable {

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
        if (phoneNumber.length() != 0 || !isPhoneNumberPresents(phoneNumberAndType)) {
            this.phoneNumberAndTypeList.add(phoneNumberAndType);
        }
    }

    private boolean isPhoneNumberPresents(PhoneNumberAndType phoneNumberAndType) {
        for (PhoneNumberAndType current : this.phoneNumberAndTypeList ){
            if(current.equals(phoneNumberAndType)){
                return true;
            }
        }
        return false;
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

    public ArrayList<PhoneNumberAndType> getPhoneNumberAndTypeList() {
        return phoneNumberAndTypeList;
    }

    @Override
    public String toString(){
        String phoneAndType = "";
        String phoneContact;
        for(int i = 0; i < this.phoneNumberAndTypeList.size(); i++){
            phoneAndType +=
            "PHONE NUMBER: " + phoneNumberAndTypeList.get(i).getPhoneNumber() + "\n" +
            "PHONE TYPE: "+ phoneNumberAndTypeList.get(i).getPhoneType() + "\n";
        }
        phoneContact =
        "FIRST NAME: " + this.firstName + "\n" +
        "LAST NAME: " + this.lastName + "\n" +
        "EMAIL: " + this.email + "\n" +
        phoneAndType +
        "BIRTH DATE: " + birthDate + "\n" +
        "ADDRESS: " + this.address + "\n";
        return phoneContact;
    }

    @Override
    public int hashCode() {
        int baseValue = 11;
        int result = 1;
        result = baseValue * result + this.firstName.hashCode();
        result = baseValue * result + this.lastName.hashCode();
        result = baseValue * result + this.birthDate.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        PhoneContact other = (PhoneContact) obj;
        if (!this.firstName.equals(other.firstName)) {
            return false;
        }
        if (!this.lastName.equals(other.lastName)) {
            return false;
        }
        if (!this.birthDate.equals(other.birthDate)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object obj){
        String commonName1, commonName2;
        PhoneContact contact = (PhoneContact) obj;
        commonName1 = this.firstName + this.lastName;
        commonName2 = contact.firstName + contact.lastName;
        return commonName1.compareTo(commonName2);
    }

}

