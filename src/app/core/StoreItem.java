package app.core;

import java.text.NumberFormat;

import app.core.SQLModel;
import db.tables.Accessories;
import db.tables.Cars;
import app.core.SQLTable;

public abstract class StoreItem implements SQLModel {

    private int ID;
    private String name;
    private double price;
    private String imageLocation;

    public int getID(){
        return ID;
    }

    public void setID(int id){
        ID = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void setImageLocation(String imageLocation){

        this.imageLocation = imageLocation;

    }

    public String getImageLocation(){

        return imageLocation;

    }

    public String getDollarAmount(){

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(price);

    }

    public SQLTable getTable(){

        String type = this.getClass().getName();

        switch(type){

            case "Car":
                return new Cars();
            case "Accessory":
                return new Accessories();
            default:
                return null;
        }

    }

}
