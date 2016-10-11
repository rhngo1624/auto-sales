package db.models;


public class Car implements SQLModel, StoreItem {

    private int ID;
    private double price;

    public void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

}
