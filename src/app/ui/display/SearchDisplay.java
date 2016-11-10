package app.ui.display;

import java.sql.SQLException;
import java.util.ArrayList;

import app.core.Display;
import app.ui.items.StoreItemPane;
import db.models.Accessory;
import db.models.Car;
import app.core.SQLModel;
import app.core.StoreItem;
import db.tables.Accessories;
import db.tables.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchDisplay extends Display {

    private ArrayList<StoreItemPane> panes = new ArrayList<>();

    public void createElements(){


        for(StoreItemPane pane : panes){
            tilePane.getChildren().add(pane);
        }


    }


    public void setSearch(String search){

            for(Car car : new Cars().getAllRows()){

                if(car.getName().toLowerCase().startsWith(search.toLowerCase())){
                    panes.add(new StoreItemPane(car));
                }

            }

            for(Accessory accessory : new Accessories().getAllRows()){

                if(accessory.getName().toLowerCase().startsWith(search.toLowerCase())){
                    panes.add(new StoreItemPane(accessory));
                }

            }
        

        }

    }

