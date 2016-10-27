package app.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import db.models.Accessory;
import db.models.Car;
import db.models.SQLModel;
import db.models.StoreItem;
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

            ObservableList<SQLModel> cars = FXCollections.observableArrayList();
            ObservableList<SQLModel> accessories = FXCollections.observableArrayList();

            try{

                cars = carsTable.getAllRows();
                accessories = accessoriesTable.getAllRows();

            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }

            for(SQLModel car : cars){

                if(((Car)car).getName().toLowerCase().contains(search.toLowerCase())){
                    panes.add(new StoreItemPane((StoreItem)car));
                }

            }

            for(SQLModel accessory : accessories){

                if(((Accessory)accessory).getName().toLowerCase().contains(search.toLowerCase())){
                    panes.add(new StoreItemPane((StoreItem)accessory));
                }

            }
        

        }

    }

