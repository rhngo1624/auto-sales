package app.controllers;


import com.apple.laf.AquaButtonBorder;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;

import app.ui.tableview.AccessoriesTableView;
import app.ui.tableview.CarsTableView;
import app.ui.tableview.FinancialApplicationView;
import app.ui.tableview.TransactionsView;
import app.ui.tableview.UsersTableView;
import app.utils.ModalUtil;
import app.utils.Session;
import app.utils.StageUtil;
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
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdministrationController implements Initializable {

    @FXML
    private ToolBar buttonToolbar;
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
    private VBox center;
    private JFXTextField search;

    @FXML
    public void initialize(URL location, ResourceBundle rb){

        if(!Session.getInstance().isActive()){
            System.exit(-1);
        }

        center = new VBox();

        setAdminName();

    }

    private void setAdminName(){
        String name =  Session.getInstance().getUser().getUsername();
        adminNameLabel.setText(name);
    }

    public void showUsers(){
        center.getChildren().clear();
        table = new UsersTableView();
        setupSearch();
        center.getChildren().add(table);
        borderPane.setCenter(center);

    }

    public void showCars(){
        center.getChildren().clear();
        table = new CarsTableView();
        setupSearch();
        center.getChildren().add(table);
        borderPane.setCenter(center);
    }

    public void showAccessories(){
        center.getChildren().clear();
        setupSearch();
        table = new AccessoriesTableView();
        center.getChildren().add(table);
        borderPane.setCenter(center);
    }

    public void showFinancialApps(){
        center.getChildren().clear();
        setupSearch();
        table = new FinancialApplicationView();
        center.getChildren().add(table);
        borderPane.setCenter(center);

    }

    public void showTransactions(){
        center.getChildren().clear();
        setupSearch();
        TransactionsView view = new TransactionsView();
        center.getChildren().add(view);
        borderPane.setCenter(center);

    }

    private void setupSearch(){
        search = new JFXTextField();
        search.setPromptText("Search...");
        search.setOnAction((e) -> search());
        VBox.setMargin(search, new Insets(5,0,7,0));
        center.getChildren().add(search);
    }

    public void view(){
        if(table == null){
            return;
        }
        if(table.getClass().getSimpleName().equals("FinancialApplicationView")){
            FinancialApplication app = (FinancialApplication)table.getSelectionModel().getSelectedItem();
            if(app != null){
                ((FinancialApplicationView)table).showApp(app, table);
            }
        }
    }

    public void search(){
        String str = "searching..." + search.getText();
        System.out.println(str);
    }

    public void add(){

    }

    public void delete(){


    }

    public void edit(){


    }

    public void returnToMain(){
        ((Stage)buttonToolbar.getScene().getWindow()).close();
    }

}
