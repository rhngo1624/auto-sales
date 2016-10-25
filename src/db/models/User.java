package db.models;

import java.util.HashMap;
import java.util.List;

import db.tables.SQLTable;
import javafx.collections.ObservableList;

public class User implements SQLModel {

    private int ID;
    private String username;
    private String password;
    private boolean isAdmin = false;
    private List<Transaction> transactions;
    private List<StoreItem> cart;

    public User(String username){

        setUsername(username);

    }

    public void addCartItem(StoreItem item){

        cart.add(item);

    }

    public void delCartItem(StoreItem item){

        cart.remove(item);

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

    public void setAdminPriveleges(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin(){
        return isAdmin;
    }


}
