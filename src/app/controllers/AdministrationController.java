package app.controllers;


import com.apple.laf.AquaButtonBorder;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.net.URL;
import java.util.ResourceBundle;

import app.core.Resource;
import app.core.SQLModel;
import app.ui.tableview.AccessoriesTableView;
import app.ui.tableview.AppointmentsTableView;
import app.ui.tableview.CarsTableView;
import app.ui.tableview.FinancialApplicationView;
import app.ui.tableview.TransactionsView;
import app.ui.tableview.UsersTableView;
import app.utils.ModalUtil;
import app.utils.Session;
import app.utils.StageUtil;
import db.models.Accessory;
import db.models.Appointment;
import db.models.Car;
import db.models.FinancialApplication;
import db.models.Transaction;
import db.models.User;
import db.tables.Accessories;
import db.tables.Appointments;
import db.tables.Cars;
import db.tables.Finances;
import db.tables.Transactions;
import db.tables.Users;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
    private ObservableList data;
    private VBox center;
    private JFXTextField search;
    private static int selectedID;

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
        activateAllButtons();
        viewButton.setDisable(true);
        center.getChildren().clear();
        table = new UsersTableView();
        data = table.getItems();
        setupSearch();
        center.getChildren().add(table);
        borderPane.setCenter(center);

    }

    public void showCars(){
        activateAllButtons();
        viewButton.setDisable(true);
        center.getChildren().clear();
        table = new CarsTableView();
        data = table.getItems();
        setupSearch();
        center.getChildren().add(table);
        borderPane.setCenter(center);
    }

    public void showAccessories(){
        activateAllButtons();
        viewButton.setDisable(true);
        center.getChildren().clear();
        setupSearch();
        table = new AccessoriesTableView();
        data = table.getItems();
        center.getChildren().add(table);
        borderPane.setCenter(center);
    }

    public void showAppointments(){
        activateAllButtons();
        center.getChildren().clear();
        setupSearch();
        table = new AppointmentsTableView();
        data = table.getItems();
        center.getChildren().add(table);
        borderPane.setCenter(center);
    }

    public void showFinancialApps(){
        activateAllButtons();
        addButton.setDisable(true);
        editButton.setDisable(true);
        center.getChildren().clear();
        setupSearch();
        table = new FinancialApplicationView();
        data = table.getItems();
        center.getChildren().add(table);
        borderPane.setCenter(center);

    }

    public void showTransactions(){
        activateAllButtons();
        viewButton.setDisable(true);
        center.getChildren().clear();
        setupSearch();
        table = new TransactionsView();
        data = table.getItems();
        center.getChildren().add(table);
        borderPane.setCenter(center);

    }

    private void setupSearch(){
        search = new JFXTextField();
        search.setStyle("-fx-text-fill: white;");
        search.setPromptText("Search...");
        search.textProperty().addListener((e) -> {
            search();
        });

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

        String filter = search.getText();
        ObservableList filtered = FXCollections.observableArrayList();
        ObservableList<TableColumn> list = table.getColumns();

        if(filter.isEmpty()){

            table.setItems(data);
            return;

        }

        for(int i = 0; i < table.getItems().size(); i++) {
            for(int j = 0; j < table.getColumns().size(); j++){
                TableColumn col = list.get(j);
                String val = col.getCellData(table.getItems().get(i)).toString();
                val = val.toLowerCase();

                if(val.contains(search.textProperty().get().toLowerCase())){
                    filtered.add(table.getItems().get(i));
                    break;
                }
            }
        }
        table.setItems(filtered);
    }

    public void add(){
        String className = table.getClass().getSimpleName();

        if(className.equals("AccessoriesTableView")){
            ModalUtil.setupAndShow(Resource.ADD_ACCESSORY, "Add", true);
        }else if(className.equals("CarsTableView")){
            ModalUtil.setupAndShow(Resource.ADD_CAR, "Add", true);
        }

        table.refresh();
    }

    public void delete(){
        String className = table.getClass().getSimpleName();
        switch(className){

                case "AccessoriesTableView":
                    ModalUtil.showDeleteItemPrompt(new Accessories(), table);
                    break;
                case "CarsTableView":
                    ModalUtil.showDeleteItemPrompt(new Cars(), table);
                    break;
                case "AppointmentTableView":
                    ModalUtil.showDeleteItemPrompt(new Appointments(), table);
                    break;
                case "FinancialApplicationView":
                    ModalUtil.showDeleteItemPrompt(new Finances(), table);
                    break;
                case "TransactionsView":
                    ModalUtil.showDeleteItemPrompt(new Transactions(), table);
                    break;
                case "UsersTableView":
                    ModalUtil.showDeleteItemPrompt(new Users(), table);
                    break;
                default:
                    ModalUtil.showWarning("Issue acquiring item...");
                    break;

            }

    }

    public void edit(){
        if(!table.getSelectionModel().isEmpty()){
            selectedID = table.getSelectionModel().getSelectedIndex();
            if(table.getClass().getSimpleName().equals("AccessoriesTableView")){
                ModalUtil.setupAndShow(Resource.EDIT_ACCESSORY, "Edit", true);
            }else if(table.getClass().getSimpleName().equals("CarsTableView")){
                ModalUtil.setupAndShow(Resource.EDIT_CAR, "Edit", true);
            }
        }

    }

    public void activateAllButtons(){
        addButton.setDisable(false);
        editButton.setDisable(false);
        deleteButton.setDisable(false);
        viewButton.setDisable(false);
    }

    public void returnToMain(){
        ((Stage)buttonToolbar.getScene().getWindow()).close();
    }

    public static int getSelected(){
        return selectedID;
    }

}
