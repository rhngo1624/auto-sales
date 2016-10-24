package db.models;


public class Accessory extends StoreItem implements SQLModel {

    private int ID;

    public void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }

}
