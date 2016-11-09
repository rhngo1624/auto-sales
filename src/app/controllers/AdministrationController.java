package app.controllers;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class AdministrationController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
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

        GridPane userPane = new GridPane();

        userPane.getColumnConstraints().add(new ColumnConstraints(50));
        userPane.getColumnConstraints().add(new ColumnConstraints(100));
        userPane.getColumnConstraints().add(new ColumnConstraints(100));
        userPane.setVgap(10);
        userPane.setHgap(10);
        userPane.add(new Label("ID"), 0, 0);
        userPane.add(new Label("USERNAME"), 1, 0);
        userPane.add(new Label("ADMIN"), 2, 0);
        userPane.setGridLinesVisible(true);
        ObservableList<SQLModel> users = FXCollections.observableArrayList();
        try{

             users = new Users().getAllRows();

        }catch(SQLException e){

            ModalUtil.showWarning("Unable to load data.");

        }

        int rowIndex = 1;

        for(SQLModel user : users){

            User u = (User) user;

            Label id = new Label();
            Label name = new Label();
            Label admin = new Label();

            if(u.isAdmin()){
                admin.setText("Admin");
            }else{
                admin.setText("No privileges");
            }

            id.setText(String.valueOf(u.getID()));
            name.setText(u.getUsername());

            userPane.add(id, 0, rowIndex);
            userPane.add(name, 1, rowIndex);
            userPane.add(admin, 2, rowIndex);

            rowIndex++;

        }

        scrollPane.setContent(userPane);

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
