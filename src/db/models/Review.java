package db.models;

import app.core.SQLModel;

public class Review implements SQLModel {

    private int ID;
    private int rating;
    private String contents;
    private String owner;
    private String title;

    public void setID(int id){
        ID = id;
    }
    public int getID(){
        return ID;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContents(String contents){
        this.contents = contents;
    }

    public String getContents(){
        return contents;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public int getRating(){
        return rating;
    }

    public String getOwner(){
        return owner;
    }

    public void setOwner(String username){
        owner = username;
    }

}
