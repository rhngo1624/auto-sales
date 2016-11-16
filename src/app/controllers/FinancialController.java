package app.controllers;


import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ResourceBundle;

import app.core.Resource;
import app.utils.ModalUtil;
import app.utils.Session;
import db.models.Car;
import db.models.FinancialApplication;
import db.models.User;
import db.tables.Finances;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static java.lang.Character.isDigit;

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
        setListeners();
    }

    private void setListeners(){
        ValidInputListener listener = new ValidInputListener();
        monthlyHousePaymentField.setOnKeyPressed(listener);
        dependentNumField.setOnKeyPressed(listener);
        grossMonthlySalaryField.setOnKeyPressed(listener);
        otherGrossMonthlyIncomeField.setOnKeyPressed(listener);

    }


    public void clear(){
        fullnameField.setText("");
        ssnField.setText("");
        monthBox.getSelectionModel().select(1);
        dayBox.getSelectionModel().select(1);
        yearBox.getSelectionModel().select(1);
        addressField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipcodeField.setText("");
        homephoneField.setText("");
        dependentNumField.setText("");
        cellphoneField.setText("");
        residentialStatusField.setText("");
        monthlyHousePaymentField.setText("");
        propertyHolderField.setText("");
        prevAddressField.setText("");
        currentEmployerNameField.setText("");
        currentEmployerAddressField.setText("");
        grossMonthlySalaryField.setText("");
        workPhoneField.setText("");
        jobTitleField.setText("");
        jobLengthField.setText("");
        otherGrossMonthlyIncomeField.setText("");
        otherIncomeField.setText("");
        ref1Field.setText("");
        ref2Field.setText("");
        ref1PhoneField.setText("");
        ref2PhoneField.setText("");
        driversNoField.setText("");

    }

    public void submit(){

        if(fieldsAreFilled()){

            FinancialApplication app = createApplication();
            Session.getInstance().getUser().getCart().addCompletedApp(app);
            CheckoutController.getInstance().refreshRequirements();
            new Finances().insert(app);
            prevAddressField.getScene().getWindow().hide();

        }

    }

    private FinancialApplication createApplication(){

        FinancialApplication application = new FinancialApplication();
        application.setUserID(user.getID());
        application.setCarID(user.getCart().getAppInProgress().getID());
        application.setNumberOfDependents(Integer.parseInt(dependentNumField.getText()));
        application.setResidentialStatus(residentialStatusField.getText());
        application.setMonthlyHomePayment(Double.parseDouble(monthlyHousePaymentField.getText()));
        application.setLandLordName(propertyHolderField.getText());
        application.setPreviousAddress(prevAddressField.getText());
        application.setCurrentEmployerName(currentEmployerNameField.getText());
        application.setCurrentEmployerAddress(currentEmployerAddressField.getText());
        application.setGrossMonthlySalary(Double.parseDouble(grossMonthlySalaryField.getText()));
        application.setWorkPhone(workPhoneField.getText());
        application.setJobTitle(jobTitleField.getText());
        application.setJobLength(jobLengthField.getText());
        application.setOtherMonthlyGrossIncome(Double.parseDouble(otherGrossMonthlyIncomeField.getText()));
        application.setOtherIncomeSource(otherIncomeField.getText());
        application.setRef1(ref1Field.getText());
        application.setRef2(ref2Field.getText());
        application.setRef1Phone(ref1PhoneField.getText());
        application.setRef2Phone(ref2PhoneField.getText());
        application.setDriversLicenseNo(driversNoField.getText());
        application.setSSN(ssnField.getText());

        return application;


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

    private boolean fieldsAreFilled(){

        boolean personalInfoEmpty = fullnameField.getText().isEmpty() ||
                monthBox.getSelectionModel().isEmpty() || dayBox.getSelectionModel().isEmpty()
                || yearBox.getSelectionModel().isEmpty() || addressField.getText().isEmpty() ||
                cityField.getText().isEmpty() || stateField.getText().isEmpty() ||
                zipcodeField.getText().isEmpty() || homephoneField.getText().isEmpty() ||
                cellphoneField.getText().isEmpty() || ssnField.getText().isEmpty();

        boolean financeInfoEmpty = dependentNumField.getText().isEmpty() ||
                residentialStatusField.getText().isEmpty() ||
                monthlyHousePaymentField.getText().isEmpty() ||
                propertyHolderField.getText().isEmpty() || prevAddressField.getText().isEmpty() ||
                currentEmployerNameField.getText().isEmpty() ||
                currentEmployerAddressField.getText().isEmpty() ||
                grossMonthlySalaryField.getText().isEmpty() ||
                workPhoneField.getText().isEmpty() || jobTitleField.getText().isEmpty() ||
                jobLengthField.getText().isEmpty() ||
                otherGrossMonthlyIncomeField.getText().isEmpty() ||
                otherIncomeField.getText().isEmpty();

        boolean referenceInfoEmpty = ref1Field.getText().isEmpty() || ref2Field.getText().isEmpty()
                || ref1PhoneField.getText().isEmpty() || ref2PhoneField.getText().isEmpty() ||
                driversNoField.getText().isEmpty();


        if(personalInfoEmpty || financeInfoEmpty || referenceInfoEmpty){
            return false;
        }else{
            return true;
        }

    }

    private class ValidInputListener implements EventHandler<KeyEvent> {

        public void handle(KeyEvent e){

           if(!e.getCode().isDigitKey() && !(e.getCode() == KeyCode.BACK_SPACE) &&
                   !(e.getCode().isArrowKey()) && !(e.getCode() == KeyCode.TAB)){
               ModalUtil.showWarning("Numbers only");
               ((TextField)e.getSource()).setText("");
           }
        }

    }


}
