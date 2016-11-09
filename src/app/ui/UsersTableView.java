package app.ui;


import com.sun.istack.internal.Nullable;

import java.sql.SQLException;

import app.core.SQLModel;
import db.models.User;
import db.tables.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsersTableView extends TableView<User> {

    public UsersTableView(){

        setupColumns();
        setItems(getUsers());

    }

    private void setupColumns(){

        TableColumn<User, Integer> ID = new TableColumn<>("ID");
        TableColumn<User, String> username = new TableColumn<>("USERNAME");
        TableColumn<User, Boolean> admin = new TableColumn<>("ADMIN PRIVILEGES");
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        admin.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

        ID.prefWidthProperty().bind(this.widthProperty().divide(3));
        username.prefWidthProperty().bind(this.widthProperty().divide(3));
        admin.prefWidthProperty().bind(this.widthProperty().divide(3));

        getColumns().add(0, ID);
        getColumns().add(1, username);
        getColumns().add(2, admin);
    }

    private ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();

        try{

            for(SQLModel u : new Users().getAllRows()){
                User user = (User) u;
                users.add(user);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

        return users;

    }


}
