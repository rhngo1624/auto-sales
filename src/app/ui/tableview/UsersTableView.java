package app.ui.tableview;


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
        setItems(new Users().getAllRows());

    }

    private void setupColumns(){

        TableColumn<User, String> username = new TableColumn<>("USERNAME");
        TableColumn<User, String> address = new TableColumn<>("ADDRESS");
        TableColumn<User, String> city = new TableColumn<>("CITY");
        TableColumn<User, String> state = new TableColumn<>("STATE");
        TableColumn<User, String> phone = new TableColumn<>("PHONE");

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        username.prefWidthProperty().bind(this.widthProperty().divide(5));
        address.prefWidthProperty().bind(this.widthProperty().divide(4));
        city.prefWidthProperty().bind(this.widthProperty().divide(6));
        state.prefWidthProperty().bind(this.widthProperty().divide(8));
        phone.prefWidthProperty().bind(this.widthProperty().divide(5));

        getColumns().add(0, username);
        getColumns().add(1, address);
        getColumns().add(2, city);
        getColumns().add(3, state);
        getColumns().add(4, phone);
    }


}
