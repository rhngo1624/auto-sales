package db.models;

import java.util.HashMap;
import java.util.List;

import db.tables.SQLTable;
import javafx.collections.ObservableList;

public class User implements SQLModel {

    private int ID;
    private String username;
    private String password;
    private List<Transaction> transactions;
    private List<StoreItem> cart;

    public boolean addCartItem(StoreItem item){

        SQLTable table = item.getTable();

        try{

            table.insertModel(item);
            cart.add(item);
            return true;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean delCartItem(StoreItem item){

        SQLTable table = item.getTable();

        try{

            table.deleteModel(item.getID());
            cart.remove(item);
            return true;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

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
