package app.utils;


import java.sql.SQLException;

import db.models.Car;
import db.models.SQLModel;
import db.tables.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Analytics {

    public static int getLongestCarNameLength(){

        ObservableList<SQLModel> cars = FXCollections.observableArrayList();
        Cars table = new Cars();
        int length = 0;

        try{

            cars = table.getAllRows();

        }catch(SQLException e){

            System.exit(-1);

        }

        for(SQLModel car : cars){

            int nameLen = ((Car)car).getName().length();

            if( nameLen > length){
                length = nameLen;
            }

        }

        return length;

    }


}
