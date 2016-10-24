package app.ui;

import java.sql.SQLException;

import db.models.Car;
import db.models.SQLModel;
import db.tables.Cars;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;

public class CarDisplay {

    private static final double ELEMENT_SIZE = 200;
    private static final double GAP = ELEMENT_SIZE / 10;
    private int cols;

    private TilePane tilePane = new TilePane();
    private Group display = new Group(tilePane);

    public CarDisplay(){
        tilePane.setHgap(GAP*1.3);
        tilePane.setVgap(GAP*4);
        tilePane.setPrefTileHeight(150);
        tilePane.setPrefTileWidth(230);
        setColumns(4);
    }

    public Group getDisplay(){
        return display;
    }

    private void setColumns(int newCols){
        cols = newCols;
        tilePane.setPrefColumns(cols);
        createElements();
    }

    private void createElements(){


        tilePane.getChildren().clear();

        Cars table = new Cars();
        ObservableList<SQLModel> rows = null;

        try{
            rows = table.getAllRows();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        for(SQLModel model : rows){

            CarPane pane = new CarPane(((Car)model));
            tilePane.getChildren().add(pane);

        }

    }

}
