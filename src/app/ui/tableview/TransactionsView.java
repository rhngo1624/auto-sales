package app.ui.tableview;


import com.jfoenix.controls.JFXButton;

import java.sql.Date;
import java.util.ArrayList;

import app.core.StoreItem;
import db.models.Transaction;
import db.tables.Transactions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class TransactionsView extends VBox {

    public TransactionsView(){
        formTransactionView();
    }

    public void formTransactionView(){
        for(Transaction transaction : new Transactions().getAllRows()){

            JFXButton transactionButton = new JFXButton("Date: " + transaction.getDate()
                    + " User: " + transaction.getUser().getUsername());
            transactionButton.setStyle("-fx-background-color: #b5bdc8");

            VBox.setMargin(transactionButton, new Insets(0,0,0,80));

            transactionButton.setOnAction((e) -> {
                printTransaction(transaction);
            });

            getChildren().add(transactionButton);
        }
    }

    private void printTransaction(Transaction transaction){
        ArrayList<StoreItem> items = transaction.getItemList();
        Date date = transaction.getDate();
        String username = transaction.getUser().getUsername();
        System.out.println(date);
        System.out.println("User: " + username);
        for(StoreItem item : items){
            System.out.println("Item: " + item.getName());
        }
    }

}
