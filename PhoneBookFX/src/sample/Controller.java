package sample;

import PhoneBookCore.PhoneBook;
import PhoneBookCore.PhoneContact;
import PhoneBookCore.PhoneContactValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    private static final PhoneBook PHONE_BOOK = new PhoneBook();
    @FXML private TextField firstName = new TextField("");
    @FXML private TextField lastName = new TextField("");
    @FXML private TextField phoneNumber1 = new TextField("");
    @FXML private TextField phoneType1 = new TextField("");
    @FXML private TextField phoneNumber2 = new TextField("");
    @FXML private TextField phoneType2 = new TextField("");
    @FXML private TextField phoneNumber3 = new TextField("");
    @FXML private TextField phoneType3 = new TextField("");
    @FXML private TextField email = new TextField("");
    @FXML private TextField birthday = new TextField("");
    @FXML private TextField adress = new TextField("");

    public void goToCreateNewContactWindow(ActionEvent event) throws IOException{
        Parent createWindow = FXMLLoader.load(getClass().getResource("FXML/CreateWindow.fxml"));
        Scene createWindowScene = new Scene(createWindow);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(createWindowScene);
    }

    public void goToMainWindow(ActionEvent event) throws IOException{
        Parent createMainMenu = FXMLLoader.load(getClass().getResource("FXML/MainMenu.fxml"));
        Scene createWindowScene = new Scene(createMainMenu);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(createWindowScene);
    }

    public void createNewContact(ActionEvent event) throws IOException {
        PhoneContact phoneContact = new PhoneContact();
        PhoneContactValidator validator = new PhoneContactValidator(phoneContact);
        phoneContact.setFirstName(this.firstName.getText());
        phoneContact.setLastName(this.lastName.getText());
        phoneContact.setPhoneNumberAndType(this.phoneNumber1.getText(), this.phoneType1.getText());
        phoneContact.setPhoneNumberAndType(this.phoneNumber2.getText(), this.phoneType2.getText());
        phoneContact.setPhoneNumberAndType(this.phoneNumber3.getText(), this.phoneType3.getText());
        phoneContact.setEmail(this.email.getText());
        phoneContact.setBirthDate(this.birthday.getText());
        phoneContact.setAddress(this.adress.getText());
        validator.validate();
        PHONE_BOOK.add(phoneContact);
        this.firstName.setText("");
        this.lastName.setText("");
        this.phoneNumber1.setText("");
        this.phoneType1.setText("");
        this.phoneNumber2.setText("");
        this.phoneType2.setText("");
        this.phoneNumber3.setText("");
        this.phoneType3.setText("");
        this.email.setText("");
        this.birthday.setText("");
        this.adress.setText("");
    }

    public PhoneBook getPhoneBook() {
        return PHONE_BOOK;
    }

//    public String getFirstName() {
//        return firstName.get();
//    }
//
//    public TextField firstNameProperty() {
//        return firstName;
//    }


}
