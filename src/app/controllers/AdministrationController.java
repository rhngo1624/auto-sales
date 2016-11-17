package app.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import app.ui.tableview.AccessoriesTableView;
import app.ui.tableview.CarsTableView;
import app.ui.tableview.FinancialApplicationView;
import app.ui.tableview.UsersTableView;
import app.utils.ModalUtil;
import app.utils.Session;
import db.models.Car;
import db.models.FinancialApplication;
import db.models.User;
import db.tables.Cars;
import db.tables.Finances;
import db.tables.Users;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdministrationController implements Initializable {

    @FXML
    private VBox buttonVBox;
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
    private Button viewButton;
    @FXML
    private TableView table;

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
        table = new UsersTableView();
        showViewButton(false);
        borderPane.setCenter(table);

    }

    public void showCars(){
        table = new CarsTableView();
        showViewButton(false);
        borderPane.setCenter(table);
    }

    public void showAccessories(){
        table = new AccessoriesTableView();
        showViewButton(false);
        borderPane.setCenter(table);
    }

    public void showFinancialApps(){
        table = new FinancialApplicationView();
        showViewButton(true);
        borderPane.setCenter(table);

    }

    public void showTransactions(){

    }

    private void showViewButton(boolean showView){
        buttonVBox.getChildren().clear();
        buttonVBox.getChildren().addAll(addButton, deleteButton, editButton);
        if(showView){
            buttonVBox.getChildren().add(viewButton);
        }
    }

    public void view(){
        if(table.getClass().getSimpleName().equals("FinancialApplicationView")){
            FinancialApplication app = (FinancialApplication)table.getSelectionModel().getSelectedItem();
            if(app != null){
                ((FinancialApplicationView)table).showApp(app, table);
            }
        }
    }

}
