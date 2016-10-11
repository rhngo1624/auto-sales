package db.models;

import java.util.List;

public class User implements SQLModel {

    private int ID;
    private List<Transaction> transactionIDList;

    public void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }

}
