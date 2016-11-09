package db.models;

import java.util.List;

import app.core.SQLModel;
import app.core.StoreItem;

public class Transaction implements SQLModel {

    private int ID;
    private List<StoreItem> items;

    public void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }

}
