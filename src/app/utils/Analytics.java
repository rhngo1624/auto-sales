package app.utils;


import app.core.StoreItem;
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
