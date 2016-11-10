package app.ui.tableview;


import java.sql.SQLException;

import app.core.SQLModel;
import db.models.Car;
import db.tables.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CarsTableView extends TableView<Car> {

    public CarsTableView(){

        setupColumns();
        setItems(new Cars().getAllRows());

    }

    private void setupColumns(){

        TableColumn<Car, Integer> ID = new TableColumn<>("ID");
        TableColumn<Car, String> Make = new TableColumn<>("Make");
        TableColumn<Car, String> Model = new TableColumn<>("Model");
        TableColumn<Car, Integer> Year = new TableColumn<>("Year");

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Make.setCellValueFactory(new PropertyValueFactory<>("make"));
        Model.setCellValueFactory(new PropertyValueFactory<>("model"));
        Year.setCellValueFactory(new PropertyValueFactory<>("year"));

        ID.prefWidthProperty().bind(this.widthProperty().divide(4));
        Make.prefWidthProperty().bind(this.widthProperty().divide(4));
        Model.prefWidthProperty().bind(this.widthProperty().divide(4));
        Year.prefWidthProperty().bind(this.widthProperty().divide(4));

        getColumns().add(0, ID);
        getColumns().add(1, Make);
        getColumns().add(2, Model);
        getColumns().add(3, Year);
    }

}
