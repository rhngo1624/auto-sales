package db.models;


public class Accessory extends StoreItem {

    public Accessory(String name, double price, String imageLocation){
        setName(name);
        setPrice(price);
        setImageLocation(imageLocation);
    }

}
