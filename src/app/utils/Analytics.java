package app.utils;


import java.sql.SQLException;

import db.models.Accessory;
import db.models.Car;
import db.models.SQLModel;
import db.tables.Accessories;
import db.tables.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Analytics {

    public static int getLongestNameLength(){

        ObservableList<SQLModel> cars = FXCollections.observableArrayList();
        ObservableList<SQLModel> accessories = FXCollections.observableArrayList();
        Cars carsTable = new Cars();
        Accessories accessoriesTable = new Accessories();
        int length = 0;

        try{

            cars = carsTable.getAllRows();
            accessories = accessoriesTable.getAllRows();

        }catch(SQLException e){

            System.exit(-1);

        }

        for(SQLModel car : cars){

            int nameLen = ((Car)car).getName().length();

            if( nameLen > length){
                length = nameLen;
            }

        }

        for(SQLModel acc : accessories){

            int nameLen = ((Accessory)acc).getName().length();

            if( nameLen > length){
                length = nameLen;
            }
        }

        return length;

    }


}
