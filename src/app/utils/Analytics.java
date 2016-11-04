package app.utils;


import java.sql.SQLException;

import db.models.Accessory;
import db.models.Car;
import db.models.SQLModel;
import db.models.StoreItem;
import db.tables.Accessories;
import db.tables.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Analytics {


    public <T extends StoreItem> int getMax(ObservableList<T> items ){
        int ret = 0;
        for(T item : items){
            int len = item.getName().length();
            if( len > ret){
                ret = len;
            }
        }

        return ret;
    }


}
