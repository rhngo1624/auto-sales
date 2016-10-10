package app.controllers;

import java.util.ResourceBundle;
import java.net.URL;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button loginButton;

    @FXML
    public void initialize(URL Location, ResourceBundle rb){

    }

    public void customize(){


    }

    public void login(){

        LoginController lc = new LoginController();
        lc.initialize();

    }


}
