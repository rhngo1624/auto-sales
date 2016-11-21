package app.core;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

import app.core.SQLModel;
import db.models.Review;
import db.tables.Accessories;
import db.tables.Cars;
import app.core.SQLTable;
import db.tables.Reviews;

public abstract class StoreItem implements SQLModel {

    private int ID;
    private int rating = 0;
    private String name;
    private double price;
    private final String dollarAmount = getDollarAmount();
    private String type;
    private String imageLocation;

    public StoreItem(){
        type = this.getClass().getSimpleName();
    }
    public String getType(){
        return type;
    }

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}
