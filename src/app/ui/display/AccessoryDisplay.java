package app.ui.display;

import java.sql.SQLException;

import app.core.Display;
import app.core.SQLModel;
import app.core.StoreItem;
import app.ui.items.StoreItemPane;
import db.tables.Accessories;
import app.core.SQLTable;
import javafx.collections.ObservableList;

public class AccessoryDisplay extends Display {

    public void createElements(){

        tilePane.getChildren().clear();

        for(SQLModel model : new Accessories().getAllRows()){

            StoreItemPane pane = new StoreItemPane((StoreItem)model);
            tilePane.getChildren().add(pane);

        }

    }

    public void createElementsAddOnsOnly(){

    }
}
