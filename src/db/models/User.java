package db.models;

import java.util.HashMap;
import java.util.List;

public class User implements SQLModel {

    private int ID;
    private String username;
    private List<Transaction> transactions;
    private List<HashMap<StoreItem, Integer>> cart;

    public void setUsername(String username){
        this.username = username;
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
