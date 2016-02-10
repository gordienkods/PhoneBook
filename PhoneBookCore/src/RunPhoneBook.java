public class RunPhoneBook {
    public static void main(String[] args) throws Exception {
        PhoneContact phoneContact = new PhoneContact();
        phoneContact.setFirstName("Vasya");
        phoneContact.setLastName("Pupkin");
        phoneContact.setAddress("Ivanovo city");
        phoneContact.setBirthDate("10.10.2010");
        phoneContact.setPhoneNumberAndType("555-555-555", "HOME");
        phoneContact.setPhoneNumberAndType("666-666-666", "WORK");
        phoneContact.setPhoneNumber("777-777-777");
        phoneContact.setEmail("vasya@pupkin.com");

        PhoneContactValidator validator = new PhoneContactValidator(phoneContact);
        validator.validate();
        for (int i = 0; i < validator.getValidateErrors().size(); i++){
            System.out.println(validator.getValidateErrors().get(i));
        }

        System.out.println(phoneContact.getPhoneContact() + "\n" + "VALIDATION STATUS: " + validator.validate());
    }
}
