package app.ui.tableview;


import java.sql.SQLException;

import app.core.SQLModel;
import db.models.Accessory;
import db.tables.Accessories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccessoriesTableView extends TableView<Accessory> {

    public AccessoriesTableView(){

        setupColumns();
        setItems(getAccessories());

    }

    private void setupColumns(){

        TableColumn<Accessory, Integer> ID = new TableColumn<>("ID");
        TableColumn<Accessory, String> Name = new TableColumn<>("Name");

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));


        ID.prefWidthProperty().bind(this.widthProperty().divide(2));
        Name.prefWidthProperty().bind(this.widthProperty().divide(2));

        getColumns().add(0, ID);
        getColumns().add(1, Name);
    }

    private ObservableList<Accessory> getAccessories(){
        ObservableList<Accessory> accessories = FXCollections.observableArrayList();

        try{

            for(SQLModel c : new Accessories().getAllRows()){
                Accessory Accessory = (Accessory) c;
                accessories.add(Accessory);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

        return accessories;

    }

}