package app.controllers;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import app.ui.UsersTableView;
import app.utils.ModalUtil;
import app.utils.Session;
import app.core.SQLModel;
import db.models.User;
import db.tables.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

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

    }

    public void showAccessories(){

    }

    public void showFinancialApps(){

    }

    public void showTransactions(){

    }





}
