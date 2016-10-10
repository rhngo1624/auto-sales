package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.utils.DisplayUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

    private final URL UI_PATH = DisplayUtil.getLoginResource();
    private final String TITLE = "AutoSales Login";

    void initialize(){
        setupAndShow();
    }

    private void setupAndShow(){

        Stage loginStage;
        Pane layout;

        try{

            layout = FXMLLoader.load(UI_PATH);

        }catch(IOException e){

            System.out.println("Could not locate FXML File.");
            return;

        }

        loginStage = new Stage();
        Scene loginScene = new Scene(layout);
        loginStage.setScene(loginScene);
        loginStage.setTitle(TITLE);
        loginStage.setResizable(false);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.show();

    }

}

