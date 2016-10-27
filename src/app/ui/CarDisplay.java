package app.ui;

import java.sql.SQLException;

import db.models.Car;
import db.models.SQLModel;
import db.models.StoreItem;
import db.tables.Cars;
import db.tables.SQLTable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;

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
