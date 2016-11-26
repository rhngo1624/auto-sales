package db.models;


import app.core.SQLModel;
import app.core.StoreItem;

public class Accessory extends StoreItem implements SQLModel {

    private String description;
    private int isAddOn;

    public Accessory(String name, double price, String imageLocation){
        setName(name);
        setPrice(price);
        setImageLocation(imageLocation);
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public int isAddOn(){
        return isAddOn;
    }

    public void setIsAddOn(int type){
        isAddOn = type;
    }
}
