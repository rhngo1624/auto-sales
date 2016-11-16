package db.models;

import java.util.ArrayList;
import java.util.List;

import app.core.SQLModel;
import app.core.StoreItem;
import db.tables.Accessories;
import db.tables.Cars;
import db.tables.Transactions;

public class Transaction implements SQLModel {

    private int ID;
    private User user;
    private String serializedItems;

    public void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }

    public void setSerializedItems(ArrayList<StoreItem> items){

        StringBuilder serializedStr = new StringBuilder();

        for(StoreItem item : items){
            if(item.getClass().getSimpleName().equals("Car")){
                serializedStr.append(1);
            }else{
                serializedStr.append(0);
            }

            serializedStr.append(item.getID());
        }

        serializedItems = serializedStr.toString();
    }

    public void setSerializedItems(String serializedItems){
        this.serializedItems = serializedItems;
    }

    public String getSerializedItems(){
        return serializedItems;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public ArrayList<StoreItem> getItemList(){

        ArrayList<StoreItem> items = new ArrayList<>();

        for(int index = 0; index < serializedItems.length(); index++){

            int type = serializedItems.charAt(index);
            int itemID = serializedItems.charAt(index+1);

            switch(type){
                case 0:
                    StoreItem acc = new Accessories().get(itemID);
                    items.add(acc);
                    break;
                case 1:
                    StoreItem car = new Cars().get(itemID);
                    items.add(car);
                    break;
            }
        }

        return items;
    }

}
