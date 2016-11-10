package db.models;

import app.core.SQLModel;

public class Review implements SQLModel {

    private int ID;
    private int rating;
    private String contents;
    private boolean showName;

    public void setID(int id){
        ID = id;
    }
    public int getID(){
        return ID;
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

    public boolean showName(){
        return showName;
    }

    public void setShowName(boolean show){
        showName = show;
    }

}
