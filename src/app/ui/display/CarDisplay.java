package app.ui.display;

import java.sql.SQLException;
import java.util.ArrayList;

import app.core.Display;
import app.core.SQLModel;
import app.core.StoreItem;
import app.ui.items.StoreItemPane;
import db.models.Car;
import db.tables.Cars;
import app.core.SQLTable;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class CarDisplay extends Display {

    public void createElements(){

        tilePane.getChildren().clear();

        for(Car model : new Cars().getAllRows()){

            StoreItemPane pane = new StoreItemPane(model);
            tilePane.getChildren().add(pane);

        }

    }

    public void createElements(ArrayList<Car> cars){
        tilePane.getChildren().clear();

        for(Car car : cars){
            StoreItemPane pane = new StoreItemPane(car);
            tilePane.getChildren().add(pane);
        }
    }

    public void createElementsWithPanes(ArrayList<StoreItemPane> panes){
        tilePane.getChildren().clear();
        for(StoreItemPane pane : panes){
            tilePane.getChildren().add(pane);
        }

    }

}
