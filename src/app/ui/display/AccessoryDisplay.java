package app.ui.display;

import java.sql.SQLException;

import app.core.Display;
import app.core.SQLModel;
import app.core.StoreItem;
import app.ui.items.StoreItemPane;
import db.models.Accessory;
import db.tables.Accessories;
import app.core.SQLTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccessoryDisplay extends Display {

    public void createElements(){

        tilePane.getChildren().clear();

        for(SQLModel model : new Accessories().getAllRows()){

            StoreItemPane pane = new StoreItemPane((StoreItem)model);
            tilePane.getChildren().add(pane);

        }

    }

    public void createElements(int type){

        tilePane.getChildren().clear();
        ObservableList<Accessory> accessories = FXCollections.observableArrayList();
        Accessories table = new Accessories();


        switch(type){
            case 0:
                accessories = table.getAllAccessories();
                break;
            case 1:
                accessories = table.getAllExteriorColors();
                break;
            case 2:
                accessories = table.getAllInteriorColors();
                break;
            case 3:
                accessories = table.getAllTires();
                break;
        }

        for(Accessory a : accessories){
            StoreItemPane pane = new StoreItemPane(a);
            tilePane.getChildren().add(pane);
        }

    }
}
