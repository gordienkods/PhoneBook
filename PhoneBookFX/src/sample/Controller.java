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

    private static PhoneBook phoneBook = new PhoneBook();
    @FXML private TextField firstName = new TextField("");
    @FXML private TextField  lastName = new TextField("");
    @FXML private TextField  phoneNumber = new TextField("");
    @FXML private TextField phoneType = new TextField("");
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
        phoneContact.setPhoneNumberAndType(this.phoneNumber.getText(), this.phoneType.getText());
        phoneContact.setEmail(this.email.getText());
        phoneContact.setBirthDate(this.birthday.getText());
        phoneContact.setAddress(this.adress.getText());
        validator.validate();
        phoneBook.add(phoneContact);
    }



    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

//    public String getFirstName() {
//        return firstName.get();
//    }
//
//    public TextField firstNameProperty() {
//        return firstName;
//    }


}
