package app.controllers;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class ModalController {

    public void setupAndShow(URL uiPath, String title){

        Stage loginStage;
        Pane layout;

        try{

            layout = FXMLLoader.load(uiPath);

        }catch(IOException e){

            System.out.println("Could not locate FXML File.");
            return;

        }

        loginStage = new Stage();
        Scene loginScene = new Scene(layout);
        loginStage.setScene(loginScene);
        loginStage.setTitle(title);
        loginStage.setResizable(false);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.show();

    }


}
