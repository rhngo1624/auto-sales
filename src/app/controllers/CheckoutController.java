package app.controllers;

import db.models.Transaction;
import db.tables.Cars;
import db.tables.Transactions;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Locale;

import app.core.StoreItem;
import app.ui.tableview.CartTableView;
import app.utils.ModalUtil;
import app.core.Resource;
import app.utils.Session;
import app.utils.StageUtil;
import db.models.Car;
import db.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *  Controller Class for Check Out Page
 */
public class CheckoutController implements Initializable {

    private User user;
    private static CheckoutController instance;

    @FXML
    private VBox mainPane;
    @FXML
    private VBox itemPane;
    @FXML
    private TextField fnField;
    @FXML
    private TextField lnField;
    @FXML
    private TextField cardNumField;
    @FXML
    private TextField CVField;
    @FXML
    private ComboBox<Integer> expMonth;
    @FXML
    private ComboBox<Integer> expYear;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField zipField;
    @FXML
    private ComboBox<String> countryBox;
    @FXML
    private TextField phoneField;
    private Button checkout;
    private ArrayList<Button> financeButtons;

    public static CheckoutController getInstance(){
        return CheckoutController.instance;
    }
    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){
        instance = this;
        user = Session.getInstance().getUser();
        financeButtons = new ArrayList<>();
        fillOutComboBoxes();

        if(user != null){
            CartTableView ctv = new CartTableView(this);
            itemPane.getChildren().add(ctv);
            checkForRequirements();
        }else{
            ModalUtil.showWarning("Not logged in.");

            try{

                StageUtil.getInstance().getMainStage().
                        getScene().setRoot(FXMLLoader.load(Resource.MAIN));

            }catch(IOException e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }

            mainPane.getScene().getWindow().hide();

        }

    }



    public void checkout(){

        if(validate()){

            Transaction transaction = new Transaction();
            transaction.setSerializedItems(user.getCart().dump());
            transaction.setDate(new Date().toString());
            transaction.setUser(user);

            new Transactions().insert(transaction);

            ModalUtil.showMessage("Items will be shipped within a week!\n Thank you for your service.");
            itemPane.getScene().getWindow().hide();


        }else{
            ModalUtil.showWarning("Please fill out all fields.");
        }

    }

    private boolean validate(){
        boolean personalInfoEmpty = fnField.getText().isEmpty() || lnField.getText().isEmpty()
                || addressField.getText().isEmpty() || cityField.getText().isEmpty() ||
                zipField.getText().isEmpty() || countryBox.getSelectionModel().isEmpty() ||
                phoneField.getText().isEmpty();
        boolean financeInfoEmpty = cardNumField.getText().isEmpty() || CVField.getText().isEmpty() ||
                expMonth.getSelectionModel().isEmpty() || expYear.getSelectionModel().isEmpty();

        return !(personalInfoEmpty || financeInfoEmpty);
    }

    private void checkForRequirements(){

        if(user.getCart().getRequirements().isEmpty()){
            HBox box = new HBox();
            box.setAlignment(Pos.TOP_CENTER);
            checkout = new Button("Check Out");
            HBox.setMargin(checkout, new Insets(20,0,0,0));
            checkout.getStyleClass().add("toolbar_button");
            checkout.setOnAction((e) -> {
                checkout();
            });
            box.getChildren().add(checkout);
            mainPane.getChildren().add(box);
            mainPane.setPrefHeight(650);
        }

        for(Car car : user.getCart().getRequirements()){
            prepareDocumentButton(car);
        }

        displayButtons();

    }

    public void refreshRequirements(){
        mainPane.setPrefHeight(715);
        mainPane.getChildren().remove(checkout);
        mainPane.getChildren().removeAll(financeButtons);
        financeButtons.clear();
        checkForRequirements();
    }

    private void displayButtons(){
        mainPane.getChildren().addAll(financeButtons);
    }

    private void prepareDocumentButton(Car car){

        Button financeAppButton = new Button(car.getName() + " Financial Application");
        financeAppButton.getStyleClass().add("finance_button");
        financeAppButton.setOnAction((e) -> {

            ModalUtil.setupAndShow(Resource.FINANCE, "Financial Application", false);
            Session.getInstance().getUser().getCart().setAppInProgress(car);

        });

        financeButtons.add(financeAppButton);

    }

    public void autofill(){
        fnField.setText(user.getFirstname());
        lnField.setText(user.getLastname());
        addressField.setText(user.getAddress());
        cityField.setText(user.getCity());
        zipField.setText(user.getZipcode());
        phoneField.setText(user.getPhone());
    }

    public void fillOutComboBoxes(){
        for(int i = 2012; i <= 2100; i++){
            expYear.getItems().add(i);
        }

        for(int i = 1; i <= 12; i++){
            expMonth.getItems().add(i);
        }

        String[] locales = Locale.getISOCountries();

        for(String countryCode : locales){
            Locale obj = new Locale("", countryCode);
            countryBox.getItems().add(obj.getDisplayCountry());

        }

    }


}