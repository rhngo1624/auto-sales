package app.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import app.ui.tableview.AccessoriesTableView;
import app.ui.tableview.CarsTableView;
import app.ui.tableview.UsersTableView;
import app.utils.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class AdministrationController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label adminNameLabel;
    @FXML
    private Button usersButton;
    @FXML
    private Button carsButton;
    @FXML
    private Button accessoriesButton;
    @FXML
    private Button financialAppButton;
    @FXML
    private Button transactionsButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    @FXML
    public void initialize(URL location, ResourceBundle rb){

        if(!Session.getInstance().isActive()){
            System.exit(-1);
        }

        setAdminName();

    }

    private void setAdminName(){
        String name =  Session.getInstance().getUser().getUsername();
        adminNameLabel.setText(name);
    }

    public void showUsers(){

        borderPane.setCenter(new UsersTableView());

    }

    public void showCars(){
        borderPane.setCenter(new CarsTableView());
    }

    public void showAccessories(){
        borderPane.setCenter(new AccessoriesTableView());
    }

    public void showFinancialApps(){

    }

    public void showTransactions(){

    }





}
