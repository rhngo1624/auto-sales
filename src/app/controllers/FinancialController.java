package app.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import app.core.Resource;
import app.utils.ModalUtil;
import app.utils.Session;
import db.models.Car;
import db.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FinancialController implements Initializable {

    @FXML
    private TextField fullnameField;
    @FXML
    private ComboBox<Integer> monthBox;
    @FXML
    private ComboBox<Integer> dayBox;
    @FXML
    private ComboBox<Integer> yearBox;
    @FXML
    private TextField ssnField;
    @FXML
    private TextField dependentNumField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField zipcodeField;
    @FXML
    private TextField homephoneField;
    @FXML
    private TextField cellphoneField;
    @FXML
    private TextField residentialStatusField;
    @FXML
    private TextField monthlyHousePaymentField;
    @FXML
    private TextField propertyHolderField;
    @FXML
    private TextField prevAddressField;
    @FXML
    private TextField currentEmployerNameField;
    @FXML
    private TextField currentEmployerAddressField;
    @FXML
    private TextField grossMonthlySalaryField;
    @FXML
    private TextField workPhoneField;
    @FXML
    private TextField jobTitleField;
    @FXML
    private TextField jobLengthField;
    @FXML
    private TextField otherGrossMonthlyIncomeField;
    @FXML
    private TextField otherIncomeField;
    @FXML
    private TextField ref1Field;
    @FXML
    private TextField ref2Field;
    @FXML
    private TextField ref1PhoneField;
    @FXML
    private TextField ref2PhoneField;
    @FXML
    private TextField driversNoField;

    private User user;


    public FinancialController(){
        user = Session.getInstance().getUser();
    }

    @FXML
    public void initialize(URL location, ResourceBundle rb){
        fullnameField.setText(user.getFirstname() + " " + user.getLastname());
        monthBox.getSelectionModel().select(new Integer(user.getBirthMonth()));
        dayBox.getSelectionModel().select(new Integer(user.getBirthDay()));
        yearBox.getSelectionModel().select(new Integer(user.getBirthYear()));
        addressField.setText(user.getAddress());
        cityField.setText(user.getCity());
        stateField.setText(user.getState());
        zipcodeField.setText(user.getZipcode());
        homephoneField.setText(user.getPhone());
        loadOptions();
    }

    public void print(){

    }

    public void clear(){

    }

    public void submit(){

    }

    private void loadOptions(){
        for(int i = 1; i <= 12; i++){
            monthBox.getItems().add(i);
            dayBox.getItems().add(i);
        }

        for(int i = 13; i <= 31; i++){
            dayBox.getItems().add(i);
        }

        for(int i = 1980; i <= 2016; i++){
            yearBox.getItems().add(i);
        }
    }

}
