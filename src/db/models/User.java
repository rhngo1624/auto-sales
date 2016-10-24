package db.models;

import java.util.HashMap;
import java.util.List;

public class User implements SQLModel {

    private int ID;
    private String username;
    private String password;
    private List<Transaction> transactions;
    private List<StoreItem> cart;

    public void addCartItem(StoreItem item){

    }

    public void delCartItem(StoreItem item){


    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }

    public void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }


}
