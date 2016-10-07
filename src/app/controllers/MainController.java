package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.Event;

/**
 * Created by RayDeveloper on 10/7/16.
 */
public class MainController {

    @FXML
    private Button loginButton;

    @FXML
    public void initialize(){

    }

    public void login(Event event){

        try{

            Parent root = FXMLLoader.load(getClass().getResource("/app/views/login.fxml"));

        }catch (IOException err){

            System.out.println("FXML file not found");

        }



    }

}
