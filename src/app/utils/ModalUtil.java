package app.utils;

import java.io.IOException;
import java.net.URL;

import app.ui.CarDisplay;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ModalUtil {

    public static void setupAndShow(URL uiPath, String title){

        Stage stage;
        Pane layout;

        try{

            layout = FXMLLoader.load(uiPath);

        }catch(IOException e){

            System.out.println("Could not locate FXML File.");
            return;

        }

        stage = new Stage();
        Scene loginScene = new Scene(layout);
        stage.setScene(loginScene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public static void setupAndShow(){

        CarDisplay cd = new CarDisplay();

        BorderPane mainPanel = new BorderPane();
        mainPanel.setCenter(cd.getDisplay());

        Scene scene = new Scene(mainPanel, 500, 500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}
