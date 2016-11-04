package app.controllers;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *  Controller Class for Register Page
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField adminCodeField;

    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){

    }

    public void sendRequest(){


    }

    private boolean validate(){

        boolean uname = usernameField.getText().isEmpty();
        boolean fnfield = firstNameField.getText().isEmpty();
        boolean lnfield = lastNameField.getText().isEmpty();
        boolean addfield = addressField.getText().isEmpty();
        boolean cityfield = cityField.getText().isEmpty();
        boolean statefield = stateField.getText().isEmpty();
        boolean zipfield = zipCodeField.getText().isEmpty();
        boolean phonefield = phoneField.getText().isEmpty();
        boolean adminfield = adminCodeField.getText().isEmpty();

        boolean empty = uname && fnfield && lnfield && addfield
                && cityfield && statefield && zipfield && phonefield
                && adminfield;

        return !(empty || !(passwordField.getText().equals(confirmPasswordField.getText())));

    }


}