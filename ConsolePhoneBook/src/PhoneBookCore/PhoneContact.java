package PhoneBookCore;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PhoneContact implements Serializable, Comparable {

    private String firstName = "undefined";
    private String lastName = "undefined";
    private ArrayList<PhoneNumberAndType> phoneNumberAndTypeList = new ArrayList<>();
    private String email = "undefined";
    private LocalDate birthDate = null;
    private String address = "undefined";
    private final static DateTimeFormatter dateFormatPattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
        try {
            this.birthDate = LocalDate.parse(birthDate, dateFormatPattern);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format. Value will set to 'none'");
            this.birthDate = null;
        }
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

    public LocalDate getBirthDate() {
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
        StringBuilder phoneContact = new StringBuilder();
        StringBuilder phoneAndType = new StringBuilder();
        String birthDate;

        if (this.birthDate == null ){
            birthDate = "none";
        } else {
            birthDate = this.birthDate.format(dateFormatPattern);
        }

        for(int i = 0; i < this.phoneNumberAndTypeList.size(); i++){
            phoneAndType.append("PHONE NUMBER: " + phoneNumberAndTypeList.get(i).getPhoneNumber() + "\n")
                        .append("PHONE TYPE: "+ phoneNumberAndTypeList.get(i).getPhoneType() + "\n");
        }

        phoneContact.append("FIRST NAME: " + this.firstName + "\n")
                    .append("LAST NAME: " + this.lastName + "\n")
                    .append("EMAIL: " + this.email + "\n")
                    .append(phoneAndType)
                    .append("BIRTH DATE: " + birthDate  + "\n")
                    .append("ADDRESS: " + this.address + "\n");
        return phoneContact.toString();
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
        Integer dateBirthCompareResult = this.birthDate.compareTo(contact.birthDate);
        Integer namesCompareResult = commonName1.compareTo(commonName2);
        return dateBirthCompareResult.compareTo(namesCompareResult);
    }

}


