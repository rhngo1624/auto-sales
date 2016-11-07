package db.models;


public class Accessory extends StoreItem {

    private String description;

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

}
