package app.ui;

import java.sql.SQLException;

import db.models.SQLModel;
import db.models.StoreItem;
import db.tables.Accessories;
import db.tables.Cars;
import db.tables.SQLTable;
import javafx.collections.ObservableList;

public class AccessoryDisplay extends Display {

    protected void createElements(){

        tilePane.getChildren().clear();

        SQLTable table = new Accessories();
        ObservableList<SQLModel> rows = null;

        try{
            rows = table.getAllRows();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        for(SQLModel model : rows){

            StoreItemPane pane = new StoreItemPane((StoreItem)model);
            tilePane.getChildren().add(pane);

        }

    }
}
