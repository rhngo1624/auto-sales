package app.utils;

import app.controllers.MainController;
import db.models.User;

public class Session {

    private static User user;

    public static User getUser(){

        if(user != null){
            return user;
        }else{
            return null;
        }

    }

    public static void setUser(User user){
        Session.user = user;
    }

}
