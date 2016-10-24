package app.ui;

import java.sql.SQLException;

import db.models.Car;
import db.models.SQLModel;
import db.tables.Cars;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;

public class CarDisplay {

    private static final double ELEMENT_SIZE = 200;
    private static final double GAP = ELEMENT_SIZE / 10;

    private TilePane tilePane = new TilePane();
    private Group display = new Group(tilePane);

    public CarDisplay(){
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        tilePane.setPrefTileHeight(200);
        tilePane.setPrefTileWidth(200);
        createElements();
    }

    public Group getDisplay(){
        return display;
    }

    private void createElements(){

        tilePane.getChildren().clear();

        Cars table = new Cars();
        ObservableList<SQLModel> rows = null;

        try{
            rows = table.getAllRows();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        for(SQLModel model : rows){

            CarPane newCarPane = new CarPane(((Car)model));
            tilePane.getChildren().add(newCarPane);

        }

    }

}
