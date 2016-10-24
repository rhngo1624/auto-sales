package db.models;

import java.text.NumberFormat;

import db.tables.Accessories;
import db.tables.Cars;
import db.tables.SQLTable;

public abstract class StoreItem {

    private String name;
    private double price;
    private String imageLocation;

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
