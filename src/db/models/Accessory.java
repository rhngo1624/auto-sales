package db.models;


public class Accessory extends StoreItem implements SQLModel {

    private int ID;

    public Accessory(String name, double price, String imageLocation){
        setName(name);
        setPrice(price);
        setImageLocation(imageLocation);
    }

    public void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }

}
