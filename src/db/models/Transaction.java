package db.models;

import java.sql.Date;
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
    private Date date;

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
            serializedStr.append("-");
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
        int itemCount = 0;

        //count number of dashes = num of items
        for(int i = 0; i < serializedItems.length(); i++){
            if(serializedItems.charAt(i) == '-'){
                itemCount++;
            }
        }

        int index = 0;

        //get type and id from serialized string
        while(items.size() != itemCount){

            StringBuilder sb = new StringBuilder();

            while(!(serializedItems.charAt(index) == '-')){

                sb.append(serializedItems.charAt(index));
                index++;

            }

            int type = Character.getNumericValue(sb.toString().charAt(0));
            String num = "";

            for(int i = 1; i < sb.length(); i++){
                num += sb.toString().charAt(i);
            }

            int id = Integer.parseInt(num);

            switch(type){

                case 0:
                    StoreItem acc = new Accessories().get(id);
                    items.add(acc);
                    break;
                case 1:
                    StoreItem car = new Cars().get(id);
                    items.add(car);
                    break;

            }

            index++;

        }


        return items;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

}
