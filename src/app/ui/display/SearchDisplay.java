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

            Accessories accessoriesTable = new Accessories();
            Cars carsTable = new Cars();

            ObservableList<Car> cars = FXCollections.observableArrayList();
            ObservableList<Accessory> accessories = FXCollections.observableArrayList();

            try{

                cars = carsTable.getAllRows();
                accessories = accessoriesTable.getAllRows();

            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }

            for(Car car : cars){

                if(car.getName().toLowerCase().startsWith(search.toLowerCase())){
                    panes.add(new StoreItemPane(car));
                }

            }

            for(Accessory accessory : accessories){

                if(accessory.getName().toLowerCase().startsWith(search.toLowerCase())){
                    panes.add(new StoreItemPane(accessory));
                }

            }
        

        }

    }

