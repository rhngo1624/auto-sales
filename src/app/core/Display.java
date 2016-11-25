package app.core;


import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.layout.TilePane;

public abstract class Display {

    private static final double ELEMENT_SIZE = 200;
    private static final double GAP = ELEMENT_SIZE / 10;
    protected int cols = 4;
    protected TilePane tilePane = new TilePane();
    private Group display = new Group(tilePane);

    public Display(){
        tilePane.setHgap(GAP*1.3);
        tilePane.setVgap(GAP*4);
        tilePane.setPrefTileHeight(150);
        tilePane.setPrefTileWidth(230);
        setColumns(cols);
    }

    public Group getDisplay(){
        return display;
    }

    public void setColumns(int newCols){
        cols = newCols;
        tilePane.setPrefColumns(cols);
    }

    public void setPrefTileHeight(double height){
        tilePane.setPrefTileHeight(height);
    }

    public void setPrefTileWidth(double width){
        tilePane.setPrefTileWidth(width);
    }

    protected abstract void createElements();

}
