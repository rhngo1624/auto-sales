package db.models;

import java.text.NumberFormat;

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

}
