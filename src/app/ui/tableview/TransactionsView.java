package app.ui.tableview;


import com.jfoenix.controls.JFXButton;

import java.sql.Date;
import java.util.ArrayList;

import app.core.StoreItem;
import db.models.Transaction;
import db.tables.Transactions;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TransactionsView extends TableView<Transaction> {

    public TransactionsView(){
        setupColumns();
        setItems(new Transactions().getAllRows());
    }

    private void setupColumns(){

        TableColumn<Transaction, Integer> ID = new TableColumn<>("ID");
        TableColumn<Transaction, String> Username = new TableColumn<>("User");
        TableColumn<Transaction, String> Items = new TableColumn<>("Items");

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Username.setCellValueFactory((cellDataFeatures) -> {
            if(cellDataFeatures.getValue() != null){
                return new SimpleStringProperty(cellDataFeatures.getValue().getUser().getUsername());
            }else{
                return new SimpleStringProperty("<no name>");
            }
        });

        Items.setCellValueFactory((cellDataFeatures) -> {
            if(cellDataFeatures.getValue() != null){
                return new SimpleStringProperty(getItemString(cellDataFeatures.getValue()));
            }else{
                return new SimpleStringProperty("<no items>");
            }
        });

        ID.prefWidthProperty().bind(this.widthProperty().divide(5));
        Username.prefWidthProperty().bind(this.widthProperty().divide(4));
        Items.prefWidthProperty().bind(this.widthProperty().divide(3));

        getColumns().add(0, ID);
        getColumns().add(1, Username);
        getColumns().add(2, Items);

    }

    private String getItemString(Transaction t){
        StringBuilder sb = new StringBuilder();
        for(StoreItem item : t.getItemList()){
            sb.append(item.getName() + "\n");
        }

        return sb.toString();
    }


}
