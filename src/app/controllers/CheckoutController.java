package app.controllers;

import db.tables.Cars;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

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
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *  Controller Class for Check Out Page
 */
public class CheckoutController implements Initializable {

    private User user;

    @FXML
    private VBox mainPane;
    @FXML
    private VBox itemPane;
    private ArrayList<Button> financeButtons;

    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){

        user = Session.getInstance().getUser();
        financeButtons = new ArrayList<>();

        if(user != null){
            System.out.println(user.getUsername());
            CartTableView ctv = new CartTableView(this);
            itemPane.getChildren().add(ctv);
            checkForRequirements();
        }else{
            ModalUtil.showMessage("Not logged in.");

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


    }

    private void checkForRequirements(){

        if(user.getCart().getRequirements().isEmpty()){
            return;
        }

        for(Car car : user.getCart().getRequirements()){
            prepareDocumentButton(car);
        }

        displayButtons();

    }

    public void refreshRequirements(){
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

            ModalUtil.setupAndShow(Resource.FINANCE, "Financial Application");

        });

        financeButtons.add(financeAppButton);

    }


}