package app.utils;

import app.controllers.MainController;
import db.models.User;

public class Session {

    private static Session instance = null;
    private User user;

    private Session(){

    }

    public static Session getInstance(){
        if(instance == null){
            instance = new Session();
        }

        return instance;
    }

    public User getUser(){

        if(user != null){
            return user;
        }else{
            return null;
        }

    }

    public void setUser(User user){
        if(user != null){
            this.user = user;
        }
    }

    public void end(){
        this.user = null;
    }

}
