package app.ui.display;

import java.sql.SQLException;

import app.core.Display;
import app.core.SQLModel;
import app.core.StoreItem;
import app.ui.items.StoreItemPane;
import db.tables.Cars;
import app.core.SQLTable;
import javafx.collections.ObservableList;

public class CarDisplay extends Display {

    public void createElements(){

        tilePane.getChildren().clear();

        SQLTable table = new Cars();
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
